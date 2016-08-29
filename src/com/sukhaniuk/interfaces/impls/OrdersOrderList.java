package com.sukhaniuk.interfaces.impls;


import appmodels._OrderList;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

public class OrdersOrderList {
    private ArrayList<_OrderList> OrdersOrderList;

    public ArrayList<_OrderList> getOrdersOrderList() {
        return OrdersOrderList;
    }

    public void setOrdersOrderList(ArrayList<_OrderList> ordersOrderList) {
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

