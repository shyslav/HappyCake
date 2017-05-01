package com.shyslav.controller.Admin;

import appmodels.localmodels.LocalEmployee;
import com.happycake.sitemodels.*;
import com.happycake.sitemodels.CafeCoordinateList;
import com.happycake.sitemodels.OrderList;
import com.shyslav.controller.alert.LazyConfirmDialog;
import com.shyslav.controller.alert.LazyJavaFXAlert;
import appmodels.*;
import com.shyslav.server.ServerCommands;
import com.shyslav.start.StartApplication;
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
 * @author Shyshkin Vladyslav on 28.03.2016.
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
    private TableColumn<News, Integer> ncAuthorID;
    @FXML
    private TableColumn<News, String> ncNews;
    @FXML
    private TableColumn<News, String> ncText;
    @FXML
    private TableColumn<News, Date> ncDate;
    @FXML
    private TableColumn<News, Integer> ncViews;
    @FXML
    private TableColumn<News, String> ncTegs;
    @FXML
    private TableColumn<News, String> ncImageView;
    @FXML
    private TableColumn<News, Integer> ncID;
    //*********Category tabs
    @FXML
    private TableView categoryTable;
    @FXML
    private TableColumn<Category, Integer> categoryId;
    @FXML
    private TableColumn<Category, String> categoryName;
    @FXML
    private TableColumn<Category, String> categoryDescription;
    @FXML
    private TableColumn<Category, String> categoryImage;
    //**********Dish tabs
    @FXML
    private TableView dishTable;
    @FXML
    private TableColumn<Dish, Integer> dishId;
    @FXML
    private TableColumn<Dish, Integer> dishCategoryId;
    @FXML
    private TableColumn<Dish, String> dishName;
    @FXML
    private TableColumn<Dish, String> dishDescription;
    @FXML
    private TableColumn<Dish, Integer> dishAmount;
    @FXML
    private TableColumn<Dish, Double> dishPrice;
    @FXML
    private TableColumn<Dish, String> dishImage;
    @FXML
    private TableColumn<Dish, String> dishReadyOrNot;
    @FXML
    private TableColumn<Dish, String> dishSell;
    //********************Reservation tabs
    @FXML
    private TableView reservationTable;
    @FXML
    private TableColumn<Reservation, Integer> reservationID;
    @FXML
    private TableColumn<Reservation, Integer> reservationCafeID;
    @FXML
    private TableColumn<Reservation, String> reservationClientName;
    @FXML
    private TableColumn<Reservation, String> reservationClientPhone;
    @FXML
    private TableColumn<Reservation, Date> reservationDate;
    @FXML
    private TableColumn<Reservation, Time> reservationTime;
    @FXML
    private TableColumn<Reservation, String> reservationStatus;
    @FXML
    private TableColumn<Reservation, Integer> reservationAmountPeople;
    @FXML
    private TableColumn<Reservation, String> reservationDescription;
    //**************************PREORDER TABS
    @FXML
    private TableView preorderTable;
    @FXML
    private TableColumn<PreOrder, String> preorderDishName;
    @FXML
    private TableColumn<PreOrder, Integer> preorderAmount;
    @FXML
    private TableColumn<PreOrder, Double> preorderPrice;
    @FXML
    private TableColumn<PreOrder, Integer> preOrderResID;
    @FXML
    private TableColumn<PreOrder, Integer> preOrderID;
    //**************************REPORTS TABS
    @FXML
    private TableView repTable;
    @FXML
    private TableColumn<Reports, Integer> repID;
    @FXML
    private TableColumn<Reports, String> repAuthor;
    @FXML
    private TableColumn<Reports, String> repText;
    @FXML
    private TableColumn<Reports, Date> repDate;
    @FXML
    private TableColumn<Reports, String> repMail;
    @FXML
    private TableColumn<Reports, String> repPhone;
    @FXML
    private TableColumn<Reports, String> repVision;
    //*******CafeCoordinate
    @FXML
    private TableView cafeCoordinateTable;
    @FXML
    private TableColumn<CafeCoordinate, Integer> coordId;
    @FXML
    private TableColumn<CafeCoordinate, String> coordAdrs;
    @FXML
    private TableColumn<CafeCoordinate, String> coordPhone;
    @FXML
    private TableColumn<CafeCoordinate, String> coordMail;

    private void cafeCoordinateInitialize() {
        CafeCoordinateList cl = StartApplication.userEntity.getUserBean().getCafeCoordinatesList();
        coordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coordAdrs.setCellValueFactory(new PropertyValueFactory<>("address"));
        coordPhone.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        coordMail.setCellValueFactory(new PropertyValueFactory<>("cafeemail"));
        cafeCoordinateTable.setItems(FXCollections.observableList(cl));
    }

    //*******Position
    @FXML
    private TableView positionTable;
    @FXML
    private TableColumn<Position, Integer> posID;
    @FXML
    private TableColumn<Position, String> posName;
    @FXML
    private TableColumn<Position, Double> posSalary;

    private void positionInitialize() {
        PositionsList pl = StartApplication.userEntity.getUserBean().getPositionsList();
        posID.setCellValueFactory(new PropertyValueFactory<>("id"));
        posName.setCellValueFactory(new PropertyValueFactory<>("name"));
        posSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        positionTable.setItems(FXCollections.observableList(pl));
    }

    //*************Orders
    @FXML
    private TableView OrdersTable;
    @FXML
    private TableColumn<Order, Integer> ordID;
    @FXML
    private TableColumn<Order, Integer> ordEmployeeId;
    @FXML
    private TableColumn<Order, Double> orderFullPrice;
    @FXML
    private TableColumn<Order, String> ordDate;
    @FXML
    private TableColumn<Order, String> ordCompliteORnot;

    private void ordersInitialize() {
        OrderList order = StartApplication.userEntity.getUserBean().getOrderList();
        ordID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        orderFullPrice.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
        ordDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        ordCompliteORnot.setCellValueFactory(new PropertyValueFactory<>("compliteOrNot"));
        OrdersTable.setItems(FXCollections.observableList(order));
    }

    //********OrdersList
    @FXML
    private TableView ordersOrderTable;
    @FXML
    private TableColumn<OrderDetails, Integer> ordlistId;
    @FXML
    private TableColumn<OrderDetails, Integer> ordlistOrdId;
    @FXML
    private TableColumn<OrderDetails, String> ordlistDish;
    @FXML
    private TableColumn<OrderDetails, Integer> ordlistAmount;
    @FXML
    private TableColumn<OrderDetails, Double> ordlistPrice;

    private void orderListInitialize(OrderDetailsList details) {
        ordlistId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordlistOrdId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ordlistDish.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        ordlistAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ordlistPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ordersOrderTable.setItems(FXCollections.observableList(details));
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
        StartApplication.userEntity.getUserBean().waitLoad();
        labelPercent.setTextFill(Color.DARKORANGE);
        labelPercent.setStyle("-fx-font: 24 arial;");
        labelPercent.setVisible(false);
        StartApplication.controllerMainItems.setBtnReinitializeAdmin(true);
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

    private void reservationHandler() {
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
        EmployeesList el = StartApplication.userEntity.getUserBean().getEmployeesList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        positionID.setCellValueFactory(new PropertyValueFactory<>("positionID"));
        cafeID.setCellValueFactory(new PropertyValueFactory<>("cafeID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        birthdayDay.setCellValueFactory(new PropertyValueFactory<>("birthdayDay"));
        empLogin.setCellValueFactory(new PropertyValueFactory<>("elogin"));
        empPass.setCellValueFactory(new PropertyValueFactory<>("epassword"));
        //записать обзервебл лист в таблицу
        tableEmployees.setItems(FXCollections.observableList(el));
    }

    private void newsInitialize() {
        NewsList nl = StartApplication.userEntity.getUserBean().getNewsList();
        ncID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<>("tegs"));
        ncImageView.setCellValueFactory(new PropertyValueFactory<>("imageLink"));
        newsTable.setItems(FXCollections.observableList(nl));
    }

    private void categoryInitialize() {
        CategoriesList cl = StartApplication.userEntity.getUserBean().getCategoriesList();
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        categoryTable.setItems(FXCollections.observableList(cl));
    }

    private void dishInitialize(DishesList dishList) {
        dishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        dishCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dishDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        dishAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dishImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        dishReadyOrNot.setCellValueFactory(new PropertyValueFactory<>("readyORnot"));
        dishSell.setCellValueFactory(new PropertyValueFactory<>("discount"));
        if (dishList != null) {
            dishTable.setItems(FXCollections.observableList(dishList));
        } else {
            LazyJavaFXAlert.alert("Внимание", "В данной категории нет блюд", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    private void reservationInitialize() {
        ReservationList rl = StartApplication.userEntity.getUserBean().getReservationList();
        reservationID.setCellValueFactory(new PropertyValueFactory<>("id"));
        reservationCafeID.setCellValueFactory(new PropertyValueFactory<>("cafeId"));
        reservationClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        reservationClientPhone.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        reservationTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        reservationStatus.setCellValueFactory(new PropertyValueFactory<>("confirmORnot"));
        reservationAmountPeople.setCellValueFactory(new PropertyValueFactory<>("amountPeople"));
        reservationDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reservationTable.setItems(FXCollections.observableList(rl));
    }

    private void preorderInitialize(PreOrderList preOrderList) {
        preOrderResID.setCellValueFactory(new PropertyValueFactory<>("reservID"));
        preorderDishName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        preorderAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        preorderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        preOrderID.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (preOrderList != null) {
            preorderTable.setItems(FXCollections.observableList(preOrderList));
        } else {
            LazyJavaFXAlert.alert("Внимание", "В данном заказе нет предзаказа", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    private void reportsInitialize() {
        ReportsList rl = StartApplication.userEntity.getUserBean().getReportsList();
        repID.setCellValueFactory(new PropertyValueFactory<>("id"));
        repAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        repText.setCellValueFactory(new PropertyValueFactory<>("text"));
        repDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        repMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        repPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        repVision.setCellValueFactory(new PropertyValueFactory<>("vision"));
        repTable.setItems(FXCollections.observableList(rl));
    }

    public void mouseReservationClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Reservation res = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            //TODO переделать по аналогу orders
            preorderInitialize(StartApplication.userEntity.getUserBean().getPreOrderList().getByOrderID(res.getId()));
        }
    }

    public void mouseOrdersClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Order ord = (Order) OrdersTable.getSelectionModel().getSelectedItem();
            orderListInitialize(ord.getOrderDetails());
        }
    }

    public void mouseCategoryClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            //TODO нужно переделать по аналогу orders
            dishInitialize(StartApplication.userEntity.getUserBean().getDishesList().getByCategoryId(cat.getId()));
        }
    }


    public void btnEventReservationDelete(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Reservation> res = reservationTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("Reservation", res.get(0).getId());
                reservationInitialize();
            }
        } else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<PreOrder> preor = preorderTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("preorder", preor.get(0).getId());
                reservationInitialize();
            }
            LazyJavaFXAlert.alert("Ошибка", null, "Удаление элемента предзаказа запрещена", Alert.AlertType.ERROR);
        } else {
            alertNullValue();
        }
    }

    public void btnEventEmployeeDelete(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Employees> tmp = tableEmployees.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("_Employee", tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Position> tmp = positionTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("Position", tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<CafeCoordinate> tmp = cafeCoordinateTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("cafecoordinate", tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventReviewDelete(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Reports> rep = repTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("Reports", rep.get(0).getId());
                reportsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventCategoryDishDelete(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Category> cat = categoryTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("Category", cat.get(0).getId());
                categoryInitialize();
            }
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<Dish> dish = dishTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("Dish", dish.get(0).getId());
                dishInitialize(new DishesList());
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventNewsDelete(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<News> news = newsTable.getSelectionModel().getSelectedItems();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                ServerCommands.delete("News", news.get(0).getId());
                newsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    private void alertNullValue() {
        LazyJavaFXAlert.alert("Ошибка", null, "Выберите элемент таблицы для добавления, правки или удаления", Alert.AlertType.WARNING);
    }

    public void ReInit() {
        employeeInitialize();
        newsInitialize();
        categoryInitialize();
        dishInitialize(new DishesList());
        reservationInitialize();
        preorderInitialize(new PreOrderList());
        reportsInitialize();
        cafeCoordinateInitialize();
        positionInitialize();
        CategoryDishHandler();
        EmployeeCafeeCoordinatePositionHandler();
        reservationHandler();
        ordersInitialize();
        orderListInitialize(new OrderDetailsList());
        OrderOrderListHandler();
    }

    public void periodMouseClick(Event event) {
        clear();
        if (lineChartInit(String.valueOf(dataPickerStart.getValue()), String.valueOf(dataPickerEnd.getValue()))) {
            barChartInit(String.valueOf(dataPickerStart.getValue()), String.valueOf(dataPickerEnd.getValue()));
            pieChartInit(String.valueOf(dataPickerStart.getValue()), String.valueOf(dataPickerEnd.getValue()));
        } else {
            LazyJavaFXAlert.alert("Ошибка", "За заданый период ничего не найдено", "Введите другие данные", Alert.AlertType.INFORMATION);
        }
    }

    public void monthMouseClick(Event event) {
        clear();
        if (lineChartInit(null, null)) {
            barChartInit(null, null);
            pieChartInit(null, null);
        } else {
            LazyJavaFXAlert.systemError();
        }
    }

    private void clear() {
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
            pieChart.setTitle("Количество проданых продуктов за период с " + dateStart + " по " + dateEnd);
        }
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setData(pieChartData);
        return true;
    }

    private boolean lineChartInit(String dateStart, String dateEnd) {
        ArrayList<_GraphReport> tmp = null;
        tmp = ServerCommands.getReportsGraph("line", dateStart, dateEnd);
        if (tmp == null) {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        final NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        xAxis.setLabel("Дата");
        yAxis.setLabel("Количество");

        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < tmp.size(); i++) {
            series.getData().add(new XYChart.Data(tmp.get(i).getName(), tmp.get(i).getAmount()));
        }
        series.setName("За текущий месяц");
        if (dateStart == null || dateEnd == null) {
            lineChart.setTitle("График продаж за месяц");
        } else {
            lineChart.setTitle("График продаж за период c " + dateStart + " по " + dateEnd);
        }
        lineChart.getData().add(series);
        return true;
    }

    private boolean barChartInit(String dateStart, String dateEnd) {
        ArrayList<_GraphReport> tmp = null;
        tmp = ServerCommands.getReportsGraph("bar", dateStart, dateEnd);
        if (tmp == null) {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) barChartFirst.getXAxis();
        final NumberAxis yAxis = (NumberAxis) barChartFirst.getYAxis();
        xAxis.setLabel("Продукт");
        yAxis.setLabel("Количество");
        for (int i = 0; i < tmp.size(); i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(tmp.get(i).getName());
            series.getData().add(new XYChart.Data("Значение", tmp.get(i).getAmount()));
            barChartFirst.getData().add(series);
        }
        if (dateStart == null || dateEnd == null) {
            barChartFirst.setTitle("Продажы за месяц топ продуктов");
        } else {
            barChartFirst.setTitle("Продажы за период с " + dateStart + " по " + dateEnd + " топ продуктов");
        }
        return true;
    }

    public void pieChartMouseEntered(Event event) {
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            labelPercent.setVisible(true);
                            labelPercent.setText(String.valueOf(data.getPieValue()) + " шт");
                        }
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            labelPercent.setVisible(false);
                        }
                    });
        }
    }

    public void MouseClickBtnAddDishCategory(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Категорию", "category", "insert", cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить блюдо", "dish", "insert", dish.getId());
        } else {
            alertNullValue();
        }
    }

    public void MouseClickBtnEditDishCategory(Event event) {

        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Категорию", "category", "update", cat.getId());
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить блюдо", "dish", "update", dish.getId());
        } else {
            alertNullValue();
        }
    }

    public void NewsEditBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Новость", "news", "update", news.getId());
        } else {
            alertNullValue();
        }
    }

    public void NewsAddBtn(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Новость", "news", "insert", news.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReservationPreOrderEditBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            Reservation tmp = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить Резерв", "reservation", "update", tmp.getId());
        } else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            PreOrder tmp = (PreOrder) preorderTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить Предзаказ", "preorder", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReservationPreOrderAddBtn(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            Reservation tmp = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Резерв", "reservation", "insert", tmp.getId());
        } else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            PreOrder tmp = (PreOrder) preorderTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить Предзаказ", "preorder", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void EmployeeAddBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            Employees tmp = (Employees) tableEmployees.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить сотрудника", "employees", "insert", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            Position tmp = (Position) positionTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить вакансию", "positions", "insert", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            CafeCoordinate tmp = (CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить новое кафе", "cafecoordinate", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void EmployeeEditBtn(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            Employees tmp = (Employees) tableEmployees.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить сотрудника", "employees", "update", tmp.getId());
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            Position tmp = (Position) positionTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить вакансию", "positions", "update", tmp.getId());
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            CafeCoordinate tmp = (CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить кафе", "cafecoordinate", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewAddBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            Reports tmp = (Reports) repTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Добавить отзыв", "reports", "insert", tmp.getId());
        } else {
            alertNullValue();
        }
    }

    public void ReviewEditBtn(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            Reports tmp = (Reports) repTable.getSelectionModel().getSelectedItem();
            StartApplication.updateInsertDialog("Изменить отзыв", "reports", "update", tmp.getId());
        } else {
            alertNullValue();
        }
    }
}

