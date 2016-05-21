package com.shyslav.controller.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Shyshkin Vladyslav on 21.05.2016.
 */
public class confirmAlert {
    private String title;
    private String headerText;
    private String question;

    public static boolean confirmAlert(String title,String headerText,String question)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(question);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
