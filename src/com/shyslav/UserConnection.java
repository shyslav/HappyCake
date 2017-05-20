package com.shyslav;

import com.happycake.sitemodels.Employees;
import com.shyslav.controller.ServerClient;
import com.shyslav.controller.actions.ClientActions;
import com.shyslav.controller.pinger.ClientUpdatesPinger;
import com.shyslav.controller.pinger.PingerListener;
import com.shyslav.defaults.ErrorCodes;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.utils.LazyJavaFXAlert;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 01.05.17.
 */
public class UserConnection {
    private final ClientActions clientActions;
    private ClientUpdatesPinger pinger;
    private Employees employee;
    private boolean login;
    private UserBean userBean;


    public UserConnection() throws IOException {
        ServerClient client = new ServerClient();
        clientActions = new ClientActions(client);
    }

    public Employees getEmp() {
        return employee;
    }

    /**
     * Login to server
     *
     * @param name     username
     * @param password userpassword
     * @return true if server return success
     */
    public boolean login(String name, String password) {
        if (login) {
            return false;
        }
        HappyCakeResponse login = clientActions.login(name, password);
        if (login.getCode() == ErrorCodes.SUCCESS) {
            this.employee = login.getObject(Employees.class);
            this.userBean = new UserBean(clientActions, employee);
            this.login = true;
            this.pinger = new ClientUpdatesPinger(clientActions.getClient());
            registerDefaultsPingerListeners();
            reload();
            return true;
        }
        return false;
    }

    /**
     * Register default listeners
     */
    private void registerDefaultsPingerListeners() {
        pinger.addListener("messagetousers", (event) -> {
            Platform.runLater(() -> LazyJavaFXAlert.alert(
                    "Новое глобнальное сообщение",
                    null,
                    event.getContext(),
                    Alert.AlertType.INFORMATION)
            );

        });
    }

    /**
     * Register pinger listener
     *
     * @param name           url of listener
     * @param pingerListener listener
     */
    public void registerPingerListener(String name, PingerListener pingerListener) {
        //register default pinger
        pinger.addListener(name, pingerListener);
    }

    public boolean isLogin() {
        return login;
    }

    public void reload() {
        Thread tr = new Thread(() -> userBean.reload());
        tr.setName("LOAD_ALL_DATA");
        tr.start();
    }

    /**
     * Logout
     */
    public void logout() {
        pinger.offPingerThread();
    }

    public UserBean getUserBean() {
        return userBean;
    }
}
