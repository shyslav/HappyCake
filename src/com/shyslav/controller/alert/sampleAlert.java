package com.shyslav.controller.alert;

import javafx.scene.control.Alert;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class sampleAlert {
    public sampleAlert(String title,String description, String content,Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
