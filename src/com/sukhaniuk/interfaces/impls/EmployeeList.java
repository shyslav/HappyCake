package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.jEmployees;
import com.sukhaniuk.interfaces.EmployeeInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class EmployeeList implements EmployeeInt{
    private ObservableList<jEmployees> employeeList = FXCollections.observableArrayList();

    @Override
    public void add(jEmployees Employee) {
        employeeList.add(Employee);
    }

    @Override
    public void delete(jEmployees Employee) {
        employeeList.remove(Employee);
    }

    @Override
    public void update(jEmployees Employee) {

    }

    public ObservableList<jEmployees> getEmployeeList() {
        return employeeList;
    }

    public void initialTable() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        employeeList.add(new jEmployees(1,"position","cafe","name","lastName","address",new Date(2016,05,05)));
        employeeList.add(new jEmployees(1,"position","cafe","name","lastName","address",null));
        employeeList.add(new jEmployees(1,"position","cafe","name","lastName","address",null));
    }

}
