package com.igorr.hw9;

import android.view.View;

/**
 * Created by Igorr on 12.02.2018.
 */

public interface MyActionListener {
    void updateUI(int resUI);

    void addItem(String[] args);

    void replaceItem(int itemID, String[] args);

    void deleteItem(int itemID);

    void callDialog(int itemID, View... views);
}
