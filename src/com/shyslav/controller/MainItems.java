package com.shyslav.controller;

import com.shyslav.controller.alert.LazyConfirmDialog;
import com.shyslav.server.ServerConnect;
import com.shyslav.start.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by Shyshkin Vladyslav on 07.03.2016.
 */
public class MainItems {
    @FXML
    private Button Home;
    @FXML
    private Button btnExit;
    @FXML
    private void initialize()
    {
        btnReinitializeAdmin.setVisible(false);
        btnExit.setVisible(false);
    }
    @FXML
    private Button btnReinitializeAdmin;

    private Main main;
    public void HomeButton(ActionEvent actionEvent) throws IOException {
        main.showMainItems();
    }

    public void mouseEventReinitialize(Event event) {
        Main.controllerAdminItems.ReInit();
    }

    public void setBtnReinitializeAdmin(Boolean visiable) {
        this.btnReinitializeAdmin.setVisible(visiable);
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public void setBtnExit(Boolean visiable) {
        this.btnExit.setVisible(visiable);
    }

    public void mouseExitEvent(Event event) {
        if(ServerConnect.emp.size()!=0)
        {
            if(LazyConfirmDialog.confirmAlert("Вы хотите выйти?","Доступ ко всем разделам будет закрыт",null)) {
                ServerConnect.dispatcher();
                Main.controllerMainItems.setBtnExit(false);
            }
        }
    }
}
