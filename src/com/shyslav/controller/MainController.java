package com.shyslav.controller;

import com.shyslav.start.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainController {
    private Main main;
    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnAdmin;
    @FXML
    private ImageView imgAdmin;
    @FXML
    private ImageView imgCook;
    @FXML
    private ImageView imgEmployee;


    public void mouseEntered(Event event) {
//        Object source = event.getSource();
//        if (!(source instanceof ImageView)) {
//            return;
//        }
//        ImageView MouseEntered = (ImageView) source;
//        switch (MouseEntered.getId()) {
//            case "imgAdmin":
//                imgAdmin.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
//                break;
//            case "imgCook":
//                imgCook.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
//                break;
//            case "imgEmployee":
//                imgEmployee.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
//                break;
//        }
    }

    public void mouseExited(Event event) {
//        imgAdmin.setStyle("");
//        imgCook.setStyle("");
//        imgEmployee.setStyle("");
    }

    public void enterMouseClicked(Event event) throws IOException {
        Object source = event.getSource();
        if (!(source instanceof ImageView)) {
            return;
        }
        ImageView mouseClicked = (ImageView) source;
        switch (mouseClicked.getId()) {
            case "imgAdmin":
                main.alertEnterDialog("Entered Form" , "Enter your password and username please");
                break;
            case "imgCook":
                break;
            case "imgEmployee":
                main.chooseScreenEmployee();
                break;
        }
    }
}
