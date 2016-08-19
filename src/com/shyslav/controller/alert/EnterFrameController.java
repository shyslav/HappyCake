package com.shyslav.controller.alert;

import com.shyslav.server.ServerCommands;
import com.shyslav.server.ServerConnect;
import com.shyslav.start.Main;
import com.shyslav.start.EnterDialogStart;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by Shyshkin Vladyslav on 07.03.2016.
 */
public class EnterFrameController {
    private EnterDialogStart ed;
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
            LazyAlert sa = new LazyAlert("Ошибка",null,"Все поля обязательны для ввода", Alert.AlertType.WARNING);
        }
        else
        {
            if(ServerConnect.connection==null)
            {
                ServerConnect sc = new ServerConnect();
            }
            if(ServerCommands.login(txtFieldUsername.getText(), txtFieldPassword.getText()))
            {
                Main.controllerMainItems.setBtnExit(true);
                actionCancel(event);
            }
            else
            {
                LazyAlert sa = new LazyAlert("Ошибка входа",null,"Не правильный пароль или логин", Alert.AlertType.ERROR);
            }
        }
    }
}
