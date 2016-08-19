package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.news;
import com.shyslav.server.ServerCommands;
import com.sukhaniuk.interfaces.newsInt;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class NewsList implements newsInt {
    private ArrayList<news> newsList;

    public ArrayList<news> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<news> newsList) {
        this.newsList = newsList;
    }

    @Override
    public void add(news news) {

    }

    @Override
    public void delete(news news) {

    }

    @Override
    public void update(news news) {

    }

    public NewsList() {
        newsList = ServerCommands.getNews(null);
    }
}
