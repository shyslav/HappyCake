package com.shyslav.start;

import com.sukhaniuk.controller.EmployeeController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EmployeeApp");
        showMainView();
        showMainItems();
    }
    /**
     * Загрузка меин панели
     * @throws IOException
     */
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../fxml/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Загрузка начальных елементов
     * @throws IOException
     */
    public void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../fxml/MainItems.fxml"));
        BorderPane mainItem = loader.load();
        mainLayout.setCenter(mainItem);
    }
    public static void chooseScreenEmployee() throws IOException {
        System.out.println(EmployeeController.class.getResource("../fxml/Employee/EmployeeForm.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EmployeeController.class.getResource("../fxml/Employee/EmployeeTest.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
    }
    public static void main(String[] args) {
        launch(args);
    }

}
