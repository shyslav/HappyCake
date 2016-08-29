package com.sukhaniuk.interfaces;

import appmodels.news;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public interface newsInt {
    //добавление
    void add(news news);
    //удаление
    void delete(news news);
    //обновление
    void update(news news);
}
