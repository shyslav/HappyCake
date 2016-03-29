package com.shyslav.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.shyslav.models.*;

/**
 * Created by Shyshkin Vladyslav on 27.03.2016.
 */
public class Server implements Runnable {
    public static ArrayList<employees> emp;
    public static Socket connection;
    public InputStream inputStream;
    public OutputStream outputStream;
    public static Scanner scanner;
    public static PrintWriter printWriter;
    public static ObjectInputStream objInputStream;
    public static ObjectOutputStream objOutputStream;

    public Server() {
        try {
            this.connection = new Socket("127.0.0.1", 8189);
            //входящий поток
            this.inputStream = connection.getInputStream();
            this.scanner = new Scanner(inputStream);
            //исходящий поток
            this.outputStream = connection.getOutputStream();
            this.printWriter = new PrintWriter(outputStream, true);
            this.emp = new ArrayList<>();
            //login("ivanov","ivanov");
            //входящий и исходящий поток обьектов
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
    }

    public static boolean login(String user, String password) {
        printWriter.println("login:" + user + ";" + password + ";");
        try {
            if (objInputStream == null)
                objInputStream = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = objInputStream.readObject();
            if (object.equals("not found")) {
                return false;
            }
            emp = (ArrayList<employees>) object;
            System.out.println(emp.get(0).getBirthdayDay());
            return true;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }
}
