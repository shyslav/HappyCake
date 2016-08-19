package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.category;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class CategoryList {
    private ArrayList<category> categoryLists;

    public ArrayList<category> getCategoryLists() {
        return categoryLists;
    }

    public void setCategoryLists(ArrayList<category> categoryLists) {
        this.categoryLists = categoryLists;
    }

    public CategoryList() {
        categoryLists = ServerCommands.getCategory(null);
        System.out.println(categoryLists.size());
    }
}
