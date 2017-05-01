package com.shyslav;

import com.happycake.sitemodels.Employees;
import com.shyslav.controller.ServerClient;
import com.shyslav.controller.actions.ClientActions;
import com.shyslav.defaults.ErrorCodes;
import com.shyslav.defaults.HappyCakeResponse;

import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 01.05.17.
 */
public class UserConnection {
    private final ClientActions clientActions;
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
            reload();
            return true;
        }
        return false;
    }

    public boolean isLogin() {
        return login;
    }

    public void reload() {
        Thread tr = new Thread(() -> userBean.reload());
        tr.setName("LOAD_ALL_DATA");
        tr.start();
    }

    public UserBean getUserBean() {
        return userBean;
    }
}
