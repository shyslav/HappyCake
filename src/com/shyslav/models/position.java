package com.shyslav.models;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class position {
    private int id;
    private String name;
    private double Salary;

    public position(int id, double salary, String name) {
        this.id = id;
        Salary = salary;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
