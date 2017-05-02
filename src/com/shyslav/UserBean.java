package com.shyslav;

import com.happycake.sitemodels.*;
import com.shyslav.controller.actions.ClientActions;

/**
 * @author Shyshkin Vladyslav on 01.05.17.
 */
public class UserBean {
    private final ClientActions clientActions;
    private final Employees emp;

    /**
     * If true data was loaded
     */
    private boolean load = false;

    /**
     * List of news
     */
    private NewsList newsList;
    /**
     * List of categories
     */
    private CategoriesList categoriesList;
    /**
     * List of dishes
     */
    private DishesList dishesList;
    /**
     * List of reservations
     */
    private ReservationList reservationList;
    /**
     * Pre orders list
     */
    private PreOrderList preOrderList;
    /**
     * Employees list
     */
    private EmployeesList employeesList;
    /**
     * Reports list
     */
    private ReportsList reportsList;
    /**
     * Cafe coordinates list
     */
    private CafeCoordinateList cafeCoordinatesList;
    /**
     * Positions list
     */
    private PositionsList positionsList;

    /**
     * Orders list
     */
    private OrderList orderList;

    public UserBean(ClientActions clientActions, Employees emp) {
        this.clientActions = clientActions;
        this.emp = emp;
    }

    /**
     * Reload all list from server
     */
    public void reload() {
        newsList = clientActions.selectNews().getObject(NewsList.class);
        categoriesList = clientActions.selectCategories().getObject(CategoriesList.class);
        dishesList = clientActions.selectDish().getObject(DishesList.class);
        reservationList = clientActions.selectReservation().getObject(ReservationList.class);
        preOrderList = clientActions.selectPreOrder().getObject(PreOrderList.class);
        employeesList = clientActions.selectEmployees().getObject(EmployeesList.class);
        reportsList = clientActions.selectReports().getObject(ReportsList.class);
        cafeCoordinatesList = clientActions.selectCafeCoordinate().getObject(CafeCoordinateList.class);
        positionsList = clientActions.selectPositions().getObject(PositionsList.class);
        orderList = clientActions.selectOrders().getObject(OrderList.class);

        load = true;
    }

    public Employees getEmp() {
        return emp;
    }

    public NewsList getNewsList() {
        return newsList;
    }

    public CategoriesList getCategoriesList() {
        return categoriesList;
    }

    public DishesList getDishesList() {
        return dishesList;
    }

    public ReservationList getReservationList() {
        return reservationList;
    }

    public PreOrderList getPreOrderList() {
        return preOrderList;
    }

    public EmployeesList getEmployeesList() {
        return employeesList;
    }

    public ReportsList getReportsList() {
        return reportsList;
    }

    public CafeCoordinateList getCafeCoordinatesList() {
        return cafeCoordinatesList;
    }

    public PositionsList getPositionsList() {
        return positionsList;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public boolean isLoad() {
        return load;
    }

    public void waitLoad() {
        while (!load) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }

    public ClientActions getClientActions() {
        return clientActions;
    }
}
