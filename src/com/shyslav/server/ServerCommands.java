package com.shyslav.server;

import com.shyslav.controller.alert.LazyAlert;
import com.shyslav.models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ServerCommands {
    public static boolean login(String user, String password) {
        ServerConnect.printWriter.println("login:" + user + ";" + password + ";");
        try {
            if (ServerConnect.objInputStream == null)
                ServerConnect.objInputStream = new ObjectInputStream(ServerConnect.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = ServerConnect.objInputStream.readObject();
            if (object.equals("not found")) {
                return false;
            }
            ServerConnect.emp = (ArrayList<employees>) object;
            System.out.println(ServerConnect.emp.get(0).getBirthdayDay());
            return true;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return false;
    }

    public static ArrayList<news> getNews(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectNews:" + 0);
        } else {
            ServerConnect.printWriter.println("selectNews:" + id);
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
            ArrayList<news> news = (ArrayList<news>) object;
            return news;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<category> getCategory(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectCategory:" + 0);
        } else {
            ServerConnect.printWriter.println("selectCategory:" + id);
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
            ArrayList<category> category = (ArrayList<category>) object;
            return category;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }


    public static ArrayList<dish> getDish(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectDish:" + 0);
        } else {
            ServerConnect.printWriter.println("selectDish:" + id);
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
            ArrayList<dish> dish = (ArrayList<dish>) object;
            return dish;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<reservation> getReservation(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectReservation:" + 0);
        } else {
            ServerConnect.printWriter.println("selectReservation:" + id);
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
            ArrayList<reservation> reservation = (ArrayList<reservation>) object;
            return reservation;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<preOrderTable> getPreOrder(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectPreOrder:" + 0);
        } else {
            ServerConnect.printWriter.println("selectPreOrder:" + id);
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
            ArrayList<preOrderTable> preOrder = (ArrayList<preOrderTable>) object;
            return preOrder;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<employees> getEmployees(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectEmployees:" + 0);
        } else {
            ServerConnect.printWriter.println("selectEmployees:" + id);
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
            ArrayList<employees> employees = (ArrayList<employees>) object;
            return employees;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<reports> getReport(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectReports:" + 0);
        } else {
            ServerConnect.printWriter.println("selectReports:" + id);
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
            ArrayList<reports> reports = (ArrayList<reports>) object;
            return reports;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<cafeCoordinate> getCafeCoordinate(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectCafeCoordinte:" + 0);
        } else {
            ServerConnect.printWriter.println("selectCafeCoordinte:" + id);
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
            ArrayList<cafeCoordinate> cafeCoordinate = (ArrayList<cafeCoordinate>) object;
            return cafeCoordinate;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<position> getPosition(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectPositions:" + 0);
        } else {
            ServerConnect.printWriter.println("selectPositions:" + id);
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
            ArrayList<position> position = (ArrayList<position>) object;
            return position;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<orders> getOrders(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectOrders:" + 0);
        } else {
            ServerConnect.printWriter.println("selectOrders:" + id);
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
            ArrayList<orders> order = (ArrayList<orders>) object;
            return order;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<orderList> getOrdersOrderList(String id) {
        if (id == null) {
            ServerConnect.printWriter.println("selectOrderList:" + 0);
        } else {
            ServerConnect.printWriter.println("selectOrderList:" + id);
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
            ArrayList<orderList> order = (ArrayList<orderList>) object;
            return order;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

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
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
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

    public static ArrayList<ReportsGraph> getReportsGraph(String chart, String dateStart, String dateEnd) {
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
            ArrayList<ReportsGraph> charts = (ArrayList<ReportsGraph>) object;
            return charts;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<_Cassir> getCassirData() {
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
            ArrayList<_Cassir> cas = (ArrayList<_Cassir>) object;
            return cas;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String cassirSent(ArrayList<orderList> od, double price) {
        int employeeID = ServerConnect.emp.get(0).getId();
        try {
            if (ServerConnect.objOutputStream == null) {
                ServerConnect.objOutputStream = new ObjectOutputStream(ServerConnect.connection.getOutputStream());
            }
            ServerConnect.printWriter.println("readObj:" + employeeID + "," + price);
            sleep();
            ServerConnect.objOutputStream.writeUnshared(od);
            Object object = null;
            try {
                object = ServerConnect.objInputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            if (object.equals("not found")) {
                return null;
            } else {
                return (String) object;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        LazyAlert.SystemError();
        return "error";
    }

    public static LinkedList<CookOrder> getCookList() {
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
            LinkedList<CookOrder> cas = (LinkedList<CookOrder>) object;
            return cas;
        } catch (IOException e) {
            System.out.println("Не правильно введенные данные");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }
    public static void cookCompliteOrder(int id)
    {
        ServerConnect.printWriter.println("compliteCookOrder:"+id);
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
