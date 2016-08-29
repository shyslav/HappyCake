package com.sukhaniuk.interfaces.impls;

import appmodels.dish;
import com.shyslav.server.ServerCommands;

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
