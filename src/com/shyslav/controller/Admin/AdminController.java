package com.shyslav.controller.Admin;

import com.shyslav.models.jEmployees;
import com.shyslav.models.news;
import com.sukhaniuk.interfaces.impls.EmployeeList;
import com.sukhaniuk.interfaces.impls.NewsList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class AdminController {
    EmployeeList el = new EmployeeList();
    NewsList nl = new NewsList();
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
    //*******News tabs
    @FXML
    private TableView newsTable;
    @FXML
    private TableColumn<news,Integer> ncAuthorID;
    @FXML
    private TableColumn<news,String> ncNews;
    @FXML
    private TableColumn<news,String> ncText;
    @FXML
    private TableColumn<news,Date> ncDate;
    @FXML
    private TableColumn<news,Integer> ncViews;
    @FXML
    private TableColumn<news,String> ncTegs;
    @FXML
    private TableColumn<news,String> ncImageView;

    @FXML
    private void initialize()
    {
        employeeInitialize();
        newsInitialize();
    }

    private void employeeInitialize()
    {
        id.setCellValueFactory(new PropertyValueFactory<jEmployees, Integer>("id"));
        positionID.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("positionID"));
        cafeID.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("cafeID"));
        name.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("lastname"));
        address.setCellValueFactory(new PropertyValueFactory<jEmployees, String>("address"));
        birthdayDay.setCellValueFactory(new PropertyValueFactory<jEmployees, Date>("birthdayDay"));
        el.initialTable();
        //записать обзервебл лист в таблицу
        tableEmployees.setItems(el.getEmployeeList());
    }
    private void newsInitialize()
    {
        ncAuthorID.setCellValueFactory(new PropertyValueFactory<news,Integer>("authorID"));
        ncNews.setCellValueFactory(new PropertyValueFactory<news,String>("name"));
        ncText.setCellValueFactory(new PropertyValueFactory<news,String>("text"));
        ncDate.setCellValueFactory(new PropertyValueFactory<news,Date>("date"));
        ncViews.setCellValueFactory(new PropertyValueFactory<news,Integer>("view"));
        ncTegs.setCellValueFactory(new PropertyValueFactory<news,String>("tegs"));
        ncImageView.setCellValueFactory(new PropertyValueFactory<news,String>("imageLink"));
        nl.initialTable();
        System.out.println(nl.getNewsList().get(0).getText());
        newsTable.setItems(nl.getNewsList());

    }
}
