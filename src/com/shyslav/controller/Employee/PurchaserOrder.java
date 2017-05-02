package com.shyslav.controller.Employee;

import com.happycake.sitemodels.Dish;

/**
 * @author Shyshkin Vladyslav on 02.05.17.
 */
public class PurchaserOrder {
    private final Dish dish;
    private double sum;
    private int count;

    public PurchaserOrder(Dish dish, int count) {
        this.dish = dish;
        this.sum = dish.getPrice() * count;
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public double getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    /**
     * Reload sum and count
     *
     * @param count dish count in order
     */
    public void addCount(int count) {
        this.count += count;
        this.sum = dish.getPrice() * this.count;
    }
}
