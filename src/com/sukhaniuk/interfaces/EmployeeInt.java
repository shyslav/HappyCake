package com.sukhaniuk.interfaces;


import appmodels.localmodels.LocalEmployee;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public interface EmployeeInt {
    //добавление
    void add(LocalEmployee Employee);
    //удаление
    void delete(LocalEmployee Employee);
    //обновление
    void update(LocalEmployee Employee);
}
