package com.shyslav.models;

import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class jEmployees {
    private int id;
    private String positionID;
    private String cafeID;
    private String name;
    private String lastname;
    private String address;
    private Date birthdayDay;

    public jEmployees(int id, String positionID, String cafeID, String name, String lastname, String address, Date birthdayDay) {
        this.id = id;
        this.positionID = positionID;
        this.cafeID = cafeID;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.birthdayDay = birthdayDay;
    }

    public String getCafeID() {
        return cafeID;
    }

    public void setCafeID(String cafeID) {
        this.cafeID = cafeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(Date birthdayDay) {
        this.birthdayDay = birthdayDay;
    }
}
