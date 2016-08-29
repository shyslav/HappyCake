package com.sukhaniuk.interfaces.impls;

import com.shyslav.server.ServerCommands;
import appmodels.*;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class EmployeeList {
    private ArrayList<_Employee> employees;

    public EmployeeList(int id) {
        if(id == 0) {
            employees = ServerCommands.getEmployees(null);
        } else
        {
            employees = ServerCommands.getEmployees(String.valueOf(id));
        }
    }

    public ArrayList<_Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<_Employee> employees) {
        this.employees = employees;
    }
}
