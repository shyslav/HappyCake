package com.shyslav.server;

import appmodels._CookOrder;
import appmodels._GraphReport;
import appmodels.localmodels.LocalServerCassir;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Shyshkin Vladyslav on 16.05.2016.
 */
public class ServerCommands {
    public static String delete(String table, int id) {
        ServerConnect.printWriter.println("deleteFromTable:" + table + "," + id);
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return "Ошибка";
            }
            return "Удаление прошло успешно";
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String getValueToUpdate(String table, String what, int id) {
        ServerConnect.printWriter.println("getValueToUpdate:" + table + "," + what + "," + id);
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return "Ошибка";
            }
            return (String) object;
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String executeComand(String comand) {
        if (comand == null) {
            return "Ошибка";
        }
        ServerConnect.printWriter.println(comand);
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return "Ошибка";
            }
            return "done";
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<_GraphReport> getReportsGraph(String chart, String dateStart, String dateEnd) {
        if (dateStart == null || dateEnd == null) {
            ServerConnect.printWriter.println("selectGrapgMonth:" + chart);
        } else {
            ServerConnect.printWriter.println("selectGrapg:" + chart + "," + dateStart + "," + dateEnd);
        }
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return null;
            }
            ArrayList<_GraphReport> charts = (ArrayList<_GraphReport>) object;
            return charts;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<LocalServerCassir> getCassirData() {
        ServerConnect.printWriter.println("selectCassir:");
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return null;
            }
            ArrayList<LocalServerCassir> cas = (ArrayList<LocalServerCassir>) object;
            return cas;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }


    public static LinkedList<_CookOrder> getCookList() {
        ServerConnect.printWriter.println("getCookList:");
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sleep();
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return null;
            }
            LinkedList<_CookOrder> cas = (LinkedList<_CookOrder>) object;
            return cas;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void cookCompliteOrder(int id) {
        ServerConnect.printWriter.println("compliteCookOrder:" + id);
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
