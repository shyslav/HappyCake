package com.shyslav.controller.alert;

import javafx.scene.control.Alert;

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
}
