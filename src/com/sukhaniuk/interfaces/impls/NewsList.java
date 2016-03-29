package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.news;
import com.sukhaniuk.interfaces.newsInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class NewsList implements newsInt {
    private ObservableList<news> newsList = FXCollections.observableArrayList();

    public ObservableList<news> getNewsList() {
        return newsList;
    }

    public void setNewsList(ObservableList<news> newsList) {
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
    public void initialTable() {
        //(int id, int authorID, String name, String nText, Date nDate, String tegs, int view, String imageLink)
        newsList.add(new news(1,1,"Theme","tfsdfsdfsdfsdfsdfsdext",new Date(2016,06,06),"tegs",1,"httt:/image.123.jpg"));
        newsList.add(new news(2,1,"Theme","tefdsfsdfsdfsdfxt",new Date(2016,06,06),"tegs",2,"httt:/image.123.jpg"));
        newsList.add(new news(3,1,"Theme","tsfdsffdsfsddsext",new Date(2016,06,06),"tegs",3,"httt:/image.123.jpg"));
    }
}
