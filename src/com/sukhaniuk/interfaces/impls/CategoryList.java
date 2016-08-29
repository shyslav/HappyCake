package com.sukhaniuk.interfaces.impls;

import appmodels._Category;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class CategoryList {
    private ArrayList<_Category> categoryLists;

    public ArrayList<_Category> getCategoryLists() {
        return categoryLists;
    }

    public void setCategoryLists(ArrayList<_Category> categoryLists) {
        this.categoryLists = categoryLists;
    }

    public CategoryList() {
        categoryLists = ServerCommands.getCategory(null);
        System.out.println(categoryLists.size());
    }
}
