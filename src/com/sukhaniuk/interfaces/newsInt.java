package com.sukhaniuk.interfaces;

import appmodels._News;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public interface newsInt {
    //добавление
    void add(_News news);
    //удаление
    void delete(_News news);
    //обновление
    void update(_News news);
}
