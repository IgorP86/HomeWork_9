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
import android.widget.TextView;

import com.igorr.hw9.MyActionListener;
import com.igorr.hw9.R;

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
    private static boolean OVERWRITE = false;   //for write?

    @BindViews({R.id.inputSurname, R.id.inputName, R.id.inputPatronymic, R.id.inputPhone})
    TextView[] inputFields;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            myActionListener = (MyActionListener) context;
        } catch (Exception e) {
            Log.d("CardView", "onAttach" + e.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.f_change_data, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Добавление/Изменение данных");
        actionBar.setDisplayHomeAsUpEnabled(true);
        unbinder = ButterKnife.bind(this, this.view);

        Bundle args = getArguments();
        //Initialization
        if (args != null) {
            inputFields[1].setText(args.getString("name"));
            OVERWRITE = !OVERWRITE;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            myActionListener.updateUI(R.string.CARD_VIEW);
        }
        return true;
    }

    //buttons
    @OnClick(R.id.addNote)
    protected void btnClickAddNote() {
        //get data from fields
        String[] args = new String[inputFields.length];
        for (int i = 0; i < inputFields.length; i++)
            args[i] = inputFields[i].getText().toString();

        //clear fields
        for (TextView inputField : inputFields)
            inputField.setText("");
/*
        if(OVERWRITE){
            //выполнить замену
            myActionListener.overwriteItem(args);
            OVERWRITE = !OVERWRITE;
        }*/
        myActionListener.addItem(args);
        myActionListener.updateUI(R.string.CARD_VIEW);
    }

    //for tests
    @OnClick(R.id.getNote)
    protected void refresh() {
        myActionListener.updateUI(R.string.DATA_OPERATIONS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
