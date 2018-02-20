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

    public PersonAdapter(Fragment fragment) {
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
        holder.surname.setText("ф: ".concat(Note.Actions.getNote(position).getSurname()));
        holder.name.setText("и: ".concat(Note.Actions.getNote(position).getName()));
        holder.patronymic.setText("о: ".concat(Note.Actions.getNote(position).getPatronymic()));
        holder.number.setText("тел: ".concat(Note.Actions.getNote(position).getPhoneNumber()));
    }

    @Override
    public int getItemCount() {
        return Note.Actions.getNoteSize();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView surname;
        private TextView patronymic;
        private TextView number;

        private int position;
        float x = 0;

        void setPosition(int position) {
            this.position = position;
        }

        private MyHolder(View itemView) {
            super(itemView);

            surname = itemView.findViewById(R.id.outSurname);
            name = itemView.findViewById(R.id.outName);
            patronymic = itemView.findViewById(R.id.outPatronymic);
            number = itemView.findViewById(R.id.outNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActionListener.setSelectedItem(position);
                    myActionListener.callDialog();
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
                            myActionListener.setSelectedItem(position);
                            myActionListener.deleteItem();
                        }
                    }
                    return false;
                }
            });
        }
    }
}