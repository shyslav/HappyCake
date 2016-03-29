package com.sukhaniuk.interfaces;

import com.shyslav.models.jEmployees;
import com.shyslav.models.news;

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
