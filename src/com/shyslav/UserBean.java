package com.shyslav;

import appmodels.IMTDataList;
import com.happycake.sitemodels.*;
import com.shyslav.controller.actions.ClientActions;

/**
 * @author Shyshkin Vladyslav on 01.05.17.
 */
public class UserBean {
    public enum RELOAD_TYPES {
        NEWS,
        CATEGORIES,
        DISHES,
        PREORDER,
        EMPLOYEES,
        REPORTS,
        CAFECOORDINATE,
        RESERVATION,
        ORDERS
    }

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
        orderList = clientActions.selectOrders().getObject(OrderList.class);

        load = true;
    }

    /**
     * Get imt data list
     *
     * @return list for imt algorithm
     */
    public IMTDataList getIMTDataList(int[] dishIDS) {
        return clientActions.getDataForIMTAlgo(dishIDS).getObject(IMTDataList.class);
    }

    /**
     * Reload news list
     */
    public void reloadNews(RELOAD_TYPES reloadType) {
        switch (reloadType) {
            case NEWS: {
                newsList = clientActions.selectNews().getObject(NewsList.class);
                break;
            }
            case DISHES: {
                dishesList = clientActions.selectDish().getObject(DishesList.class);
                break;
            }
            case REPORTS: {
                reportsList = clientActions.selectReports().getObject(ReportsList.class);
                break;
            }
            case PREORDER: {
                preOrderList = clientActions.selectPreOrder().getObject(PreOrderList.class);
                break;
            }
            case EMPLOYEES: {
                employeesList = clientActions.selectEmployees().getObject(EmployeesList.class);
                break;
            }
            case ORDERS: {
                orderList = clientActions.selectOrders().getObject(OrderList.class);
                break;
            }
            case CATEGORIES: {
                categoriesList = clientActions.selectCategories().getObject(CategoriesList.class);
                break;
            }
            case CAFECOORDINATE: {
                cafeCoordinatesList = clientActions.selectCafeCoordinate().getObject(CafeCoordinateList.class);
                break;
            }
            case RESERVATION: {
                reservationList = clientActions.selectReservation().getObject(ReservationList.class);
                break;
            }
            default: {
                reload();
                break;
            }
        }
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
