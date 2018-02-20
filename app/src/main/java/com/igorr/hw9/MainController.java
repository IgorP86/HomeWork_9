package com.igorr.hw9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import fragments.CardView;
import fragments.DataOperations;
import fragments.DialogActionSelect;
import model.Note;

public class MainController extends AppCompatActivity implements MyActionListener {
    private static final String CARD_VIEW = "cardView";
    private static final String DATA_OPERATIONS = "changeData";
    private FragmentManager fragmentManager;
    private static int selectedPos;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_controller);
        fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getFragments().size() == 0) {
            updateUI(R.string.CARD_VIEW);
        }
/*        for (int i = 0; i < 8; i++) {
            String[] data = {"Surname " + i, "Name " + i, "patronymic " + i, "8900999900" + Integer.toString(i)};
            Note.Actions.addNote(data);
        }*/
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
        transaction.commit();
    }

    @Override
    public void addItem(String[] args) {
        Note.Actions.addNote(args);
    }

    @Override
    public void overwriteItem(String[] args) {
        Note.Actions.overwriteItem(selectedPos, args);
    }

    @Override
    public void representItem() {
        Note.PersonItem tempItem = Note.Actions.getNote(selectedPos);
        Fragment tempFrag = new DataOperations();

        Bundle bundle = new Bundle();

        String[] keys = {"surname", "name", "patronymic", "phone"};
        bundle.putString(keys[0], tempItem.getSurname());
        bundle.putString(keys[1], tempItem.getName());
        bundle.putString(keys[2], tempItem.getPatronymic());
        bundle.putString(keys[3], tempItem.getPhoneNumber());
        tempFrag.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentView, tempFrag, DATA_OPERATIONS);
        transaction.commit();

    }

    @Override
    public void deleteItem() {
        Note.Actions.removeItem(selectedPos);
        updateUI(R.string.CARD_VIEW);
    }

    @Override
    public void callDialog() {
        DialogActionSelect dialog = new DialogActionSelect();
        dialog.show(fragmentManager, "tag");
    }

    @Override
    public void setSelectedItem(int position) {
        selectedPos = position;
    }
}