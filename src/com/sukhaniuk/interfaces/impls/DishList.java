package com.sukhaniuk.interfaces.impls;

import appmodels._Dish;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class DishList {
    private ArrayList<_Dish> dishList;

    public ArrayList<_Dish> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<_Dish> dishList) {
        this.dishList = dishList;
    }

    public DishList(int id) {
        if(id == 0) {
            dishList = ServerCommands.getDish(null);
        } else
        {
            dishList = ServerCommands.getDish(String.valueOf(id));
        }
        System.out.println(dishList.size());
    }

}
