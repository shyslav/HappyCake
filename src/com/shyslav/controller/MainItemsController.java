package com.shyslav.controller;

import com.shyslav.utils.LazyJavaFXAlert;
import com.shyslav.start.StartDesktopApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 07.03.2016.
 */
public class MainItemsController {
    @FXML
    private Button Home;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnReinitializeAdmin;

    @FXML
    private void initialize() {
        btnReinitializeAdmin.setVisible(false);
        btnExit.setVisible(false);
    }

    private StartDesktopApplication main;

    /**
     * Home button click
     *
     * @param actionEvent income event
     * @throws IOException
     */
    public void homeButtonClick(ActionEvent actionEvent) throws IOException {
        StartDesktopApplication.showMainItems();
    }

    /**
     * Reinitialize admin page
     *
     * @param event income event
     */
    public void mouseEventReinitialize(Event event) {
        StartDesktopApplication.controllerAdminItems.initializeTableData();
    }

    /**
     * Set reinitialize admin button
     *
     * @param visible visible
     */
    public void setBtnReinitializeAdmin(Boolean visible) {
        this.btnReinitializeAdmin.setVisible(visible);
    }

    /**
     * Set button exit
     *
     * @param visible true if visible
     */
    public void setBtnExit(boolean visible) {
        this.btnExit.setVisible(visible);
    }

    /**
     * On exit button click
     *
     * @param event income event
     */
    public void mouseExitEvent(Event event) {
        if (LazyJavaFXAlert.confirmAlert("Вы хотите выйти?", "Доступ ко всем разделам будет закрыт", null)) {
            StartDesktopApplication.controllerMainItems.setBtnExit(false);
        }
    }
}
