package com.sukhaniuk.interfaces.impls;

import appmodels._News;
import com.shyslav.server.ServerCommands;
import com.sukhaniuk.interfaces.newsInt;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class NewsList implements newsInt {
    private ArrayList<_News> newsList;

    public ArrayList<_News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<_News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public void add(_News news) {

    }

    @Override
    public void delete(_News news) {

    }

    @Override
    public void update(_News news) {

    }

    public NewsList() {
        newsList = ServerCommands.getNews(null);
    }
}
