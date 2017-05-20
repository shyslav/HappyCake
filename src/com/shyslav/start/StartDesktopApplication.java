package com.shyslav.start;

import appmodels.IMTDataList;
import com.happycake.editablemodel.EditableFieldException;
import com.shyslav.UserConnection;
import com.shyslav.controller.MainItemsController;
import com.shyslav.controller.admin.AdminController;
import com.shyslav.controller.admin.IMTTableValues;
import com.shyslav.controller.admin.IMTTableValuesList;
import com.shyslav.controller.cook.CookController;
import com.shyslav.controller.employee.EmployeeController;
import com.shyslav.models.IMTRequest;
import com.shyslav.models.IMTRequestList;
import com.shyslav.mysql.interfaces.DBEntity;
import com.shyslav.utils.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import starter.IMTModalWindow;

import java.io.IOException;
import java.util.Optional;

public class StartDesktopApplication extends Application {
    /**
     * Primary stage
     */
    private static Stage primaryStage;
    /**
     * Main application pane. To center of this pane adds all another
     */
    private static BorderPane mainLayout;
    /**
     * Main items controller
     */
    public static MainItemsController controllerMainItems;
    /**
     * Admin pane controller
     */
    public static AdminController controllerAdminItems;
    /**
     * Employee pane controller
     */
    public static EmployeeController employeeController;
    /**
     * Cook pane controller
     */
    public static CookController cookConroller;
    /**
     * User entity
     */
    public static UserConnection userEntity;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartDesktopApplication.primaryStage = primaryStage;
        StartDesktopApplication.primaryStage.setTitle("EmployeeApp");
        StartDesktopApplication.userEntity = new UserConnection();

        showMainView();
        showMainItems();
        Platform.setImplicitExit(false);
//        confirm dialog to exit action
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы дейстивительно хотите выйти из программы?");
            Optional<ButtonType> closeResponse = alert.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                System.out.println("cancel");
                we.consume();
            } else {
                System.out.println("ok");
                System.exit(0);
            }
        });
    }

    /**
     * Load imt form
     *
     * @throws IOException
     */
    public static void loadIMTForm(IMTTableValuesList tableValues) throws IOException {
        int n = 7;
        IMTRequestList imtRequest = new IMTRequestList();

        for (IMTTableValues tableValue : tableValues) {
            IMTRequest request = new IMTRequest();
            request.setDishName(tableValue.getDishName());
            request.setDishID(tableValue.getDishID());
            request.setN(n);

            //load storage cost
            int[] valueH = new int[n];
            for (int i = 0; i < n; i++) {
                valueH[i] = (int) tableValue.getStorageCost();
            }
            request.setH(valueH);

            //load shipping cost
            int[] valueA = new int[n];
            for (int i = 0; i < n; i++) {
                valueA[i] = (int) tableValue.getShippingCost();
            }
            request.setA(valueA);

            //load demand
            IMTDataList imtDataList = userEntity.getUserBean().getIMTDataList(new int[]{tableValue.getDishID()});
            int[] valueD = new int[n];
            for (int i = 0; i < n; i++) {
                if (imtDataList.size() > i) {
                    valueD[i] = imtDataList.get(i).getCount();
                }
            }
            request.setD(valueD);

            imtRequest.add(request);
        }
        new IMTModalWindow(primaryStage, imtRequest);
    }

    /**
     * Загрузка меин панели
     *
     * @throws IOException
     */
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartDesktopApplication.class.getClass().getResource("/fxml/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        controllerMainItems = loader.getController();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Загрузка начальных елементов
     *
     * @throws IOException
     */
    public static void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartDesktopApplication.class.getResource("/fxml/LoginForm.fxml"));
        BorderPane mainItem = loader.load();
        mainLayout.setCenter(mainItem);
    }

    /**
     * Add employee form to main view
     *
     * @throws IOException
     */
    public static void chooseScreenEmployee() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartDesktopApplication.class.getResource("/fxml/employee/EmployeeForm.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        employeeController = loader.getController();
    }

    /**
     * Added cook form to main view
     *
     * @throws IOException
     */
    public static void chooseScreenCook() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartDesktopApplication.class.getResource("/fxml/cook/CookForm.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        cookConroller = loader.getController();
    }

    /**
     * Added admin form to main vie
     *
     * @throws IOException
     */
    public static void chooseScreenAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartDesktopApplication.class.getResource("/fxml/admin/AdminPane.fxml"));
        BorderPane employeeItem = loader.load();
        mainLayout.setCenter(employeeItem);
        controllerAdminItems = loader.getController();
    }

    /**
     * Load alert enter dialog
     *
     * @param title   enter dialog title
     * @param message enter dialog message
     * @throws IOException
     */
    public static void alertEnterDialog(String title, String message) throws IOException {
        new EnterDialogLoader(primaryStage, title, message);
    }

    /**
     * Show image
     *
     * @param image image bytes array
     */
    public static void showImage(byte[] image) {
        new LazyImageDialog(primaryStage, image);
    }

    /**
     * Show web view
     *
     * @param html html to web view
     */
    public static void showWebView(String html) {
        new LazyWebViewDialog(primaryStage, html);
    }

    /**
     * Open add edit dialog
     *
     * @param entity db entity
     */
    public static void openSaveDialog(DBEntity entity, ISaveDialog completion) {
        try {
            new JavaFXSaveDialog(primaryStage, entity, completion);
        } catch (EditableFieldException e) {
            LazyJavaFXAlert.systemError();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
