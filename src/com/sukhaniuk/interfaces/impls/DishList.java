package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.dish;
import com.shyslav.server.comands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class DishList {
    private ArrayList<dish> dishList;

    public ArrayList<dish> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<dish> dishList) {
        this.dishList = dishList;
    }

    public DishList() {
        dishList = comands.getDish(null);
        System.out.println(dishList.size());
    }

}
