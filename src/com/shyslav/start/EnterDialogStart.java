package com.shyslav.start;

import com.shyslav.controller.alert.EnterFrameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Shyshkin Vladyslav on 07.03.2016.
 */
public class EnterDialogStart {
    private String title;
    private String message;
    private Stage primaryStage;

    public EnterDialogStart(Stage primaryStage, String title, String message) throws IOException {
        this.primaryStage = primaryStage;
        this.title = title;
        this.message = message;
        start();
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/com/shyslav/fxml/alerts/enterDialogForm.fxml"));
        VBox passwordAlert = loader.load();
        EnterFrameController controller = loader.getController();
        Stage enterFormStage = new Stage();
        enterFormStage.setTitle(title);
        enterFormStage.initModality(Modality.WINDOW_MODAL);
        enterFormStage.initOwner(primaryStage);
        enterFormStage.setResizable(false);
        Scene scene = new Scene(passwordAlert);
        enterFormStage.setScene(scene);
        enterFormStage.show();
        controller.setTopText(message);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
