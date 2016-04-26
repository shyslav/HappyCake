package com.shyslav.models;

import java.util.Date;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class orders {
    private int id;
    private int employeeId;
    private double fullPrice;
    private Date oData;
    private boolean compliteOrNot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Date getoData() {
        return oData;
    }

    public void setoData(Date oData) {
        this.oData = oData;
    }

    public boolean isCompliteOrNot() {
        return compliteOrNot;
    }

    public void setCompliteOrNot(boolean compliteOrNot) {
        this.compliteOrNot = compliteOrNot;
    }
}
