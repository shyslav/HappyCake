package com.shyslav.controller.admin;

import appmodels.GraphReport;
import appmodels.GraphReportList;
import appmodels.localmodels.LocalEmployee;
import com.happycake.sitemodels.*;
import com.shyslav.UserBean;
import com.shyslav.defaults.ErrorCodes;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartDesktopApplication;
import com.shyslav.utils.LazyCalendar;
import com.shyslav.utils.LazyDate;
import com.shyslav.utils.LazyJavaFXAlert;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.happycake.sitemodels.HappyCakeNotifications.*;

/**
 * @author Shyshkin Vladyslav on 28.03.2016.
 */
@SuppressWarnings("unused")
public class AdminController {
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
    private TableColumn<News, Integer> ncID;
    @FXML
    public TextField newsSearchField;
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
    public TextField categorySearchField;

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
    private TableColumn<Dish, Bool> needCook;
    @FXML
    private TableColumn<Dish, String> dishSell;
    @FXML
    public TextField dishSearchField;
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
    private TableColumn<Reservation, String> reservationStatus;
    @FXML
    private TableColumn<Reservation, Integer> reservationAmountPeople;
    @FXML
    private TableColumn<Reservation, String> reservationDescription;
    @FXML
    public TextField reservationSearchField;
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
    private TableView reportsTable;
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
    @FXML
    public TextField reviewSearchField;
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
    @FXML
    public TextField cafeCoordinateSearchField;

    /**
     * Initialize cafe coordinates table
     */
    private void cafeCoordinateInitialize(CafeCoordinateList list) {
        coordId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coordAdrs.setCellValueFactory(new PropertyValueFactory<>("address"));
        coordPhone.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        coordMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cafeCoordinateTable.setItems(FXCollections.observableList(list));
    }

    //******Employee tabs
    @FXML
    private TableView employeesTable;
    @FXML
    private TableColumn<LocalEmployee, Integer> id;
    @FXML
    private TableColumn<LocalEmployee, String> position;
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
    @FXML
    public TextField employeeSearchField;

    //*************Orders
    @FXML
    private TableView ordersTable;
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
    @FXML
    public DatePicker orderDatePickerField;
    @FXML
    public TextField orderSearchFields;

    /**
     * Initialize orders
     */
    private void ordersInitialize(OrderList list) {
        ordID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ordEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        orderFullPrice.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
        ordDate.setCellValueFactory(new PropertyValueFactory<>("dateFromUnix"));
        ordCompliteORnot.setCellValueFactory(new PropertyValueFactory<>("complite"));
        ordersTable.setItems(FXCollections.observableList(list));
    }

    //********OrdersList
    @FXML
    private TableView orderDetailsTable;
    @FXML
    private TableColumn<OrderDetails, Integer> orderDetailsIDColumn;
    @FXML
    private TableColumn<OrderDetails, Integer> orderDetailsOrderIdColumn;
    @FXML
    private TableColumn<OrderDetails, String> orderDetailsDishIdColumn;
    @FXML
    private TableColumn<OrderDetails, Integer> orderDetailsAmountColumn;
    @FXML
    private TableColumn<OrderDetails, Double> orderDetailsPriceColumn;

    /**
     * Initialize order details table
     *
     * @param details order details
     */
    private void orderListInitialize(OrderDetailsList details) {
        orderDetailsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderDetailsOrderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDetailsDishIdColumn.setCellValueFactory(new PropertyValueFactory<>("dishID"));
        orderDetailsAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        orderDetailsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderDetailsTable.setItems(FXCollections.observableList(details));
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

    //******Messenger tab
    @FXML
    public TextArea messengerText;
    @FXML
    public ChoiceBox messengerRoleChoiceBox;

    /**
     * Date format
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private void initialize() {
        StartDesktopApplication.userEntity.getUserBean().waitLoad();
        labelPercent.setTextFill(Color.DARKORANGE);
        labelPercent.setStyle("-fx-font: 24 arial;");
        labelPercent.setVisible(false);
        StartDesktopApplication.controllerMainItems.setBtnReinitializeAdmin(true);
        initializeTableData();
        initializeSearchFields();
        categoryRightClickMenuInitialize();
        dishRightClickMenuInitialize();

        //setup messenger tab
        ObservableList<Object> objects = FXCollections.observableArrayList(HappyCakeRoles.values());
        objects.add("ALL");
        messengerRoleChoiceBox.setItems(objects);
        messengerRoleChoiceBox.setValue("ALL");

        //setup listeners
        initializePingerHandlers();
    }

    /**
     * Initialize pinger handlers
     */
    private void initializePingerHandlers() {
        //register update news listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_NEWS, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.NEWS);
            newsInitialize(StartDesktopApplication.userEntity.getUserBean().getNewsList());
        });
        //register update categories listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_CATEGORIES, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CATEGORIES);
            categoryInitialize(StartDesktopApplication.userEntity.getUserBean().getCategoriesList());
        });
        //register update dishes listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_DISHES, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.DISHES);
            dishInitialize(StartDesktopApplication.userEntity.getUserBean().getDishesList());
        });
        //register update reservation listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_RESERVATION, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.RESERVATION);
            reservationInitialize(StartDesktopApplication.userEntity.getUserBean().getReservationList());
        });
        //register update preorders listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_PREORDER, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.PREORDER);
            preorderInitialize(StartDesktopApplication.userEntity.getUserBean().getPreOrderList());
        });
        //register update employees listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_EMPLOYEES, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.EMPLOYEES);
            employeeInitialize(StartDesktopApplication.userEntity.getUserBean().getEmployeesList());
        });
        //register update reports listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_REPORTS, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.REPORTS);
            reportsInitialize(StartDesktopApplication.userEntity.getUserBean().getReportsList());
        });
        //register update cafe coordinate listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_CAFECOORDINATE, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CAFECOORDINATE);
            cafeCoordinateInitialize(StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList());
        });
        //register update orders listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_ORDERS, (event) -> {
            StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.ORDERS);
            ordersInitialize(StartDesktopApplication.userEntity.getUserBean().getOrderList());
        });
    }

    /**
     * Initialize search fields
     */
    private void initializeSearchFields() {
        //Search in news table
        newsSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            NewsList resultList = new NewsList();
            NewsList newsList = StartDesktopApplication.userEntity.getUserBean().getNewsList();
            if (newValue.isEmpty()) {
                newsInitialize(newsList);
                return;
            }
            for (News news : newsList) {
                if (news.getName().contains(newValue)) {
                    resultList.add(news);
                } else if (news.getText().contains(newValue)) {
                    resultList.add(news);
                } else if (news.getTags().contains(newValue)) {
                    resultList.add(news);
                }
            }
            newsInitialize(resultList);
        });

        //Search in category table
        categorySearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            CategoriesList result = new CategoriesList();
            CategoriesList categoriesList = StartDesktopApplication.userEntity.getUserBean().getCategoriesList();
            if (newValue.isEmpty()) {
                categoryInitialize(categoriesList);
                return;
            }
            for (Category category : categoriesList) {
                if (String.valueOf(category.getId()).equals(newValue)) {
                    result.add(category);
                } else if (category.getDescription().contains(newValue)) {
                    result.add(category);
                } else if (category.getName().contains(newValue)) {
                    result.add(category);
                }
            }
            categoryInitialize(result);
        });

        //Search in dish table
        dishSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            DishesList result = new DishesList();
            DishesList dishesList = StartDesktopApplication.userEntity.getUserBean().getDishesList();
            if (newValue.isEmpty()) {
                dishInitialize(dishesList);
                return;
            }
            for (Dish dish : dishesList) {
                if (String.valueOf(dish.getId()).equals(newValue)) {
                    result.add(dish);
                } else if (dish.getDescription().contains(newValue)) {
                    result.add(dish);
                } else if (dish.getName().contains(newValue)) {
                    result.add(dish);
                } else if (String.valueOf(dish.getCategoryId()).equals(newValue)) {
                    result.add(dish);
                } else if (String.valueOf(dish.getDiscount()).equals(newValue)) {
                    result.add(dish);
                }
            }
            dishInitialize(result);
        });

        //Reservation search
        reservationSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ReservationList result = new ReservationList();
            ReservationList reservationList = StartDesktopApplication.userEntity.getUserBean().getReservationList();
            if (newValue.isEmpty()) {
                reservationInitialize(reservationList);
                return;
            }
            for (Reservation reservation : reservationList) {
                if (reservation.getClientName().contains(newValue)) {
                    result.add(reservation);
                } else if (reservation.getClientPhone().contains(newValue)) {
                    result.add(reservation);
                } else if (reservation.getDescription().contains(newValue)) {
                    result.add(reservation);
                }
            }
            reservationInitialize(result);
        });

        //Employees search
        employeeSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            EmployeesList result = new EmployeesList();
            EmployeesList employeesList = StartDesktopApplication.userEntity.getUserBean().getEmployeesList();
            if (newValue.isEmpty()) {
                employeeInitialize(employeesList);
                return;
            }
            for (Employees employees : employeesList) {
                if (employees.getAddress().contains(newValue)) {
                    result.add(employees);
                } else if (employees.getLastname().contains(newValue)) {
                    result.add(employees);
                } else if (employees.getLogin().contains(newValue)) {
                    result.add(employees);
                } else if (employees.getName().contains(newValue)) {
                    result.add(employees);
                }
            }
            employeeInitialize(result);
        });

        //Cafe coordinate search
        cafeCoordinateSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            CafeCoordinateList result = new CafeCoordinateList();
            CafeCoordinateList cafesList = StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList();
            if (newValue.isEmpty()) {
                cafeCoordinateInitialize(cafesList);
                return;
            }
            for (CafeCoordinate cafeCoordinate : cafesList) {
                if (cafeCoordinate.getAddress().contains(newValue)) {
                    result.add(cafeCoordinate);
                } else if (cafeCoordinate.getEmail().contains(newValue)) {
                    result.add(cafeCoordinate);
                } else if (cafeCoordinate.getMobilePhone().contains(newValue)) {
                    result.add(cafeCoordinate);
                }
            }
            cafeCoordinateInitialize(result);
        });

        //Reports search
        reviewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ReportsList result = new ReportsList();
            ReportsList reportsList = StartDesktopApplication.userEntity.getUserBean().getReportsList();
            if (newValue.isEmpty()) {
                reportsInitialize(reportsList);
                return;
            }
            for (Reports reports : reportsList) {
                if (reports.getAuthor().contains(newValue)) {
                    result.add(reports);
                } else if (reports.getMail().contains(newValue)) {
                    result.add(reports);
                } else if (reports.getPhone().contains(newValue)) {
                    result.add(reports);
                } else if (reports.getText().contains(newValue)) {
                    result.add(reports);
                }
            }
            reportsInitialize(result);
        });

        //orders search
        orderSearchFields.textProperty().addListener((observable, oldValue, newValue) -> {
            OrderList result = new OrderList();
            OrderList orderList = StartDesktopApplication.userEntity.getUserBean().getOrderList();
            if (newValue.isEmpty()) {
                ordersInitialize(orderList);
                return;
            }
            for (Order order : orderList) {
                if (String.valueOf(order.getEmployeeId()).equals(newValue)) {
                    result.add(order);
                    continue;
                }
                //search by data
                for (OrderDetails orderDetails : order.getOrderDetails()) {
                    if (String.valueOf(orderDetails.getDishID()).equals(newValue)) {
                        result.add(order);
                    } else if (String.valueOf(orderDetails.getPrice()).equals(newValue)) {
                        result.add(order);
                    }
                }
            }
            ordersInitialize(result);
        });

        //order date search
        orderDatePickerField.valueProperty().addListener((observable, oldValue, newValue) -> {
            OrderList result = new OrderList();
            OrderList orderList = StartDesktopApplication.userEntity.getUserBean().getOrderList();
            if (newValue == null) {
                ordersInitialize(orderList);
                return;
            }
            Date date = Date.from(newValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
            int startTime = (int) (LazyCalendar.getDateStartFromDate(date).getTime() / 1000);
            int endTime = (int) (LazyCalendar.getDateEndFromDate(date).getTime() / 1000);
            for (Order order : orderList) {
                if (order.getDate() >= startTime && order.getDate() <= endTime) {
                    result.add(order);
                }
            }
            ordersInitialize(result);
        });
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
        orderDetailsTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ordersTable.getSelectionModel().clearSelection();
            }
        }));
        ordersTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderDetailsTable.getSelectionModel().clearSelection();
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
    private void employeeInitialize(EmployeesList list) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        cafeID.setCellValueFactory(new PropertyValueFactory<>("cafeID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        birthdayDay.setCellValueFactory(new PropertyValueFactory<>("dateFromUnix"));
        empLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        empPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        employeesTable.setItems(FXCollections.observableList(list));
    }

    /**
     * Initialize news table
     */
    private void newsInitialize(NewsList list) {
        ncID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<>("tags"));
        newsTable.setItems(FXCollections.observableList(list));
    }

    /**
     * Initialize category table
     */
    private void categoryInitialize(CategoriesList list) {
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoryTable.setItems(FXCollections.observableList(list));
    }

    /**
     * On category table right button click show menu
     */
    private void categoryRightClickMenuInitialize() {
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem = new MenuItem("Show image");
        cm.getItems().add(menuItem);

        menuItem.setOnAction(event -> {
            if (categoryTable.getSelectionModel().getSelectedItem() != null) {
                Category category = (Category) categoryTable.getSelectionModel().getSelectedItem();
                StartDesktopApplication.showImage(category.getImage());
            } else {
                alertNullValue();
            }
        });

        categoryTable.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(categoryTable, t.getScreenX(), t.getScreenY());
            }
        });
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
        needCook.setCellValueFactory(new PropertyValueFactory<>("needCook"));
        dishSell.setCellValueFactory(new PropertyValueFactory<>("discount"));
        if (dishList != null) {
            dishTable.setItems(FXCollections.observableList(dishList));
        } else {
            LazyJavaFXAlert.alert("Внимание", "В данной категории нет блюд", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
        }
    }

    /**
     * On dish table right button click show menu
     */
    private void dishRightClickMenuInitialize() {
        ContextMenu cm = new ContextMenu();
        MenuItem menuItem = new MenuItem("Show image");
        cm.getItems().add(menuItem);

        menuItem.setOnAction(event -> {
            if (dishTable.getSelectionModel().getSelectedItem() != null) {
                Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
                StartDesktopApplication.showImage(dish.getImage());
            } else {
                alertNullValue();
            }
        });

        dishTable.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(dishTable, t.getScreenX(), t.getScreenY());
            }
        });
    }

    /**
     * Initialize reservation table
     */
    private void reservationInitialize(ReservationList list) {
        reservationID.setCellValueFactory(new PropertyValueFactory<>("id"));
        reservationCafeID.setCellValueFactory(new PropertyValueFactory<>("cafeId"));
        reservationClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        reservationClientPhone.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("dateFromUnix"));
        reservationStatus.setCellValueFactory(new PropertyValueFactory<>("confirm"));
        reservationAmountPeople.setCellValueFactory(new PropertyValueFactory<>("amountPeople"));
        reservationDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reservationTable.setItems(FXCollections.observableList(list));
    }

    /**
     * Initialize preorders
     *
     * @param preOrderList prorders list
     */
    private void preorderInitialize(PreOrderList preOrderList) {
        preOrderResID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
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
    private void reportsInitialize(ReportsList list) {
        repID.setCellValueFactory(new PropertyValueFactory<>("id"));
        repAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        repText.setCellValueFactory(new PropertyValueFactory<>("text"));
        repDate.setCellValueFactory(new PropertyValueFactory<>("dateFromUnix"));
        repMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        repPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        repVision.setCellValueFactory(new PropertyValueFactory<>("vision"));
        reportsTable.setItems(FXCollections.observableList(list));
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
            preorderInitialize(StartDesktopApplication.userEntity.getUserBean().getPreOrderList().getByOrderID(res.getId()));
        }
    }

    /**
     * Double mouser click handler on order table view
     *
     * @param event income event
     */
    public void mouseOrdersClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Order ord = (Order) ordersTable.getSelectionModel().getSelectedItem();
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
            dishInitialize(StartDesktopApplication.userEntity.getUserBean().getDishesList().getByCategoryId(cat.getId()));
        }
    }

    /**
     * Element in table hasn't selected
     */
    private void alertNullValue() {
        LazyJavaFXAlert.alert("Ошибка", null, "Выберите элемент таблицы для добавления, правки или удаления", Alert.AlertType.WARNING);
    }

    /**
     * Reinitialize all tables
     */
    public void initializeTableData() {
        //tab 1
        newsSearchField.clear();
        //tab 2
        categorySearchField.clear();
        dishSearchField.clear();
        //tab 3
        reservationSearchField.clear();
        //tab 4
        employeeSearchField.clear();
        cafeCoordinateSearchField.clear();
        //tab 5
        reviewSearchField.clear();
        //tab 6
        orderSearchFields.clear();
        orderDatePickerField.setValue(null);

        //tab 7
        initializeIMT();

        employeeInitialize(StartDesktopApplication.userEntity.getUserBean().getEmployeesList());
        newsInitialize(StartDesktopApplication.userEntity.getUserBean().getNewsList());
        categoryInitialize(StartDesktopApplication.userEntity.getUserBean().getCategoriesList());
        dishInitialize(StartDesktopApplication.userEntity.getUserBean().getDishesList());
        reservationInitialize(StartDesktopApplication.userEntity.getUserBean().getReservationList());
        preorderInitialize(new PreOrderList());
        reportsInitialize(StartDesktopApplication.userEntity.getUserBean().getReportsList());
        cafeCoordinateInitialize(StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList());
        CategoryDishHandler();
        reservationHandler();
        ordersInitialize(StartDesktopApplication.userEntity.getUserBean().getOrderList());
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
        HappyCakeResponse happyCakeResponse = StartDesktopApplication.userEntity.getUserBean().getClientActions().getSalesForPeriod(dateStart, dateEnd);
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
        HappyCakeResponse happyCakeResponse = StartDesktopApplication.userEntity.getUserBean().getClientActions().getDateSalesForPeriod(dateStart, dateEnd);
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
        HappyCakeResponse happyCakeResponse = StartDesktopApplication.userEntity.getUserBean().getClientActions().getSalesForPeriod(dateStart, dateEnd);
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
     * Edit news button click
     *
     * @param event income event
     */
    public void editNewsBtnClick(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(news, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addNews(news);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.NEWS);
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
        StartDesktopApplication.openSaveDialog(news, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addNews(news);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.NEWS);
                newsInitialize(StartDesktopApplication.userEntity.getUserBean().getNewsList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Delete news button click
     *
     * @param actionEvent income event
     */
    public void deleteNewsBtnClick(ActionEvent actionEvent) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            News news = (News) newsTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteNews(news.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartDesktopApplication.userEntity.getUserBean().getNewsList().removeById(news.getId());
                    newsInitialize(StartDesktopApplication.userEntity.getUserBean().getNewsList());
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
     * Add category button click
     *
     * @param actionEvent income event
     */
    public void addCategoryBtnClick(ActionEvent actionEvent) {
        Category cat = new Category();
        //open add categories dialog
        StartDesktopApplication.openSaveDialog(cat, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addCategories(cat);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CATEGORIES);
                categoryInitialize(StartDesktopApplication.userEntity.getUserBean().getCategoriesList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Add dish button click
     *
     * @param actionEvent income event
     */
    public void addDishBtnClick(ActionEvent actionEvent) {
        Dish dish = new Dish();
        //open add dish dialog
        StartDesktopApplication.openSaveDialog(dish, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addDish(dish);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.DISHES);
                dishInitialize(StartDesktopApplication.userEntity.getUserBean().getDishesList().getByCategoryId(dish.getCategoryId()));
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Edit category btn click
     *
     * @param actionEvent income event
     */
    public void editCategoryBtnClick(ActionEvent actionEvent) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category cat = (Category) categoryTable.getSelectionModel().getSelectedItem();
            //open edit categories dialog
            StartDesktopApplication.openSaveDialog(cat, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addCategories(cat);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CATEGORIES);
                    categoryTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Edit dish btn click
     *
     * @param actionEvent income event
     */
    public void editDishBtnClick(ActionEvent actionEvent) {
        if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            //open edit dish dialog
            StartDesktopApplication.openSaveDialog(dish, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addDish(dish);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.DISHES);
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
     * Category delete button click
     *
     * @param actionEvent income event
     */
    public void deleteCategoryBtnClick(ActionEvent actionEvent) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category category = (Category) categoryTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteCategories(category.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartDesktopApplication.userEntity.getUserBean().getCategoriesList().removeById(category.getId());
                    categoryInitialize(StartDesktopApplication.userEntity.getUserBean().getCategoriesList());
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить категорию", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Dish delete button click
     *
     * @param actionEvent income event
     */
    public void deleteDishBtnClick(ActionEvent actionEvent) {
        if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Dish dish = (Dish) dishTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteDish(dish.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove news
                    StartDesktopApplication.userEntity.getUserBean().getDishesList().removeById(dish.getId());
                    dishInitialize(StartDesktopApplication.userEntity.getUserBean().getDishesList());
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
     * Pre order add button click
     *
     * @param actionEvent income event
     */
    public void addPreOrderBtnClick(ActionEvent actionEvent) {
        PreOrder tmp = new PreOrder();
        StartDesktopApplication.openSaveDialog(tmp, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addPreorder(tmp);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.PREORDER);
                preorderInitialize(StartDesktopApplication.userEntity.getUserBean().getPreOrderList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Edit pre order button click
     *
     * @param actionEvent
     */
    public void editPreOrderBtnClick(ActionEvent actionEvent) {
        if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            PreOrder tmp = (PreOrder) preorderTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(tmp, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addPreorder(tmp);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.PREORDER);
                    preorderTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Delete pre order data
     *
     * @param actionEvent income event
     */
    public void deletePreOrderBtnClick(ActionEvent actionEvent) {
        if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            //get preorder entity
            PreOrder preOrder = (PreOrder) preorderTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deletePreOrder(preOrder.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartDesktopApplication.userEntity.getUserBean().getPreOrderList().removeById(preOrder.getId());
                    preorderInitialize(StartDesktopApplication.userEntity.getUserBean().getPreOrderList());
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить элемент предзаказа", Alert.AlertType.ERROR);
                }
                reservationInitialize(StartDesktopApplication.userEntity.getUserBean().getReservationList());
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Reservation add button click
     *
     * @param actionEvent income event
     */
    public void addReservationBtnClick(ActionEvent actionEvent) {
        Reservation tmp = new Reservation();
        StartDesktopApplication.openSaveDialog(tmp, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addReservation(tmp);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.RESERVATION);
                reservationInitialize(StartDesktopApplication.userEntity.getUserBean().getReservationList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Reservation edit button click
     *
     * @param actionEvent income event
     */
    public void editReservationBtnClick(ActionEvent actionEvent) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            Reservation tmp = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(tmp, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addReservation(tmp);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.RESERVATION);
                    reservationTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Delete reservation data
     *
     * @param actionEvent income event
     */
    public void deleteReservationBtnClick(ActionEvent actionEvent) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            //get reservation entity
            Reservation res = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteReservation(res.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartDesktopApplication.userEntity.getUserBean().getReservationList().removeById(res.getId());
                    reservationInitialize(StartDesktopApplication.userEntity.getUserBean().getReservationList());
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить резерв", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Add cafe btn click
     *
     * @param actionEvent income event
     */
    public void addCafeBtnClick(ActionEvent actionEvent) {
        CafeCoordinate tmp = new CafeCoordinate();
        StartDesktopApplication.openSaveDialog(tmp, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addCafeCoordinate(tmp);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CAFECOORDINATE);
                cafeCoordinateInitialize(StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Edit cafe btn click
     *
     * @param actionEvent income event
     */
    public void editCafeBtnClick(ActionEvent actionEvent) {
        if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            CafeCoordinate tmp = (CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(tmp, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addCafeCoordinate(tmp);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.CAFECOORDINATE);
                    cafeCoordinateTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Delete cafe btn click
     *
     * @param actionEvent income event
     */
    public void deleteCafeBtnClick(ActionEvent actionEvent) {
        if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            //get selected cafe coordinate
            CafeCoordinate tmp = (CafeCoordinate) cafeCoordinateTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteCafeCoordinate(tmp.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList().removeById(tmp.getId());
                    cafeCoordinateInitialize(StartDesktopApplication.userEntity.getUserBean().getCafeCoordinatesList());
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
     * Add employee btn click
     *
     * @param actionEvent income event
     */
    public void addEmployeeBtnClick(ActionEvent actionEvent) {
        Employees tmp = new Employees();
        StartDesktopApplication.openSaveDialog(tmp, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addEmployee(tmp);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.EMPLOYEES);
                employeeInitialize(StartDesktopApplication.userEntity.getUserBean().getEmployeesList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Edit employee btn click
     *
     * @param actionEvent income event
     */
    public void editEmployeeBtnClick(ActionEvent actionEvent) {
        if (employeesTable.getSelectionModel().getSelectedItem() != null) {
            Employees tmp = (Employees) employeesTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(tmp, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addEmployee(tmp);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.EMPLOYEES);
                    employeesTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Delete employee btn click
     *
     * @param actionEvent income event
     */
    public void deleteEmployeeBtnClick(ActionEvent actionEvent) {
        if (employeesTable.getSelectionModel().getSelectedItem() != null) {
            //get selected employee
            Employees tmp = (Employees) employeesTable.getSelectionModel().getSelectedItem();
            //check if user try to delete himself
            if (tmp.getId() == StartDesktopApplication.userEntity.getEmp().getId()) {
                LazyJavaFXAlert.alert("Ошибка", "Ошибка удаления", "Вы не моежете удалить самого себя", Alert.AlertType.ERROR);
                return;
            }
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteEmployees(tmp.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartDesktopApplication.userEntity.getUserBean().getEmployeesList().removeById(tmp.getId());
                    employeeInitialize(StartDesktopApplication.userEntity.getUserBean().getEmployeesList());
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить сотрудника", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }

    /**
     * Review add button click
     *
     * @param actionEvent income event
     */
    public void addReviewBtnClick(ActionEvent actionEvent) {
        Reports tmp = new Reports();
        StartDesktopApplication.openSaveDialog(tmp, () -> {
            HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addReports(tmp);
            if (response.isSuccess()) {
                StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.REPORTS);
                reportsInitialize(StartDesktopApplication.userEntity.getUserBean().getReportsList());
            } else {
                LazyJavaFXAlert.systemError();
            }
        });
    }

    /**
     * Review edit btn click
     *
     * @param actionEvent income event
     */
    public void editReviewBtnClick(ActionEvent actionEvent) {
        if (reportsTable.getSelectionModel().getSelectedItem() != null) {
            Reports tmp = (Reports) reportsTable.getSelectionModel().getSelectedItem();
            StartDesktopApplication.openSaveDialog(tmp, () -> {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().addReports(tmp);
                if (response.isSuccess()) {
                    StartDesktopApplication.userEntity.getUserBean().reloadNews(UserBean.RELOAD_TYPES.REPORTS);
                    reportsTable.refresh();
                } else {
                    LazyJavaFXAlert.systemError();
                }
            });
        } else {
            alertNullValue();
        }
    }

    /**
     * Review delete button click
     *
     * @param actionEvent income event
     */
    public void deleteReviewBtnClick(ActionEvent actionEvent) {
        if (reportsTable.getSelectionModel().getSelectedItem() != null) {
            Reports rep = (Reports) reportsTable.getSelectionModel().getSelectedItem();
            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().deleteReports(rep.getId());
                //check if server return success
                if (response.getCode() == ErrorCodes.SUCCESS) {
                    //locally remove reservation
                    StartDesktopApplication.userEntity.getUserBean().getReportsList().removeById(rep.getId());
                    reportsInitialize(StartDesktopApplication.userEntity.getUserBean().getReportsList());
                } else {
                    LazyJavaFXAlert.alert("Ошибка", response
                            .getMessageText(), "Невозможно удалить отзыв", Alert.AlertType.ERROR);
                }
            }
        } else {
            alertNullValue();
        }
    }


    //----IMT
    public TextField IMTStorageCost;
    public TextField IMTShippingCost;
    public ChoiceBox IMTDishSelectBox;

    //imt table
    public TableView IMTTable;
    public TableColumn imtDishIDColumn;
    public TableColumn imtDishNameColumn;
    public TableColumn imtStorageCostColumn;
    public TableColumn imtShippingCostColumn;

    /**
     * Initialize imt tab
     */
    private void initializeIMT() {
        initializeIMTTable();
        initializeIMTDishChoiceBox();
    }

    /**
     * Initialize imt table
     */
    private void initializeIMTTable() {
        imtDishIDColumn.setCellValueFactory(new PropertyValueFactory<>("dishID"));
        imtDishNameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        imtStorageCostColumn.setCellValueFactory(new PropertyValueFactory<>("storageCost"));
        imtShippingCostColumn.setCellValueFactory(new PropertyValueFactory<>("shippingCost"));

        // заполняем таблицу данными
        IMTTable.setItems(FXCollections.observableList(new IMTTableValuesList()));
    }

    /**
     * Initialize dish choose box
     */
    private void initializeIMTDishChoiceBox() {
        IMTDishSelectBox.setItems(FXCollections.observableList(StartDesktopApplication.userEntity.getUserBean().getDishesList().getDishNames()));
    }

    /**
     * On imt start button click
     *
     * @param mouseEvent income event
     */
    public void onIMTStartButtonClick(MouseEvent mouseEvent) {
        if (IMTTable.getItems().size() == 0) {
            LazyJavaFXAlert.alert("Ошибка", "Данные не вибранны", "Данные для анализа алгоритма не указаны", Alert.AlertType.ERROR);
            return;
        }
        try {
            IMTTableValuesList list = new IMTTableValuesList();
            ObservableList items = IMTTable.getItems();
            for (Object item : items) {
                list.add((IMTTableValues) item);
            }
            StartDesktopApplication.loadIMTForm(list);
        } catch (IOException e) {
            LazyJavaFXAlert.systemError();
        }
    }

    /**
     * On imt add button click
     *
     * @param mouseEvent income event
     */
    public void onIMTAddButtonClick(MouseEvent mouseEvent) {
        if (IMTStorageCost.getText().isEmpty()) {
            LazyJavaFXAlert.alert("Ошибка", "Ошибка добавления", "Затраты на хранения не могут быть нулевые", Alert.AlertType.ERROR);
            return;
        } else if (IMTShippingCost.getText().isEmpty()) {
            LazyJavaFXAlert.alert("Ошибка", "Ошибка добавления", "Затраты на доставку не могут быть нулевые", Alert.AlertType.ERROR);
            return;
        } else if (IMTDishSelectBox.getValue() == null) {
            LazyJavaFXAlert.alert("Ошибка", "Ошибка добавления", "Выберите блюдо", Alert.AlertType.ERROR);
            return;
        }
        String value = (String) IMTDishSelectBox.getValue();
        int dishID = DishesList.getSelectableMap().get(value);
        Dish dish = StartDesktopApplication.userEntity.getUserBean().getDishesList().getByID(dishID);
        IMTTable.getItems().add(
                new IMTTableValues(dish.getId(),
                        dish.getName(),
                        Double.parseDouble(IMTStorageCost.getText()),
                        Double.parseDouble(IMTShippingCost.getText())));

        IMTDishSelectBox.setValue(null);
        IMTStorageCost.clear();
        IMTShippingCost.clear();
    }

    /**
     * Send message button click
     *
     * @param actionEvent action event
     */
    public void sendMessageButtonClick(ActionEvent actionEvent) {
        if (messengerRoleChoiceBox.getValue() == null) {
            LazyJavaFXAlert.alert("Ошибка", null, "Выберите роль для отправки сообщения", Alert.AlertType.ERROR);
            return;
        }
        if (messengerText.getText().isEmpty() || messengerText.getText().length() < 10) {
            LazyJavaFXAlert.alert("Ошибка", null, "Введите текст для отправки сообщения", Alert.AlertType.ERROR);
            return;
        }
        StartDesktopApplication.userEntity.getUserBean().getClientActions().sendMessage((String) messengerRoleChoiceBox.getValue(), messengerText.getText());
        messengerText.clear();
    }
}

