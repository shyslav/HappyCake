package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.employees;
import com.shyslav.server.comands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class EmployeeList {
    private ArrayList<employees> employees;

    public EmployeeList(int id) {
        if(id == 0) {
            employees = comands.getEmployees(null);
        } else
        {
            employees = comands.getEmployees(String.valueOf(id));
        }
    }

    public ArrayList<employees> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<employees> employees) {
        this.employees = employees;
    }
}
