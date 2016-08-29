package com.sukhaniuk.interfaces.impls;

import appmodels._Order;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 21.05.2016.
 */
public class OrderList {
    private ArrayList<_Order> orderList;

    public ArrayList<_Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<_Order> orderList) {
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
