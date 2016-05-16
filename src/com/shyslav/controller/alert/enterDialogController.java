package com.shyslav.controller.alert;

import com.shyslav.server.comands;
import com.shyslav.server.serverConnection;
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
            if(serverConnection.connection==null)
            {
                serverConnection sc = new serverConnection();
            }
            if(comands.login(txtFieldUsername.getText(), txtFieldPassword.getText()))
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
