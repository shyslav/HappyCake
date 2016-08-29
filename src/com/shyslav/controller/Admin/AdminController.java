package com.shyslav.controller.Admin;

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
    private TableColumn<jEmployees, Integer> id;
    @FXML
    private TableColumn<jEmployees, String> positionID;
    @FXML
    private TableColumn<jEmployees, String> cafeID;
    @FXML
    private TableColumn<jEmployees, String> name;
    @FXML
    private TableColumn<jEmployees, String> lastname;
    @FXML
    private TableColumn<jEmployees, String> address;
    @FXML
    private TableColumn<jEmployees, Date> birthdayDay;
    @FXML
    private TableColumn<jEmployees, String> empLogin;
    @FXML
    private TableColumn<jEmployees, String> empPass;
    //*******News tabs
    @FXML
    private TableView newsTable;
    @FXML
    private TableColumn<news, Integer> ncAuthorID;
    @FXML
    private TableColumn<news, String> ncNews;
    @FXML
    private TableColumn<news, String> ncText;
    @FXML
    private TableColumn<news, Date> ncDate;
    @FXML
    private TableColumn<news, Integer> ncViews;
    @FXML
    private TableColumn<news, String> ncTegs;
    @FXML
    private TableColumn<news, String> ncImageView;
    @FXML
    private TableColumn<news, Integer> ncID;
    //*********Category tabs
    @FXML
    private TableView categoryTable;
    @FXML
    private TableColumn<category, Integer> categoryId;
    @FXML
    private TableColumn<category, String> categoryName;
    @FXML
    private TableColumn<category, String> categoryDescription;
    @FXML
    private TableColumn<category, String> categoryImage;
    //**********Dish tabs
    @FXML
    private TableView dishTable;
    @FXML
    private TableColumn<dish, Integer> dishId;
    @FXML
    private TableColumn<dish, Integer> dishCategoryId;
    @FXML
    private TableColumn<dish, String> dishName;
    @FXML
    private TableColumn<dish, String> dishDescription;
    @FXML
    private TableColumn<dish, Integer> dishAmount;
    @FXML
    private TableColumn<dish, Double> dishPrice;
    @FXML
    private TableColumn<dish, String> dishImage;
    @FXML
    private TableColumn<dish, String> dishReadyOrNot;
    @FXML
    private TableColumn<dish, String> dishSell;
    //********************Reservation tabs
    @FXML
    private TableView reservationTable;
    @FXML
    private TableColumn<reservation, Integer> reservationID;
    @FXML
    private TableColumn<reservation, Integer> reservationCafeID;
    @FXML
    private TableColumn<reservation, String> reservationClientName;
    @FXML
    private TableColumn<reservation, String> reservationClientPhone;
    @FXML
    private TableColumn<reservation, Date> reservationDate;
    @FXML
    private TableColumn<reservation, Time> reservationTime;
    @FXML
    private TableColumn<reservation, String> reservationStatus;
    @FXML
    private TableColumn<reservation, Integer> reservationAmountPeople;
    @FXML
    private TableColumn<reservation, String> reservationDescription;
    //**************************PREORDER TABS
    @FXML
    private TableView preorderTable;
    @FXML
    private TableColumn<preOrderTable, String> preorderDishName;
    @FXML
    private TableColumn<preOrderTable, Integer> preorderAmount;
    @FXML
    private TableColumn<preOrderTable, Double> preorderPrice;
    @FXML
    private TableColumn<preOrderTable, Integer> preOrderResID;
    @FXML
    private TableColumn<preOrderTable,Integer> preOrderID;
    //**************************REPORTS TABS
    @FXML
    private TableView repTable;
    @FXML
    private TableColumn<reports, Integer> repID;
    @FXML
    private TableColumn<reports, String> repAuthor;
    @FXML
    private TableColumn<reports, String> repText;
    @FXML
    private TableColumn<reports, Date> repDate;
    @FXML
    private TableColumn<reports, String> repMail;
    @FXML
    private TableColumn<reports, String> repPhone;
    @FXML
    private TableColumn<reports, String> repVision;
    //*******CafeCoordinate
    @FXML
    private TableView cafeCoordinateTable;
    @FXML
    private TableColumn<cafeCoordinate, Integer> coordId;
    @FXML
    private TableColumn<cafeCoordinate, String> coordAdrs;
    @FXML
    private TableColumn<cafeCoordinate, String> coordPhone;
    @FXML
    private TableColumn<cafeCoordinate, String> coordMail;

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
    private TableColumn<position, Integer> posID;
    @FXML
    private TableColumn<position, String> posName;
    @FXML
    private TableColumn<position, Double> posSalary;

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
    private TableColumn<orders, Integer> ordID;
    @FXML
    private TableColumn<orders, Integer> ordEmployeeId;
    @FXML
    private TableColumn<orders, Double> orderFullPrice;
    @FXML
    private TableColumn<orders, String> ordDate;
    @FXML
    private TableColumn<orders, String> ordCompliteORnot;

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
    private TableColumn<orderList, Integer> ordlistId;
    @FXML
    private TableColumn<orderList, Integer> ordlistOrdId;
    @FXML
    private TableColumn<orderList, String> ordlistDish;
    @FXML
    private TableColumn<orderList, Integer> ordlistAmount;
    @FXML
    private TableColumn<orderList, Double> ordlistPrice;

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
        id.setCellValueFactory(new PropertyValueFactory<jEmployees, Integer>("id"));
        positionID.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("positionID"));
        cafeID.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("cafeID"));
        name.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("lastname"));
        address.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("address"));
        birthdayDay.setCellValueFactory(new PropertyValueFactory<jEmployees, Date>("birthdayDay"));
        empLogin.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("elogin"));
        empPass.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("epassword"));
        //записать обзервебл лист в таблицу
        tableEmployees.setItems(FXCollections.observableList(el.getEmployees()));
    }

    private void newsInitialize() {
        NewsList nl = new NewsList();
        ncID.setCellValueFactory(new PropertyValueFactory<news, Integer>("id"));
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<news, Integer>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<news, String>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<news, String>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<news, Date>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<news, Integer>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<news, String>("tegs"));
        ncImageView.setCellValueFactory(new PropertyValueFactory<news, String>("imageLink"));
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
            reservation res = (reservation) reservationTable.getSelectionModel().getSelectedItem();
            preorderInitialize(new PreOrderList(res.getId()));
        }
    }

    public void mouseOrdersClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            orders ord = (orders) OrdersTable.getSelectionModel().getSelectedItem();
            orderListInitialize(new OrdersOrderList(ord.getId()));
        }
    }

    public void mouseCategoryClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            category cat = (category) categoryTable.getSelectionModel().getSelectedItem();
            dishInitialize(new DishList(cat.getId()));
        }
    }


    public void btnEventReservationDelete(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<reservation> res = reservationTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("reservation",res.get(0).getId());
                reservationInitialize();
            }
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<preOrderTable> preor = preorderTable.getSelectionModel().getSelectedItems();
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
            ObservableList<employees> tmp = tableEmployees.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("employees",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<positions> tmp = positionTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("positions",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<cafeCoordinate> tmp = cafeCoordinateTable.getSelectionModel().getSelectedItems();
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
            ObservableList<reports> rep = repTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("reports",rep.get(0).getId());
                reportsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventCategoryDishDelete(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<category> cat = categoryTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("category",cat.get(0).getId());
                categoryInitialize();
            }
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<dish> dish = dishTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("dish",dish.get(0).getId());
                dishInitialize(new DishList(0));
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventNewsDelete(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<news> news = newsTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("news",news.get(0).getId());
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
        ArrayList<ReportsGraph> tmp = null;
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
        ArrayList<ReportsGraph> tmp = null;
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
        ArrayList<ReportsGraph>  tmp = null;
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
            category cat = (category) categoryTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Категорию", "category", "insert",cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            dish dish = (dish) dishTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить блюдо", "dish", "insert",dish.getId());
        } else {
            alertNullValue();
        }
    }

    public void MouseClickBtnEditDishCategory(Event event) {

        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            category cat = (category) categoryTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Категорию", "category", "update",cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            dish dish = (dish) dishTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить блюдо", "dish", "update",dish.getId());
        }else
        {
            alertNullValue();
        }
    }
    public void NewsEditBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            news news = (news) newsTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Новость", "news", "update",news.getId());
        } else
        {
            alertNullValue();
        }
    }

    public void NewsAddBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            news news = (news) newsTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Новость", "news", "insert",news.getId());
        } else
        {
            alertNullValue();
        }
    }

    public void ReservationPreOrderEditBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            reservation tmp = (reservation) reservationTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить Резерв", "reservation", "update", tmp.getId());
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            preOrderTable tmp = (preOrderTable) preorderTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить Предзаказ", "preorder", "update", tmp.getId());
        }
        else {
            alertNullValue();
        }
    }

    public void ReservationPreOrderAddBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            reservation tmp = (reservation) reservationTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Резерв", "reservation", "insert", tmp.getId());
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            preOrderTable tmp = (preOrderTable) preorderTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить Предзаказ", "preorder", "insert", tmp.getId());
        }
        else {
            alertNullValue();
        }
    }

    public void EmployeeAddBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            employees tmp = (employees) tableEmployees.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить сотрудника", "employees", "insert", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            positions tmp = (positions) positionTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить вакансию", "positions", "insert", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            cafeCoordinate tmp = (cafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить новое кафе", "cafecoordinate", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void EmployeeEditBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            employees tmp = (employees) tableEmployees.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить сотрудника", "employees", "update", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            positions tmp = (positions) positionTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить вакансию", "positions", "update", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            cafeCoordinate tmp = (cafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить кафе", "cafecoordinate", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewAddBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            reports tmp = (reports) repTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Добавить отзыв", "reports", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewEditBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            reports tmp = (reports) repTable.getSelectionModel().getSelectedItem();
            Main.updateInsertDialog("Изменить отзыв", "reports", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }
}

