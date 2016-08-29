package com.shyslav.server;

import appmodels.employees;
import com.shyslav.controller.alert.LazyAlert;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ServerConnect {
    public static ArrayList<employees> emp;
    public static Socket connection;
    public InputStream inputStream;
    public OutputStream outputStream;
    public static Scanner scanner;
    public static PrintWriter printWriter;
    public static ObjectInputStream objInputStream;
    public static ObjectOutputStream objOutputStream;
    public ServerConnect() {
        try {
            this.connection = new Socket("127.0.0.1", 8189);
            this.objOutputStream = new ObjectOutputStream(connection.getOutputStream());
            //входящий поток
            this.inputStream = connection.getInputStream();
            this.scanner = new Scanner(inputStream);
            //исходящий поток
            this.outputStream = connection.getOutputStream();
            this.printWriter = new PrintWriter(outputStream, true);
            this.emp = new ArrayList<>();
        } catch (IOException ex) {
            System.out.println(ex);
            LazyAlert.ConnectionError();
        }
    }
    public static void dispatcher()
    {
        connection = null;
        objOutputStream = null;
        objInputStream = null;
        emp=null;
    }
}

