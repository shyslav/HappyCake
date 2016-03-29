package com.shyslav.controller.alert;

import com.shyslav.server.Server;
import com.shyslav.start.enterDialogs;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by Shyshkin Vladyslav on 07.03.2016.
 */
public class enterDialogController {
    public static volatile boolean isAvilable = false;
    private enterDialogs ed;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEnter;
    @FXML
    private Label txtMessage;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private TextField txtFieldUsername;

    public void setTopText(String text) {
        // set text from another class
        txtMessage.setText(text);
    }

    public void actionCancel(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionEnter(Event event) {
        if(txtFieldPassword.getLength()<3||txtFieldUsername.getLength()<4)
        {
            sampleAlert sa = new sampleAlert("Ошибка",null,"Все поля обязательны для ввода", Alert.AlertType.WARNING);
        }
        else
        {
            if(!isAvilable) {
                Runnable runnable = new Server();
                Thread tr = new Thread(runnable);
                tr.start();
                isAvilable=true;
            }
            if(Server.login(txtFieldUsername.getText(), txtFieldPassword.getText()))
            {
                actionCancel(event);
            }
            else
            {
                sampleAlert sa = new sampleAlert("Ошибка входа",null,"Не правильный пароль или логин", Alert.AlertType.ERROR);
            }
        }
    }
}
