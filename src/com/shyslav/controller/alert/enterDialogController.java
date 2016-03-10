package com.shyslav.controller.alert;

import com.shyslav.start.enterDialogs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
