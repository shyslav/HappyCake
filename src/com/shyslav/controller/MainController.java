package com.shyslav.controller;

import com.shyslav.controller.alert.LazyJavaFXAlert;
import com.shyslav.start.StartApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainController {
    @FXML
    private ImageView imgAdmin;
    @FXML
    private ImageView imgCook;
    @FXML
    private ImageView imgEmployee;

    /**
     * Enter icon click
     *
     * @param event event
     * @throws IOException
     */
    public void enterMouseClicked(Event event) throws IOException {
        Object source = event.getSource();
        if (!(source instanceof ImageView)) {
            return;
        }
        if (!StartApplication.userEntity.isLogin()) {
            StartApplication.alertEnterDialog("Entered Form", "Enter your password and username please");
            return;
        }
        ImageView mouseClicked = (ImageView) source;
        switch (mouseClicked.getId()) {
            case "imgAdmin":
                if (StartApplication.userEntity.getEmp().getPositionID() == 1) {
                    StartApplication.chooseScreenAdmin();
                } else {
                    LazyJavaFXAlert.ruleError();
                }
                break;
            case "imgCook":
                StartApplication.chooseScreenCook();
                break;
            case "imgEmployee":
                if (StartApplication.userEntity.getEmp().getPositionID() == 2
                        || StartApplication.userEntity.getEmp().getPositionID() == 1) {
                    StartApplication.chooseScreenEmployee();
                } else {
                    LazyJavaFXAlert.ruleError();
                }
                break;
        }
    }
}
