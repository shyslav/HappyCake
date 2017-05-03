package com.shyslav.controller.employee;

import com.happycake.sitemodels.Order;
import com.happycake.sitemodels.OrderDetails;
import com.happycake.sitemodels.OrderDetailsList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shyshkin Vladyslav on 02.05.17.
 */
public class PurchaserOrderList extends ArrayList<PurchaserOrder> {
    private HashMap<Integer, PurchaserOrder> map = new HashMap<>();

    @Override
    public boolean add(PurchaserOrder purchaserOrder) {
        if (map.containsKey(purchaserOrder.getDish().getId())) {
            PurchaserOrder order = map.get(purchaserOrder.getDish().getId());
            order.addCount(purchaserOrder.getCount());
            return true;
        }
        map.put(purchaserOrder.getDish().getId(), purchaserOrder);
        return super.add(purchaserOrder);
    }

    @Override
    public boolean remove(Object o) {
        map.remove(((PurchaserOrder) o).getDish().getId());
        return super.remove(o);
    }

    /**
     * Get total sum
     *
     * @return total sum
     */
    public double getTotalSum() {
        double sum = 0;
        for (PurchaserOrder order : this) {
            sum += order.getSum();
        }
        return sum;
    }

    /**
     * Clear arraylist and map
     */
    @Override
    public void clear() {
        map.clear();
        super.clear();
    }

    /**
     * Generate order
     *
     * @param employeeID employee id
     * @return order by current list
     */
    public Order generateOrder(int employeeID) {
        Order order = new Order();
        order.setEmployeeId(employeeID);
        order.setFullPrice(getTotalSum());

        OrderDetailsList list = new OrderDetailsList();
        for (PurchaserOrder purchaserOrder : this) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setAmount(purchaserOrder.getCount());
            orderDetails.setDishID(purchaserOrder.getDish().getId());
            orderDetails.setPrice(purchaserOrder.getSum());
            list.add(orderDetails);
        }
        order.setOrderDetails(list);
        return order;
    }

}
