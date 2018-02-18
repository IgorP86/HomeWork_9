package com.igorr.hw9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import fragments.CardView;
import fragments.DataOperations;
import fragments.DialogActionSelect;
import model.Note;

public class MainController extends AppCompatActivity implements MyActionListener {
    private static final String CARD_VIEW = "cardView";
    private static final String DATA_OPERATIONS = "changeData";
    private FragmentManager fragmentManager;
    private Note note;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_controller);
        fragmentManager = getSupportFragmentManager();
        Log.d("sss", "onCreate");

        note = new Note();
        ////
        for (int i = 0; i < 8; i++) {
            String[] data = {"Surname " + i, "Name" + i, "patronymic" + i, "45818" + Integer.toString(i)};
            note.getActions().addNote(data[0], data[1], data[2], data[3]);

        }
        if (savedInstanceState != null)
            updateUI(savedInstanceState.getInt("resUI"));
        else
            updateUI(R.string.CARD_VIEW);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragmentManager.findFragmentByTag(CARD_VIEW) != null) {
            if (fragmentManager.findFragmentByTag(CARD_VIEW).isVisible()) {
                outState.putInt("resUI", R.string.CARD_VIEW);
            }
        } else if (fragmentManager.findFragmentByTag(DATA_OPERATIONS) != null) {
            if (fragmentManager.findFragmentByTag(DATA_OPERATIONS).isVisible()) {
                outState.putInt("resUI", R.string.DATA_OPERATIONS);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.findFragmentByTag(CARD_VIEW).isVisible())
            System.exit(1);
        else {
            updateUI(R.string.CARD_VIEW);
        }
    }

    //Навигация по фрагментам
    @Override
    public void updateUI(int resUI) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (resUI) {
            case R.string.CARD_VIEW:
                transaction.replace(R.id.contentView, new CardView(), CARD_VIEW);
                break;
            case R.string.DATA_OPERATIONS:
                transaction.replace(R.id.contentView, new DataOperations(), DATA_OPERATIONS);
                break;
        }
        transaction.addToBackStack(null).commit();
    }

    @Override
    public void addItem(String[] args) {
        new Note().getActions().addNote(args[0], args[1], args[2], args[3]);
    }

    @Override
    public void overwriteItem(String[] args) {

    }

    @Override
    public void representItem(int position) {
        Note.PersonItem tempItem = note.getActions().getNote(position);
        Fragment tempFrag = new DataOperations();
        Bundle id = new Bundle();
        id.putString("name", tempItem.getName());
        tempFrag.setArguments(id);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentView, tempFrag, DATA_OPERATIONS);
        transaction.addToBackStack(null).commit();

    }

    @Override
    public void deleteItem(int itemID) {
        this.note.getActions().removeItem(itemID);
        updateUI(R.string.CARD_VIEW);
    }

    @Override
    public void callDialog(int position) {
        DialogActionSelect dialog = new DialogActionSelect();
        Bundle id = new Bundle();
        id.putInt("position", position);
        dialog.setArguments(id);
        dialog.show(fragmentManager, "tag");
    }
}