package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.igorr.hw9.MyActionListener;
import com.igorr.hw9.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Igorr on 12.02.2018.
 */

public class DataOperations extends Fragment {
    private MyActionListener myActionListener;
    private View view;
    private Unbinder unbinder;
    private static boolean OVERWRITE = false;
    private static String[] KEYS = {"surname", "name", "patronymic", "phone"};

    @BindViews({R.id.inputSurname, R.id.inputName, R.id.inputPatronymic, R.id.inputPhone})
    TextView[] inputFields;
    @BindView(R.id.btnAddNote)
    Button btnAddNote;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            myActionListener = (MyActionListener) context;
        } catch (Exception e) {
            Log.d("CardView", "onAttach" + e.toString());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.f_change_data, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        unbinder = ButterKnife.bind(this, this.view);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        Bundle args = getArguments();
        //Initialization
        if (args != null) {
            actionBar.setTitle(R.string.overwriteContact);
            for (int i = 0; i < inputFields.length; i++) {
                inputFields[i].setText((args.getString(KEYS[i])));
            }
            OVERWRITE = true;
            btnAddNote.setText(R.string.overwrite);
        } else {
            actionBar.setTitle(R.string.addContact);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            myActionListener.updateUI(R.string.CARD_VIEW);
        }
        return true;
    }

    //добавить запись или выполнить замену если флаг установлен
    @OnClick(R.id.btnAddNote)
    protected void btnClickAddNote() {
        //сбор данных
        String[] args = new String[inputFields.length];
        for (int i = 0; i < inputFields.length; i++)
            args[i] = inputFields[i].getText().toString();


        if (OVERWRITE) {
            myActionListener.overwriteItem(args);
            OVERWRITE = false;
        } else {
            myActionListener.addItem(args);
        }
        myActionListener.updateUI(R.string.CARD_VIEW);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}