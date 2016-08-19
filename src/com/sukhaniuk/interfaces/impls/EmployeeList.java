package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.employees;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class EmployeeList {
    private ArrayList<employees> employees;

    public EmployeeList(int id) {
        if(id == 0) {
            employees = ServerCommands.getEmployees(null);
        } else
        {
            employees = ServerCommands.getEmployees(String.valueOf(id));
        }
    }

    public ArrayList<employees> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<employees> employees) {
        this.employees = employees;
    }
}
