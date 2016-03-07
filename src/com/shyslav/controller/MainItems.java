package com.shyslav.controller;

import com.shyslav.start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by Shyshkin Vladyslav on 07.03.2016.
 */
public class MainItems {
    @FXML
    private Button Home;

    private Main main;
    public void HomeButton(ActionEvent actionEvent) throws IOException {
        main.showMainItems();
    }
}
