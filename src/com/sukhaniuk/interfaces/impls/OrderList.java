package com.sukhaniuk.interfaces.impls;

import appmodels.orders;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 21.05.2016.
 */
public class OrderList {
    private ArrayList<orders> orderList;

    public ArrayList<orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<orders> orderList) {
        this.orderList = orderList;
    }

    public OrderList(int id) {
        if(id == 0) {
            orderList = ServerCommands.getOrders(null);
        } else
        {
            orderList = ServerCommands.getOrders(String.valueOf(id));
        }
    }
}
