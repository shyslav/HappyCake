package com.shyslav.controller.admin;

import appmodels.GraphReport;
import appmodels.GraphReportList;
import appmodels.localmodels.LocalEmployee;
import com.happycake.sitemodels.*;
import com.shyslav.UserBean;
import com.shyslav.controller.alert.LazyConfirmDialog;
import com.shyslav.controller.alert.LazyJavaFXAlert;
import com.shyslav.defaults.ErrorCodes;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartApplication;
import com.shyslav.utils.LazyCalendar;
import com.shyslav.utils.LazyDate;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Shyshkin Vladyslav on 28.03.2016.
 */
@SuppressWarnings("unused")
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
    private TableColumn<Dish, Bool> needCook;
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

    /**
     * Initialize cafe coordinates table
     */
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

    /**
     * Initialize position
     */
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

    /**
     * Initialize orders
     */
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

    /**
     * Initialize order details table
     *
     * @param details order details
     */
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

    /**
     * Date format
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private void initialize() {
        StartApplication.userEntity.getUserBean().waitLoad();
        labelPercent.setTextFill(Color.DARKORANGE);
        labelPercent.setStyle("-fx-font: 24 arial;");
        labelPercent.setVisible(false);
        StartApplication.controllerMainItems.setBtnReinitializeAdmin(true);
        ReInit();
    }

    /**
     * Select category and dish handler
     * This handler disable selected elements in other table in one tab in tabpane
     */
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

    /**
     * Select order and order details handler
     * This handler disable selected elements in other table in one tab in tabpane
     */
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

    /**
     * Select employee, cafe coordinate and position handler
     * This handler disable selected elements in other table in one tab in tabpane
     */
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

    /**
     * Select reservation and preorder handler
     * This handler disable selected elements in other table in one tab in tabpane
     */
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

    /**
     * Initialize employee table
     */
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
        tableEmployees.setItems(FXCollections.observableList(el));
    }

    /**
     * Initialize news table
     */
    private void newsInitialize() {
        NewsList nl = StartApplication.userEntity.getUserBean().getNewsList();
        ncID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<>("tags"));
        ncImageView.setCellValueFactory(new PropertyValueFactory<>("imageLink"));
        newsTable.setItems(FXCollections.observableList(nl));
    }

    /**
     * Initialize category table
     */
    private void categoryInitialize() {
        CategoriesList cl = StartApplication.userEntity.getUserBean().getCategoriesList();
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        categoryTable.setItems(FXCollections.observableList(cl));
    }

    /**
     * Initialize dishes table for category
     *
     * @param dishList dish for category
     */
    private void dishInitialize(DishesList dishList) {
        dishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        dishCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dishDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        dishAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dishImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        needCook.setCellValueFactory(new PropertyValueFactory<>("needCook"));
        dishSell.setCellValueFactory(new PropertyValueFactory<>("discount"));
        if (dishList != null) {
            dishTable.setItems(FXCollections.observableList(dishList));
        } else {
            LazyJavaFXAlert.alert("Внимание", "В данной категории нет блюд", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    /**
     * Initialize reservation table
     */
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

    /**
     * Initialize preorders
     *
     * @param preOrderList prorders list
     */
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

    /**
     * Initialize reports
     */
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

    /**
     * Double mouser click handler on reservation table view
     *
     * @param event income event
     */
    public void mouseReservationClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Reservation res = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            //TODO переделать по аналогу orders
            preorderInitialize(StartApplication.userEntity.getUserBean().getPreOrderList().getByOrderID(res.getId()));
        }
    }

    /**
     * Double mouser click handler on order table view
     *
     * @param event income event
     */
    public void mouseOrdersClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Order ord = (Order) OrdersTable.getSelectionModel().getSelectedItem();
            orderListInitialize(ord.getOrderDetails());
        }
    }

    /**
     * Double mouser click handler on category table view
     *
     * @param event income event
     */
    public void mouseCategoryClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            //TODO нужно переделать по аналогу orders
            dishInitialize(StartApplication.userEntity.getUserBean().getDishesList().getByCategoryId(cat.getId()));
        }
    }

    /**
     * Delete reservation data
     *
     * @param event income event
     */
    public void btnEventReservationDelete(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            //get reservation entity
            Reservation res = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteReservation(res.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getReservationList().removeById(res.getId());
                    reservationInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить резерв", Alert.AlertType.ERROR);
                }
            }
        } else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            //get preorder entity
            PreOrder preOrder = (PreOrder) preorderTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deletePreOrder(preOrder.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getPreOrderList().removeById(preOrder.getId());
                    preorderInitialize(StartApplication.userEntity.getUserBean().getPreOrderList().getByOrderID(preOrder.getReservationID()));
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить элемент предзаказа", Alert.AlertType.ERROR);
                }
                reservationInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Employees tab pane delete button click
     *
     * @param event income event
     */
    public void btnEventEmployeeDelete(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            //get selected employee
            Employees tmp = (Employees) tableEmployees.getSelectionModel().getSelectedItem();
            //check if user try to delete himself
            if (tmp.getId() == StartApplication.userEntity.getEmp().getId()) {
                LazyJavaFXAlert.alert("Ошибка", "Ошибка удаления", "Вы не моежете удалить самого себя", Alert.AlertType.ERROR);
                return;
            }
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteEmployees(tmp.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getEmployeesList().removeById(tmp.getId());
                    employeeInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить сотрудника", Alert.AlertType.ERROR);
                }
            }
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            //get selected position
            Position tmp = (Position) positionTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deletePositions(tmp.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getPositionsList().removeById(tmp.getId());
                    positionInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить должность. Она используется.", Alert.AlertType.ERROR);
                }
            }
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            //get selected cafe coordinate
            CafeCoordinate tmp = (CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteCafeCoordinate(tmp.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getCafeCoordinatesList().removeById(tmp.getId());
                    cafeCoordinateInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить координаты кафе", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Report delete button click
     *
     * @param event income event
     */
    public void btnEventReviewDelete(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            Reports rep = (Reports) repTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteReports(rep.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartApplication.userEntity.getUserBean().getReportsList().removeById(rep.getId());
                    reportsInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить отзыв", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Dish delete event
     *
     * @param event income event
     */
    public void btnEventCategoryDishDelete(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category category = (Category) categoryTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteCategories(category.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartApplication.userEntity.getUserBean().getCategoriesList().removeById(category.getId());
                    categoryInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить категорию", Alert.AlertType.ERROR);
                }
            }
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteDish(dish.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartApplication.userEntity.getUserBean().getDishesList().removeById(dish.getId());
                    dishInitialize(StartApplication.userEntity.getUserBean().getDishesList().getByCategoryId(dish.getCategoryId()));
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить новость", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Delete news button click
     *
     * @param event income event
     */
    public void btnEventNewsDelete(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            if (LazyConfirmDialog.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().deleteNews(news.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartApplication.userEntity.getUserBean().getNewsList().removeById(news.getId());
                    newsInitialize();
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить новость", Alert.AlertType.ERROR);
                }
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

    /**
     * Load charts by period button click
     *
     * @param event income event
     */
    public void periodMouseClick(Event event) {
        if (dataPickerStart.getValue() == null || dataPickerEnd.getValue() == null) {
            LazyJavaFXAlert.alert("Ошибка", null, "Введите приерод для поиска", Alert.AlertType.INFORMATION);
            return;
        }
        //get date from start date picker
        LocalDate startLocal = dataPickerStart.getValue();
        Instant startInstant = Instant.from(startLocal.atStartOfDay(ZoneId.systemDefault()));
        Date startDateUnParse = Date.from(startInstant);
        Date startDate = LazyCalendar.getDateStartFromDate(startDateUnParse);

        //get date from end date picker
        LocalDate endLocal = dataPickerEnd.getValue();
        Instant endInstant = Instant.from(endLocal.atStartOfDay(ZoneId.systemDefault()));
        Date endDateUnParse = Date.from(endInstant);
        Date endDate = LazyCalendar.getDateEndFromDate(endDateUnParse);

        //check if date not equals
        if (endDateUnParse.getTime() == startDateUnParse.getTime()) {
            LazyJavaFXAlert.alert("Ошибка", null, "Даты не должны совпадать", Alert.AlertType.INFORMATION);
            return;
        }
        //clear charts
        clear();
        //load chart
        if (!pieChartInit((int) (startDate.getTime() / 1000), (int) (endDate.getTime() / 1000))) {
            LazyJavaFXAlert.alert("Ошибка", "За заданый период ничего не найдено", "Введите другие данные", Alert.AlertType.INFORMATION);
        }
        if (!barChartInit((int) (startDate.getTime() / 1000), (int) (endDate.getTime() / 1000))) {
            LazyJavaFXAlert.alert("Ошибка", "За заданый период ничего не найдено", "Введите другие данные", Alert.AlertType.INFORMATION);
        }
        if (!lineChartInit((int) (startDate.getTime() / 1000), (int) (endDate.getTime() / 1000))) {
            LazyJavaFXAlert.alert("Ошибка", "За заданый период ничего не найдено", "Введите другие данные", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Mount button mouse click
     *
     * @param event income event
     */
    public void monthMouseClick(Event event) {
        clear();
        if (!pieChartInit((int) (LazyCalendar.getMonthFirstDay().getTime() / 1000), (int) (LazyCalendar.getMonthLastDay().getTime() / 1000))) {
            LazyJavaFXAlert.systemError();
            return;
        }
        if (!barChartInit((int) (LazyCalendar.getMonthFirstDay().getTime() / 1000), (int) (LazyCalendar.getMonthLastDay().getTime() / 1000))) {
            LazyJavaFXAlert.systemError();
            return;
        }
        if (!lineChartInit((int) (LazyCalendar.getMonthFirstDay().getTime() / 1000), (int) (LazyCalendar.getMonthLastDay().getTime() / 1000))) {
            LazyJavaFXAlert.systemError();
        }
    }

    /**
     * Clear charts
     */
    private void clear() {
        lineChart.getData().clear();
        barChartFirst.getData().clear();
        pieChart.getData().clear();
    }

    /**
     * Initialize pie chart
     *
     * @param dateStart date start
     * @param dateEnd   date end
     * @return true if pie chart success initialized
     */
    private boolean pieChartInit(int dateStart, int dateEnd) {
        HappyCakeResponse happyCakeResponse = StartApplication.userEntity.getUserBean().getClientActions().getSalesForPeriod(dateStart, dateEnd);
        if (!happyCakeResponse.isSuccess()) {
            LazyJavaFXAlert.systemError();
            return false;
        }
        GraphReportList pieChart = happyCakeResponse.getObject(GraphReportList.class);
        if (pieChart == null) {
            return false;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (GraphReport aTmp : pieChart) {
            pieChartData.add(new PieChart.Data(aTmp.getName(), aTmp.getAmount()));
        }
        this.pieChart.setTitle("Количество проданых продуктов за период с " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateStart)) +
                " по " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateEnd))
        );
        this.pieChart.setLegendSide(Side.LEFT);
        this.pieChart.setData(pieChartData);
        return true;
    }

    /**
     * Initialize line chart
     *
     * @param dateStart date start
     * @param dateEnd   date end
     * @return
     */
    private boolean lineChartInit(int dateStart, int dateEnd) {
        HappyCakeResponse happyCakeResponse = StartApplication.userEntity.getUserBean().getClientActions().getDateSalesForPeriod(dateStart, dateEnd);
        if (!happyCakeResponse.isSuccess()) {
            LazyJavaFXAlert.systemError();
            return false;
        }
        GraphReportList lineReport = happyCakeResponse.getObject(GraphReportList.class);
        if (lineReport == null) {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        final NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        xAxis.setLabel("Дата");
        yAxis.setLabel("Количество");

        XYChart.Series series = new XYChart.Series();
        for (GraphReport aLineReport : lineReport) {
            series.getData().add(new XYChart.Data(aLineReport.getName(), aLineReport.getAmount()));
        }
        series.setName("За текущий месяц");
        lineChart.setTitle("График продаж за период c " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateStart)) +
                " по " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateEnd))
        );
        lineChart.getData().add(series);
        return true;
    }

    /**
     * Initialize bar chart
     *
     * @param dateStart date start
     * @param dateEnd   date end
     * @return
     */
    private boolean barChartInit(int dateStart, int dateEnd) {
        HappyCakeResponse happyCakeResponse = StartApplication.userEntity.getUserBean().getClientActions().getSalesForPeriod(dateStart, dateEnd);
        if (!happyCakeResponse.isSuccess()) {
            LazyJavaFXAlert.systemError();
            return false;
        }
        GraphReportList barChart = happyCakeResponse.getObject(GraphReportList.class);
        if (barChart == null) {
            return false;
        }
        final CategoryAxis xAxis = (CategoryAxis) barChartFirst.getXAxis();
        final NumberAxis yAxis = (NumberAxis) barChartFirst.getYAxis();
        xAxis.setLabel("Продукт");
        yAxis.setLabel("Количество");
        for (GraphReport aBarChart : barChart) {
            XYChart.Series series = new XYChart.Series();
            series.setName(aBarChart.getName());
            series.getData().add(new XYChart.Data("Значение", aBarChart.getAmount()));
            barChartFirst.getData().add(series);
        }
        barChartFirst.setTitle("Продажы за период с " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateStart)) +
                " по " +
                dateFormat.format(LazyDate.getDateFromUnixTimeStaimp(dateEnd))
        );
        return true;
    }

    /**
     * Pie chart mouse entered event
     *
     * @param event income event
     */
    public void pieChartMouseEntered(Event event) {
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> {
                        labelPercent.setVisible(true);
                        labelPercent.setText(String.valueOf(data.getPieValue()) + " шт");
                    });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> labelPercent.setVisible(false));
        }
    }

    /**
     * Dish and category table row click
     *
     * @param event income event
     */
    public void addDishOrCategoryBtnClick(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category cat = new Category();
            //open add categories dialog
            StartApplication.addEditDialog(cat, () -> {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addCategories(cat);
                if (response.isSuccess()) {
                    StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CATEGORIES);
                    categoryInitialize();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = new Dish();
            //open add dish dialog
            StartApplication.addEditDialog(dish, () -> {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addDish(dish);
                if (response.isSuccess()) {
                    StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.DISHES);
                    dishInitialize(StartApplication.userEntity.getUserBean().getDishesList().getByCategoryId(dish.getCategoryId()));
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Edit dish or category btn click
     *
     * @param event income event
     */
    public void editDishOrCategoryBtnClick(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            //open edit categories dialog
            StartApplication.addEditDialog(cat, () -> {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addCategories(cat);
                if (response.isSuccess()) {
                    StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CATEGORIES);
                    categoryTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            //open edit dish dialog
            StartApplication.addEditDialog(dish, () -> {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addDish(dish);
                if (response.isSuccess()) {
                    StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.DISHES);
                    dishTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }


    /**
     * Edit news button click
     *
     * @param event income event
     */
    public void editNewsBtnClick(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            StartApplication.addEditDialog(news, () -> {
                HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addNews(news);
                if (response.isSuccess()) {
                    StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.NEWS);
                    newsTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Add news button click
     *
     * @param event income event
     */
    public void addNewsBtnClick(Event event) {
        News news = new News();
        StartApplication.addEditDialog(news, () -> {
            HappyCakeResponse response = StartApplication.userEntity.getUserBean().getClientActions().addNews(news);
            if (response.isSuccess()) {
                StartApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.NEWS);
                newsInitialize();
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
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

