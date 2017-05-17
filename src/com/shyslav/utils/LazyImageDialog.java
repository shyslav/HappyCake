package com.shyslav.utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;

/**
 * @author Shyshkin Vladyslav on 17.05.17.
 */
public class LazyImageDialog {
    private final Stage stage;
    //link to main stage
    private Stage primaryStage;
    //image
    private final byte[] image;

    public LazyImageDialog(Stage primaryStage, byte[] image) {
        this.stage = new Stage();
        this.primaryStage = primaryStage;
        this.image = image;
        start();
    }

    private void start() {
        ByteArrayInputStream bis = new ByteArrayInputStream(image);
        Image image = new Image(bis);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 300);
        pane.setCenter(imageView);

        //show scene
        Scene scene = new Scene(pane);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(false);
        stage.setTitle("Image viewer");
        stage.setScene(scene);
        stage.show();
    }
}
