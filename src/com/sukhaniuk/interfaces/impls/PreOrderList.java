package com.sukhaniuk.interfaces.impls;

import appmodels._PreOrderTable;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PreOrderList {
    private ArrayList<_PreOrderTable> preorder;

    public PreOrderList(int id) {
        if(id == 0) {
            preorder = ServerCommands.getPreOrder(null);
        } else
        {
            preorder = ServerCommands.getPreOrder(String.valueOf(id));
        }
    }

    public ArrayList<_PreOrderTable> getPreorder() {
        return preorder;
    }

    public void setPreorder(ArrayList<_PreOrderTable> preorder) {
        this.preorder = preorder;
    }
}
