package com.shyslav.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Shyshkin Vladyslav on 28.03.2016.
 */
public class LazyJavaFXAlert {
    /**
     * Generate javafx alert
     *
     * @param title       alert title
     * @param description alert description
     * @param content     alert content
     * @param type        alert typwl
     */
    public static void alert(String title, String description, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void systemError() {
        alert("Системная ошибка", "Возникла ошибка при работе сервера", "Обратитесь к разработчикам", Alert.AlertType.ERROR);
    }

    public static void ruleError() {
        alert("Ошибка доступа", "Данный раздел для вас закрыт", "Обратитесь к администратору для выдачи прав", Alert.AlertType.ERROR);
    }

    public static void connectionError() {
        alert("Ошибка", "Сервер не отвечает, попробуйте позже", "Обратитесь к администратору или разработчикам", Alert.AlertType.ERROR);
        System.exit(0);
    }

    public static boolean confirmAlert(String title, String headerText, String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(question);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
