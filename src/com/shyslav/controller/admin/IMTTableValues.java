package com.shyslav.controller.admin;

/**
 * @author Shyshkin Vladyslav on 19.05.17.
 */
public class IMTTableValues {
    private int dishID;
    private String dishName;
    private double storageCost;
    private double shippingCost;

    public IMTTableValues(int dishID, String dishName, double storageCost, double shippingCost) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.storageCost = storageCost;
        this.shippingCost = shippingCost;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getStorageCost() {
        return storageCost;
    }

    public void setStorageCost(double storageCost) {
        this.storageCost = storageCost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}
