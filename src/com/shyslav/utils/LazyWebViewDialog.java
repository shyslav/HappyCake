package com.shyslav.utils;

import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;

/**
 * @author Shyshkin Vladyslav on 17.05.17.
 */
public class LazyWebViewDialog {
    private final Stage stage;
    //link to main stage
    private Stage primaryStage;
    //image
    private final String html;

    public LazyWebViewDialog(Stage primaryStage, String html) {
        this.stage = new Stage();
        this.primaryStage = primaryStage;
        this.html = html;
        start();
    }

    private void start() {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        engine.loadContent(html);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(320, 500);
        pane.setCenter(webView);

        ButtonBar buttonBar = new ButtonBar();
        ToggleSwitchButton confirmButton = new ToggleSwitchButton();

        Button print = new Button("Печатать");
        ButtonBar.setButtonData(print, ButtonBar.ButtonData.RIGHT);
        buttonBar.getButtons().add(print);
        print.setOnAction(e -> {
            Printer printer = Printer.getDefaultPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4,
                    PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
            PrinterJob job = PrinterJob.createPrinterJob(printer);
            job.getJobSettings().setPageLayout(pageLayout);
            if (job != null) {
                engine.print(job);
                job.endJob();
                confirmButton.set(true);
            }
        });

        HBox hBox = new HBox();
        Button close = new Button("Закрыть");
        hBox.getChildren().addAll(confirmButton, close);
        ButtonBar.setButtonData(hBox, ButtonBar.ButtonData.LEFT);
        buttonBar.getButtons().add(hBox);
        close.setOnAction(e -> {
            if (confirmButton.isON()) {
                boolean confirmAlert = LazyJavaFXAlert.confirmAlert("Подвердите действие", "Вы действительно выдали чек клиенту?", "Чек должен быть выдан клиенту в любом случае");
                if (confirmAlert) {
                    stage.close();
                }
            } else {
                LazyJavaFXAlert.alert("Ошибка", "Вы не расспечатали чек", "Расспечатайте чек!", Alert.AlertType.ERROR);
            }
        });


        pane.setBottom(buttonBar);

        //show scene
        Scene scene = new Scene(pane);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(false);
        stage.setTitle("Чек");
        stage.setScene(scene);
        stage.show();
    }
}
