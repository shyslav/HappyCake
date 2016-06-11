package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.preOrderTable;
import com.shyslav.server.comands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PreOrderList {
    private ArrayList<preOrderTable> preorder;

    public PreOrderList(int id) {
        if(id == 0) {
            preorder = comands.getPreOrder(null);
        } else
        {
            preorder = comands.getPreOrder(String.valueOf(id));
        }
    }

    public ArrayList<preOrderTable> getPreorder() {
        return preorder;
    }

    public void setPreorder(ArrayList<preOrderTable> preorder) {
        this.preorder = preorder;
    }
}
