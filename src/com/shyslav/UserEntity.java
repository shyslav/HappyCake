package com.shyslav;

import com.happycake.sitemodels.Employees;
import com.shyslav.controller.ServerClient;
import com.shyslav.controller.actions.ClientActions;

import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 01.05.17.
 */
public class UserEntity {
    private Employees emp;
    private final ServerClient client;
    private final ClientActions clientActions;
    private boolean login;

    public UserEntity() throws IOException {
        client = new ServerClient();
        clientActions = new ClientActions(client);
    }

    public ServerClient getClient() {
        return client;
    }

    public ClientActions getClientActions() {
        return clientActions;
    }

    public Employees getEmp() {
        return emp;
    }

    public void setEmp(Employees emp) {
        this.emp = emp;
        this.login = true;
    }

    public boolean isLogin() {
        return login;
    }
}
