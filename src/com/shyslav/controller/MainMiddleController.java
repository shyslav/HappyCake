package com.shyslav.controller;

import com.shyslav.controller.alert.LazyJavaFXAlert;
import com.shyslav.start.StartDesktopApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainMiddleController {
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
        if (!StartDesktopApplication.userEntity.isLogin()) {
            StartDesktopApplication.alertEnterDialog("Entered Form", "Enter your password and username please");
            return;
        }
        ImageView mouseClicked = (ImageView) source;
        switch (mouseClicked.getId()) {
            case "imgAdmin":
                if (StartDesktopApplication.userEntity.getEmp().getPositionID() == 1) {
                    StartDesktopApplication.chooseScreenAdmin();
                } else {
                    LazyJavaFXAlert.ruleError();
                }
                break;
            case "imgCook":
                StartDesktopApplication.chooseScreenCook();
                break;
            case "imgEmployee":
                if (StartDesktopApplication.userEntity.getEmp().getPositionID() == 2
                        || StartDesktopApplication.userEntity.getEmp().getPositionID() == 1) {
                    StartDesktopApplication.chooseScreenEmployee();
                } else {
                    LazyJavaFXAlert.ruleError();
                }
                break;
        }
    }
}
