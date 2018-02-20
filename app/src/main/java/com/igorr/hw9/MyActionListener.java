package com.igorr.hw9;

import android.view.View;

/**
 * Created by Igorr on 12.02.2018.
 */

public interface MyActionListener {
    void representItem();

    void deleteItem();

    void callDialog();

    void setSelectedItem(int position);

    void updateUI(int resUI);

    void addItem(String[] args);

    void overwriteItem(String[] args);
}
