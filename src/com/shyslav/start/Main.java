package com.shyslav.start;

import com.shyslav.controller.Admin.AdminController;
import com.shyslav.controller.Cook.CookController;
import com.shyslav.controller.Cook.CookModel;
import com.shyslav.controller.Employee.EmployeeController;
import com.shyslav.controller.MainItems;
import com.shyslav.controller.alert.LazyEditUpdate;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    public static MainItems controllerMainItems;
    public static AdminController controllerAdminItems;
    public static EmployeeController employeeController;
    public static CookController cookConroller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EmployeeApp");
        showMainView();
        showMainItems();
        Platform.setImplicitExit(false);
        //confirm dialog to exit action
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы дейстивительно хотите выйти из программы?");
            Optional<ButtonType> closeResponse = alert.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                System.out.println("cancel");
                we.consume();
            }else
            {
                if(CookModel.tr!=null) {
                    CookModel.tr.interrupt();
                }
                System.out.println("ok");
                System.exit(0);
            }
        });
    }
    /**
     * Загрузка меин панели
     * @throws IOException
     */
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getClass().getResource("/com/shyslav/fxml/MainView.fxml"));
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
        loader.setLocation(Main.class.getResource("/com/shyslav/fxml/LoginForm.fxml"));
        BorderPane mainItem = loader.load();
        mainLayout.setCenter(mainItem);
    }
    public static void chooseScreenEmployee() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/shyslav/fxml/Employee/EmployeeForm.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        employeeController = loader.getController();
    }
    public static void chooseScreenCook() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/shyslav/fxml/Cook/CookForm.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        cookConroller = loader.getController();
    }
    public static void chooseScreenAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/shyslav/fxml/Admin/AdminPane.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        controllerAdminItems = loader.getController();
    }
    public static void alertEnterDialog(String title, String message) throws IOException {
        EnterDialogStart eD = new EnterDialogStart(primaryStage, title, message);
    }
    public static void updateInsertDialog(String title, String tableName, String command, int id)
    {
        LazyEditUpdate insertOrUpdate = new LazyEditUpdate(primaryStage, title , tableName, command , id);
    }
    public static void main(String[] args) {
        launch(args);
    }

}
