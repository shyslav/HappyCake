package com.shyslav.start;

import com.shyslav.controller.Admin.AdminController;
import com.shyslav.controller.MainItems;
import com.shyslav.controller.alert.sampleEditUpdate;
import com.shyslav.controller.Employee.EmployeeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    public static MainItems controllerMainItems;
    public static AdminController controllerAdminItems;
    public static EmployeeController employeeController;

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
        controllerMainItems = loader.getController();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Загрузка начальных елементов
     * @throws IOException
     */
    public static void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../fxml/LoginForm.fxml"));
        BorderPane mainItem = loader.load();
        mainLayout.setCenter(mainItem);
    }
    public static void chooseScreenEmployee() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../fxml/Employee/EmployeeForm.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        employeeController = loader.getController();
    }

    public static void chooseScreenAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../fxml/Admin/AdminPane.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        controllerAdminItems = loader.getController();
    }
    public static void alertEnterDialog(String title, String message) throws IOException {
        enterDialogs eD = new enterDialogs(primaryStage, title, message);
    }
    public static void updateInsertDialog(String title, String tableName, String command, int id)
    {
        sampleEditUpdate insertOrUpdate = new sampleEditUpdate(primaryStage, title , tableName, command , id);
    }
    public static void main(String[] args) {
        launch(args);
    }

}
