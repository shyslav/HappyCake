package com.shyslav.controller.alert;

import com.happycake.sitemodels.Employees;
import com.shyslav.defaults.ErrorCodes;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Shyshkin Vladyslav on 07.03.2016.
 */
public class EnterFrameController {
    @FXML
    private Label txtMessage;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private TextField txtFieldUsername;

    public void setTopText(String text) {
        txtMessage.setText(text);
    }

    /**
     * CANCEL button click
     *
     * @param event event
     */
    public void actionCancel(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * OK button click
     *
     * @param event event
     */
    public void actionEnter(Event event) {
        if (txtFieldPassword.getLength() < 3 || txtFieldUsername.getLength() < 4) {
            LazyJavaFXAlert.alert("Ошибка", null, "Все поля обязательны для ввода", Alert.AlertType.WARNING);
        } else {
            HappyCakeResponse login = StartApplication.userEntity.getClientActions().login(txtFieldUsername.getText(), txtFieldPassword.getText());
            if (login.getCode() == ErrorCodes.SUCCESS) {
                StartApplication.userEntity.setEmp(login.getObject(Employees.class));
                StartApplication.controllerMainItems.setBtnExit(true);
                actionCancel(event);
            } else {
                LazyJavaFXAlert.alert("Ошибка входа", null, "Не правильный пароль или логин", Alert.AlertType.ERROR);
            }
        }
    }
}
