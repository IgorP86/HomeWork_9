package fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igorr.hw9.MyActionListener;
import com.igorr.hw9.R;

import model.Note;

/**
 * Created by Igorr on 15.02.2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyHolder> {
    private MyActionListener myActionListener;
    private Note data;


    public PersonAdapter(Fragment fragment, Note note) {
        data = note;
        try {
            myActionListener = (MyActionListener) fragment.getContext();
        } catch (Exception e) {
            Log.d("PersonAdapter", "onAttach" + e.toString());
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setPosition(position);
        holder.name.setText(data.getActions().getNote(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.getActions().getNoteSize();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        int position;
        float x = 0;

        void setPosition(int position) {
            this.position = position;
        }

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.outName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActionListener.callDialog(position);
                }
            });

            //swipe for delete
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                        x = event.getX();
                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        float x2 = event.getX();
                        Log.d("delta", Float.toString(x2 - x));
                        if (x2 - x > 50) {
                            myActionListener.deleteItem(position);
                        }
                    }
                    return false;
                }
            });
        }
    }
}