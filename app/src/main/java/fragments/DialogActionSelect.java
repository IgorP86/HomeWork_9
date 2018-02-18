package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;

import com.igorr.hw9.MyActionListener;
import com.igorr.hw9.R;

/**
 * Created by Igorr on 18.02.2018.
 */

public class DialogActionSelect extends DialogFragment {
    private MyActionListener myActionListener;
    private int position;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            myActionListener = (MyActionListener) context;
        } catch (Exception e) {
            Log.d("DialogActionSelect", "onAttach" + e.toString());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        position = getArguments().getInt("position");

         builder.setView(inflater.inflate(R.layout.action_dialog, null)).setMessage(R.string.chooseAction)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myActionListener.deleteItem(position);
                    }
                })
                .setNegativeButton(R.string.rename, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myActionListener.representItem(position);
                    }
                });
        return builder.create();
    }
}