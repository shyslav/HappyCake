package com.shyslav.server;

import com.shyslav.models.employees;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class serverConnection {
    public static ArrayList<employees> emp;
    public static Socket connection;
    public InputStream inputStream;
    public OutputStream outputStream;
    public static Scanner scanner;
    public static PrintWriter printWriter;
    public static ObjectInputStream objInputStream;
    public static ObjectOutputStream objOutputStream;
    public serverConnection() {
        try {
            this.connection = new Socket("127.0.0.1", 8189);
            //входящий поток
            this.inputStream = connection.getInputStream();
            this.scanner = new Scanner(inputStream);
            //исходящий поток
            this.outputStream = connection.getOutputStream();
            this.printWriter = new PrintWriter(outputStream, true);
            this.emp = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

