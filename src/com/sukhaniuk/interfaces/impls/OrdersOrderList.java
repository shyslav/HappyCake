package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.orderList;
import com.shyslav.server.comands;

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
            OrdersOrderList = comands.getOrdersOrderList(null);
        } else
        {
            OrdersOrderList = comands.getOrdersOrderList(String.valueOf(id));
        }
    }
}

