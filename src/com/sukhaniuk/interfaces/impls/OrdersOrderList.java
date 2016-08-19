package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.orderList;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

public class OrdersOrderList {
    private ArrayList<orderList> OrdersOrderList;

    public ArrayList<orderList> getOrdersOrderList() {
        return OrdersOrderList;
    }

    public void setOrdersOrderList(ArrayList<orderList> ordersOrderList) {
        OrdersOrderList = ordersOrderList;
    }

    public OrdersOrderList(int id) {
        if(id == 0) {
            OrdersOrderList = ServerCommands.getOrdersOrderList(null);
        } else
        {
            OrdersOrderList = ServerCommands.getOrdersOrderList(String.valueOf(id));
        }
    }
}

