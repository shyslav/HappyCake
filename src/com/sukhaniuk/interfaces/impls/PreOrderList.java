package com.sukhaniuk.interfaces.impls;

import appmodels.preOrderTable;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PreOrderList {
    private ArrayList<preOrderTable> preorder;

    public PreOrderList(int id) {
        if(id == 0) {
            preorder = ServerCommands.getPreOrder(null);
        } else
        {
            preorder = ServerCommands.getPreOrder(String.valueOf(id));
        }
    }

    public ArrayList<preOrderTable> getPreorder() {
        return preorder;
    }

    public void setPreorder(ArrayList<preOrderTable> preorder) {
        this.preorder = preorder;
    }
}
