package com.shyslav.utils;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author Shyshkin Vladyslav on 20.05.17.
 */
public class ToggleSwitchButton extends HBox {
    private final Label label = new Label();
    private final Button button = new Button();

    private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);

    public SimpleBooleanProperty switchOnProperty() {
        return switchedOn;
    }

    private void init() {
        label.setText("OFF");
        getChildren().addAll(label, button);
        button.setOnAction((e) -> switchedOn.set(!switchedOn.get()));
        label.setOnMouseClicked((e) -> switchedOn.set(!switchedOn.get()));
        setStyle();
        bindProperties();
    }

    /**
     * Check if button is on
     *
     * @return true if on
     */
    public boolean isON() {
        return label.getText().equalsIgnoreCase("on");
    }

    /**
     * Set button status
     *
     * @param status status
     */
    public void set(boolean status) {
        if (status) {
            label.setText("ON");
            setStyle("-fx-background-color: green;");
            label.toFront();
        } else {
            label.setText("OFF");
            setStyle("-fx-background-color: grey;");
            button.toFront();
        }
    }

    private void setStyle() {
        //Default Width
        setWidth(80);
        label.setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 4;");
        setAlignment(Pos.CENTER_LEFT);
    }

    private void bindProperties() {
        label.prefWidthProperty().bind(widthProperty().divide(2));
        label.prefHeightProperty().bind(heightProperty());
        button.prefWidthProperty().bind(widthProperty().divide(2));
        button.prefHeightProperty().bind(heightProperty());
    }

    public ToggleSwitchButton() {
        init();
        switchedOn.addListener((a, b, c) -> {
            if (c) {
                label.setText("ON");
                setStyle("-fx-background-color: green;");
                label.toFront();
            } else {
                label.setText("OFF");
                setStyle("-fx-background-color: grey;");
                button.toFront();
            }
        });
    }
}

