package com.shyslav.controller.Admin;

import com.shyslav.controller.alert.confirmAlert;
import com.shyslav.controller.alert.sampleAlert;
import com.shyslav.controller.alert.sampleEditUpdate;
import com.shyslav.models.*;
import com.shyslav.server.comands;
import com.shyslav.start.Main;
import com.sukhaniuk.interfaces.impls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
    @FXML
    private void initialize() {
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
            sampleAlert sa = new sampleAlert("Внимание", "В данной категории нет блюд", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
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
        if (preOrderList.getPreorder() != null) {
            preorderTable.setItems(FXCollections.observableList(preOrderList.getPreorder()));
        } else {
            sampleAlert sa = new sampleAlert("Внимание", "В данном заказе нет предзаказа", "Попробуйте выбрать другой предзаказ", Alert.AlertType.WARNING);
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

    public void mouseCategoryClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            category cat = (category) categoryTable.getSelectionModel().getSelectedItem();
            dishInitialize(new DishList(cat.getId()));
        }
    }

    public void MouseClickBtnAddDishCategory(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Main.updateInsertDialog("Добавить Категорию", "category", "insert");
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Main.updateInsertDialog("Добавить блюдо", "category", "insert");
        } else {
            alertNullValue();
        }
    }

    public void MouseClickBtnEditDishCategory(Event event) {

        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Main.updateInsertDialog("Добавить Категорию", "category", "update");
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            Main.updateInsertDialog("Добавить блюдо", "category", "update");
        }
    }

    public void btnEventReservationDelete(Event event) {
        if (reservationTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<reservation> res = reservationTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("reservation",res.get(0).getId());
                reservationInitialize();
            }
        }else if (preorderTable.getSelectionModel().getSelectedItem() != null) {
            sampleAlert sa = new sampleAlert("Ошибка", null, "Удаление элемента предзаказа запрещена", Alert.AlertType.ERROR);
        }
        else {
            alertNullValue();
        }
    }

    public void btnEventEmployeeDelete(Event event) {
        if (tableEmployees.getSelectionModel().getSelectedItem() != null) {
            ObservableList<employees> tmp = tableEmployees.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("employees",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (positionTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<positions> tmp = positionTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("positions",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else if (cafeCoordinateTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<cafeCoordinate> tmp = cafeCoordinateTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("cafecoordinate",tmp.get(0).getId());
                cafeCoordinateInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventReviewDelete(Event event) {
        if (repTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<reports> rep = repTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("reports",rep.get(0).getId());
                reportsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventCategoryDishDelete(Event event) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<category> cat = categoryTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("category",cat.get(0).getId());
                categoryInitialize();
            }
        } else if (dishTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<dish> dish = dishTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("dish",dish.get(0).getId());
                dishInitialize(new DishList(0));
            }
        } else {
            alertNullValue();
        }
    }

    public void btnEventNewsDelete(Event event) {
        if (newsTable.getSelectionModel().getSelectedItem() != null) {
            ObservableList<news> news = newsTable.getSelectionModel().getSelectedItems();
            if (confirmAlert.confirmAlert("Удаление", "Вы уверены что хотите удалить запись?", "Действие не возратимо, запись будет удалена навсегда")) {
                comands.delete("news",news.get(0).getId());
                newsInitialize();
            }
        } else {
            alertNullValue();
        }
    }

    private void alertNullValue() {
        sampleAlert sa = new sampleAlert("Ошибка", null, "Выберите элемент таблицы для добавления, правки или удаления", Alert.AlertType.WARNING);
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
    }
}
