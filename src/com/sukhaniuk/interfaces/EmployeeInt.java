package com.sukhaniuk.interfaces;

import com.shyslav.models.jEmployees;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public interface EmployeeInt {
    //добавление
    void add(jEmployees Employee);
    //удаление
    void delete(jEmployees Employee);
    //обновление
    void update(jEmployees Employee);
}
