package com.shyslav.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane mainPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("../com/shyslav/fxml/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EmployeeApp");
        start();
        itemsSet();
    }

    private void start() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/Main.fxml"));
        mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void itemsSet() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/Items.fxml"));
        BorderPane mainItem = loader.load();
        mainPane.setCenter(mainItem);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
