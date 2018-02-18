package fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.igorr.hw9.MyActionListener;
import com.igorr.hw9.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import model.Note;

/**
 * Created by Igorr on 12.02.2018.
 */

public class CardView extends Fragment {
    private MyActionListener myActionListener;
    private Unbinder unbinder;
    private View view;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.f_card_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Список контактов");
        unbinder = ButterKnife.bind(this, this.view);

        PersonAdapter personAdapter = new PersonAdapter(this, new Note());
        recyclerView.setAdapter(personAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_card_view_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toDataOperations) {
            myActionListener.updateUI(R.string.DATA_OPERATIONS);
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}