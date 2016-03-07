package com.shyslav.controller;

import com.shyslav.start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    private Main main;
    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnAdmin;

    @FXML
    public void btnEmployeeAction(ActionEvent actionEvent) throws IOException {
        main.chooseScreenEmployee();
    }

    public void btnAdminAction(ActionEvent actionEvent) {
        
    }
}
