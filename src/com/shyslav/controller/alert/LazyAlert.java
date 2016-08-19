package com.shyslav.controller.alert;

import javafx.scene.control.Alert;

/**
 * Created by Shyshkin Vladyslav on 28.03.2016.
 */
public class LazyAlert {
    public LazyAlert(String title, String description, String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void SystemError()
    {
        LazyAlert sa = new LazyAlert("Системная ошибка","Возникла ошибка при работе сервера","Обратитесь к разработчикам", Alert.AlertType.ERROR);
    }
    public static void RuleError()
    {
        LazyAlert sa = new LazyAlert("Ошибка доступа","Данный раздел для вас закрыт","Обратитесь к администратору для выдачи прав", Alert.AlertType.ERROR);
    }
    public static void ConnectionError()
    {
        LazyAlert sa = new LazyAlert("Ошибка","Сервер не отвечает, попробуйте позже","Обратитесь к администратору или разработчикам", Alert.AlertType.ERROR);
        System.exit(0);
    }
}
