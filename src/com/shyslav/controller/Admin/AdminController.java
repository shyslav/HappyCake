package com.shyslav.controller.Admin;

import appmodels.localmodels.LocalEmployee;
import com.shyslav.controller.alert.LazyConfirmDialog;
import com.shyslav.controller.alert.LazyAlert;
import appmodels.*;
import com.shyslav.server.ServerCommands;
import com.shyslav.start.Main;
import com.sukhaniuk.interfaces.impls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.Time;
import java.util.*;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class AdminController {
    //******Employee tabs
    @FXML
    private TableView tableEmployees;
    @FXML
    private TableColumn<LocalEmployee, Integer> id;
    @FXML
    private TableColumn<LocalEmployee, String> positionID;
    @FXML
    private TableColumn<LocalEmployee, String> cafeID;
    @FXML
    private TableColumn<LocalEmployee, String> name;
    @FXML
    private TableColumn<LocalEmployee, String> lastname;
    @FXML
    private TableColumn<LocalEmployee, String> address;
    @FXML
    private TableColumn<LocalEmployee, Date> birthdayDay;
    @FXML
    private TableColumn<LocalEmployee, String> empLogin;
    @FXML
    private TableColumn<LocalEmployee, String> empPass;
    //*******News tabs
    @FXML
    private TableView newsTable;
    @FXML
    private TableColumn<_News, Integer> ncAuthorID;
    @FXML
    private TableColumn<_News, String> ncNews;
    @FXML
    private TableColumn<_News, String> ncText;
    @FXML
    private TableColumn<_News, Date> ncDate;
    @FXML
    private TableColumn<_News, Integer> ncViews;
    @FXML
    private TableColumn<_News, String> ncTegs;
    @FXML
    private TableColumn<_News, String> ncImageView;
    @FXML
    private TableColumn<_News, Integer> ncID;
    //*********Category tabs
    @FXML
    private TableView categoryTable;
    @FXML
    private TableColumn<_Category, Integer> categoryId;
    @FXML
    private TableColumn<_Category, String> categoryName;
    @FXML
    private TableColumn<_Category, String> categoryDescription;
    @FXML
    private TableColumn<_Category, String> categoryImage;
    //**********Dish tabs
    @FXML
    private TableView dishTable;
    @FXML
    private TableColumn<_Dish, Integer> dishId;
    @FXML
    private TableColumn<_Dish, Integer> dishCategoryId;
    @FXML
    private TableColumn<_Dish, String> dishName;
    @FXML
    private TableColumn<_Dish, String> dishDescription;
    @FXML
    private TableColumn<_Dish, Integer> dishAmount;
    @FXML
    private TableColumn<_Dish, Double> dishPrice;
    @FXML
    private TableColumn<_Dish, String> dishImage;
    @FXML
    private TableColumn<_Dish, String> dishReadyOrNot;
    @FXML
    private TableColumn<_Dish, String> dishSell;
    //********************Reservation tabs
    @FXML
    private TableView reservationTable;
    @FXML
    private TableColumn<_Reservation, Integer> reservationID;
    @FXML
    private TableColumn<_Reservation, Integer> reservationCafeID;
    @FXML
    private TableColumn<_Reservation, String> reservationClientName;
    @FXML
    private TableColumn<_Reservation, String> reservationClientPhone;
    @FXML
    private TableColumn<_Reservation, Date> reservationDate;
    @FXML
    private TableColumn<_Reservation, Time> reservationTime;
    @FXML
    private TableColumn<_Reservation, String> reservationStatus;
    @FXML
    private TableColumn<_Reservation, Integer> reservationAmountPeople;
    @FXML
    private TableColumn<_Reservation, String> reservationDescription;
    //**************************PREORDER TABS
    @FXML
    private TableView preorderTable;
    @FXML
    private TableColumn<_PreOrderTable, String> preorderDishName;
    @FXML
    private TableColumn<_PreOrderTable, Integer> preorderAmount;
    @FXML
    private TableColumn<_PreOrderTable, Double> preorderPrice;
    @FXML
    private TableColumn<_PreOrderTable, Integer> preOrderResID;
    @FXML
    private TableColumn<_PreOrderTable,Integer> preOrderID;
    //**************************REPORTS TABS
    @FXML
    private TableView repTable;
    @FXML
    private TableColumn<_Reports, Integer> repID;
    @FXML
    private TableColumn<_Reports, String> repAuthor;
    @FXML
    private TableColumn<_Reports, String> repText;
    @FXML
    private TableColumn<_Reports, Date> repDate;
    @FXML
    private TableColumn<_Reports, String> repMail;
    @FXML
    private TableColumn<_Reports, String> repPhone;
    @FXML
    private TableColumn<_Reports, String> repVision;
    //*******CafeCoordinate
    @FXML
    private TableView cafeCoordinateTable;
    @FXML
    private TableColumn<_CafeCoordinate, Integer> coordId;
    @FXML
    private TableColumn<_CafeCoordinate, String> coordAdrs;
    @FXML
    private TableColumn<_CafeCoordinate, String> coordPhone;
    @FXML
    private TableColumn<_CafeCoordinate, String> coordMail;

    private void cafeCoordinateInitialize() {
        CafeCoordinateList cl = new CafeCoordinateList(0);
        coordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coordAdrs.setCellValueFactory(new PropertyValueFactory<>("address"));
        coordPhone.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        coordMail.setCellValueFactory(new PropertyValueFactory<>("cafeemail"));
        cafeCoordinateTable.setItems(FXCollections.observableList(cl.getCafeCoordinate()));
    }

    //*******Position
    @FXML
    private TableView positionTable;
    @FXML
    private TableColumn<_Positions, Integer> posID;
    @FXML
    private TableColumn<_Positions, String> posName;
    @FXML
    private TableColumn<_Positions, Double> posSalary;

    private void positionInitialize() {
        PositionList pl = new PositionList(0);
        posID.setCellValueFactory(new PropertyValueFactory<>("id"));
        posName.setCellValueFactory(new PropertyValueFactory<>("name"));
        posSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        positionTable.setItems(FXCollections.observableList(pl.getPositionList()));
    }

    //*************Orders
    @FXML
    private TableView OrdersTable;
    @FXML
    private TableColumn<_Order, Integer> ordID;
    @FXML
    private TableColumn<_Order, Integer> ordEmployeeId;
    @FXML
    private TableColumn<_Order, Double> orderFullPrice;
    @FXML
    private TableColumn<_Order, String> ordDate;
    @FXML
    private TableColumn<_Order, String> ordCompliteORnot;

    private void ordersInitialize() {
        OrderList order = new OrderList(0);
        ordID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        orderFullPrice.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
        ordDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        ordCompliteORnot.setCellValueFactory(new PropertyValueFactory<>("compliteOrNot"));
        OrdersTable.setItems(FXCollections.observableList(order.getOrderList()));
    }
    //********OrdersList
    @FXML
    private TableView ordersOrderTable;
    @FXML
    private TableColumn<_OrderList, Integer> ordlistId;
    @FXML
    private TableColumn<_OrderList, Integer> ordlistOrdId;
    @FXML
    private TableColumn<_OrderList, String> ordlistDish;
    @FXML
    private TableColumn<_OrderList, Integer> ordlistAmount;
    @FXML
    private TableColumn<_OrderList, Double> ordlistPrice;

    private void orderListInitialize(OrdersOrderList order )
    {
        ordlistId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordlistOrdId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ordlistDish.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        ordlistAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ordlistPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ordersOrderTable.setItems(FXCollections.observableList(order.getOrdersOrderList()));
    }

    //**************Reports
    @FXML
    private BarChart barChartFirst;
    @FXML
    private LineChart lineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label labelPercent;
    @FXML
    private DatePicker dataPickerStart;
    @FXML
    private DatePicker dataPickerEnd;
    @FXML
    private void initialize() {
        labelPercent.setTextFill(Color.DARKORANGE);
        labelPercent.setStyle("-fx-font: 24 arial;");
        labelPercent.setVisible(false);
        Main.controllerMainItems.setBtnReinitializeAdmin(true);
        ReInit();
    }

    private void CategoryDishHandler() {
        categoryTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dishTable.getSelectionModel().clearSelection();
            }
        }));
        dishTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                categoryTable.getSelectionModel().clearSelection();
            }
        }));
    }
    private void OrderOrderListHandler() {
        ordersOrderTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                OrdersTable.getSelectionModel().clearSelection();
            }
        }));
        OrdersTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ordersOrderTable.getSelectionModel().clearSelection();
            }
        }));
    }

    private void EmployeeCafeeCoordinatePositionHandler() {
        cafeCoordinateTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tableEmployees.getSelectionModel().clearSelection();
                positionTable.getSelectionModel().clearSelection();
            }
        }));
        tableEmployees.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cafeCoordinateTable.getSelectionModel().clearSelection();
                positionTable.getSelectionModel().clearSelection();
            }
        }));
        positionTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tableEmployees.getSelectionModel().clearSelection();
                cafeCoordinateTable.getSelectionModel().clearSelection();
            }
        }));
    }
    private void reservationHandler()
    {
        reservationTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                preorderTable.getSelectionModel().clearSelection();
            }
        }));
        preorderTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                reservationTable.getSelectionModel().clearSelection();
            }
        }));
    }

    private void employeeInitialize() {
        EmployeeList el = new EmployeeList(0);
        id.setCellValueFactory(new PropertyValueFactory<LocalEmployee, Integer>("id"));
        positionID.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("positionID"));
        cafeID.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("cafeID"));
        name.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("lastname"));
        address.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("address"));
        birthdayDay.setCellValueFactory(new PropertyValueFactory<LocalEmployee, Date>("birthdayDay"));
        empLogin.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("elogin"));
        empPass.setCellValueFactory(new PropertyValueFactory<LocalEmployee, String>("epassword"));
        //записать обзервебл лист в таблицу
        tableEmployees.setItems(FXCollections.observableList(el.getEmployees()));
    }

    private void newsInitialize() {
        NewsList nl = new NewsList();
        ncID.setCellValueFactory(new PropertyValueFactory<_News, Integer>("id"));
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<_News, Integer>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<_News, String>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<_News, String>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<_News, Date>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<_News, Integer>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<_News, String>("tegs"));
        ncImageView.setCellValueFactory(new PropertyValueFactory<_News, String>("imageLink"));
        newsTable.setItems(FXCollections.observableList(nl.getNewsList()));
    }

    private void categoryInitialize() {
        CategoryList cl = new CategoryList();
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        categoryTable.setItems(FXCollections.observableList(cl.getCategoryLists()));
    }

    private void dishInitialize(DishList dishList) {
        dishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        dishCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dishDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        dishAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dishImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        dishReadyOrNot.setCellValueFactory(new PropertyValueFactory<>("readyORnot"));
        dishSell.setCellValueFactory(new PropertyValueFactory<>("sell"));
        if (dishList.getDishList() != null) {
            dishTable.setItems(FXCollections.observableList(dishList.getDishList()));
        } else {
            LazyAlert sa = new LazyAlert("Внимание", "В данной категории нет блюд", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    private void reservationInitialize() {
        ReservationList rl = new ReservationList();
        reservationID.setCellValueFactory(new PropertyValueFactory<>("id"));
        reservationCafeID.setCellValueFactory(new PropertyValueFactory<>("cafeId"));
        reservationClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        reservationClientPhone.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        reservationTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        reservationStatus.setCellValueFactory(new PropertyValueFactory<>("confirmORnot"));
        reservationAmountPeople.setCellValueFactory(new PropertyValueFactory<>("amountPeople"));
        reservationDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reservationTable.setItems(FXCollections.observableList(rl.getReservations()));
    }

    private void preorderInitialize(PreOrderList preOrderList) {
        preOrderResID.setCellValueFactory(new PropertyValueFactory<>("reservID"));
        preorderDishName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        preorderAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        preorderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        preOrderID.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (preOrderList.getPreorder() != null) {
            preorderTable.setItems(FXCollections.observableList(preOrderList.getPreorder()));
        } else {
            LazyAlert sa = new LazyAlert("Внимание", "В данном заказе нет предзаказа", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    private void reportsInitialize() {
        ReportsList rl = new ReportsList(0);
        repID.setCellValueFactory(new PropertyValueFactory<>("id"));
        repAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        repText.setCellValueFactory(new PropertyValueFactory<>("text"));
        repDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        repMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        repPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        repVision.setCellValueFactory(new PropertyValueFactory<>("vision"));
        repTable.setItems(FXCollections.observableList(rl.getReport()));
    }

    public void mouseReservationClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            _Reservation res = (_Reservation) reservationTable.getSelectionModel().getSelectedItem();
            preorderInitialize(new PreOrderList(res.getId()));
        }
    }

    public void mouseOrdersClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            _Order ord = (_Order) OrdersTable.getSelectionModel().getSelectedItem();
            orderListInitialize(new OrdersOrderList(ord.getId()));
        }
    }

    public void mouseCategoryClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            _Category cat = (_Category) categoryTable.getSelectionModel().getSelectedItem();
            dishInitialize(new DishList(cat.getId()));
        }
    }


    public void btnEventReservationDelete(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Reservation> res = reservationTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Reservation",res.get(0).getId());
                reservationInitialize();
            }
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_PreOrderTable> preor = preorderTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("preorder",preor.get(0).getId());
                reservationInitialize();
            }
            LazyAlert sa = new LazyAlert("Ошибка", null, "Удаление элемента предзаказа запрещена", Alert.AlertType.ERROR);
        }
        else {
            alertNullValue();
        }
    }

    public void btnEventEmployeeDelete(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Employee> tmp = tableEmployees.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Employee",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Positions> tmp = positionTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Positions",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_CafeCoordinate> tmp = cafeCoordinateTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("cafecoordinate",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventReviewDelete(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Reports> rep = repTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Reports",rep.get(0).getId());
                reportsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventCategoryDishDelete(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Category> cat = categoryTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Category",cat.get(0).getId());
                categoryInitialize();
            }
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_Dish> dish = dishTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Dish",dish.get(0).getId());
                dishInitialize(new DishList(0));
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventNewsDelete(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<_News> news = newsTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_News",news.get(0).getId());
                newsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    private void alertNullValue() {
        LazyAlert sa = new LazyAlert("Ошибка", null, "Выберите элемент таблицы для добавления, правки или удаления", Alert.AlertType.WARNING);
    }
    public void ReInit()
    {
        employeeInitialize();
        newsInitialize();
        categoryInitialize();
        dishInitialize(new DishList(0));
        reservationInitialize();
        preorderInitialize(new PreOrderList(0));
        reportsInitialize();
        cafeCoordinateInitialize();
        positionInitialize();
        CategoryDishHandler();
        EmployeeCafeeCoordinatePositionHandler();
        reservationHandler();
        ordersInitialize();
        orderListInitialize(new OrdersOrderList(0));
        OrderOrderListHandler();
    }

    public void periodMouseClick(Event event) {
        clear();
        if(lineChartInit(String.valueOf(dataPickerStart.getValue()),String.valueOf(dataPickerEnd.getValue())))
        {
            barChartInit(String.valueOf(dataPickerStart.getValue()),String.valueOf(dataPickerEnd.getValue()));
            pieChartInit(String.valueOf(dataPickerStart.getValue()),String.valueOf(dataPickerEnd.getValue()));
        }else
        {
            LazyAlert sa = new LazyAlert("Ошибка","За заданый период ничего не найдено","Введите другие данные", Alert.AlertType.INFORMATION);
        }
    }

    public void monthMouseClick(Event event) {
        clear();
        if(lineChartInit(null,null))
        {
            barChartInit(null,null);
            pieChartInit(null,null);
        }else {
            LazyAlert.SystemError();
        }
    }
    private void clear()
    {
        lineChart.getData().clear();
        barChartFirst.getData().clear();
        pieChart.getData().clear();
    }
    private boolean pieChartInit(String dateStart, String dateEnd) {
        ArrayList<_GraphReport> tmp = null;
        tmp = ServerCommands.getReportsGraph("pie", dateStart, dateEnd);
        if (tmp == null) {
            return false;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < tmp.size(); i++) {
            pieChartData.add(new PieChart.Data(tmp.get(i).getName(), tmp.get(i).getAmount()));
        }
        if (dateStart == null || dateEnd == null) {
            pieChart.setTitle("Количество проданых продуктов за месяц");
        } else {
            pieChart.setTitle("Количество проданых продуктов за период с "+dateStart+" по "+dateEnd);
        }
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setData(pieChartData);
        return true;
    }

    private boolean lineChartInit(String dateStart, String dateEnd)
    {
        ArrayList<_GraphReport> tmp = null;
        tmp = ServerCommands.getReportsGraph("line",dateStart,dateEnd);
        if(tmp == null)
        {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        final NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        xAxis.setLabel("Дата");
        yAxis.setLabel("Количество");

        XYChart.Series series = new XYChart.Series();
        for (int i = 0 ; i <tmp.size();i++)
        {
            series.getData().add(new XYChart.Data(tmp.get(i).getName(), tmp.get(i).getAmount()));
        }
        series.setName("За текущий месяц");
        if (dateStart == null || dateEnd == null) {
            lineChart.setTitle("График продаж за месяц");
        } else {
            lineChart.setTitle("График продаж за период c "+dateStart+" по "+dateEnd);
        }
        lineChart.getData().add(series);
        return true;
    }

    private boolean barChartInit(String dateStart, String dateEnd)
    {
        ArrayList<_GraphReport>  tmp = null;
        tmp = ServerCommands.getReportsGraph("bar",dateStart,dateEnd);
        if(tmp == null)
        {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) barChartFirst.getXAxis();
        final NumberAxis yAxis = (NumberAxis) barChartFirst.getYAxis();
        xAxis.setLabel("Продукт");
        yAxis.setLabel("Количество");
        for(int i=0;i<tmp.size();i++)
        {
            XYChart.Series series = new XYChart.Series();
            series.setName(tmp.get(i).getName());
            series.getData().add(new XYChart.Data("Значение", tmp.get(i).getAmount()));
            barChartFirst.getData().add(series);
        }
        if (dateStart == null || dateEnd == null)  {
            barChartFirst.setTitle("Продажы за месяц топ продуктов");
        } else {
            barChartFirst.setTitle("Продажы за период с "+dateStart+" по "+dateEnd+" топ продуктов");
        }
        return true;
    }

    public void pieChartMouseEntered(Event event) {
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            labelPercent.setVisible(true);
                            labelPercent.setText(String.valueOf(data.getPieValue()) + " шт");
                        }
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            labelPercent.setVisible(false);
                        }
                    });
        }
    }

    public void MouseClickBtnAddDishCategory(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            _Category cat = (_Category) categoryTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Категорию", "category", "insert",cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            _Dish dish = (_Dish) dishTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить блюдо", "dish", "insert",dish.getId());
        } else {
            alertNullValue();
        }
    }

    public void MouseClickBtnEditDishCategory(Event event) {

        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            _Category cat = (_Category) categoryTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Категорию", "category", "update",cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            _Dish dish = (_Dish) dishTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить блюдо", "dish", "update",dish.getId());
        }else
        {
            alertNullValue();
        }
    }
    public void NewsEditBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            _News news = (_News) newsTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Новость", "news", "update",news.getId());
        } else
        {
            alertNullValue();
        }
    }

    public void NewsAddBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            _News news = (_News) newsTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Новость", "news", "insert",news.getId());
        } else
        {
            alertNullValue();
        }
    }

    public void ReservationPreOrderEditBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            _Reservation tmp = (_Reservation) reservationTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить Резерв", "reservation", "update", tmp.getId());
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            _PreOrderTable tmp = (_PreOrderTable) preorderTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить Предзаказ", "preorder", "update", tmp.getId());
        }
        else {
            alertNullValue();
        }
    }

    public void ReservationPreOrderAddBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            _Reservation tmp = (_Reservation) reservationTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Резерв", "reservation", "insert", tmp.getId());
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            _PreOrderTable tmp = (_PreOrderTable) preorderTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Предзаказ", "preorder", "insert", tmp.getId());
        }
        else {
            alertNullValue();
        }
    }

    public void EmployeeAddBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            _Employee tmp = (_Employee) tableEmployees.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить сотрудника", "employees", "insert", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            _Positions tmp = (_Positions) positionTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить вакансию", "positions", "insert", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            _CafeCoordinate tmp = (_CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить новое кафе", "cafecoordinate", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void EmployeeEditBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            _Employee tmp = (_Employee) tableEmployees.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить сотрудника", "employees", "update", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            _Positions tmp = (_Positions) positionTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить вакансию", "positions", "update", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            _CafeCoordinate tmp = (_CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить кафе", "cafecoordinate", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewAddBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            _Reports tmp = (_Reports) repTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить отзыв", "reports", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewEditBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            _Reports tmp = (_Reports) repTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить отзыв", "reports", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }
}

