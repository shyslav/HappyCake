package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.orders;
import com.shyslav.server.comands;

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
            orderList = comands.getOrders(null);
        } else
        {
            orderList = comands.getOrders(String.valueOf(id));
        }
    }
}
