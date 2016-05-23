package com.shyslav.server;

import com.shyslav.controller.MainItems;
import com.shyslav.models.*;
import com.shyslav.start.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class comands {
    public static boolean login(String user, String password) {
        serverConnection.printWriter.println("login:" + user + ";" + password + ";");
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
            if (object.equals("not found")) {
                return false;
            }
            serverConnection.emp = (ArrayList<employees>) object;
            System.out.println(serverConnection.emp.get(0).getBirthdayDay());
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
            serverConnection.printWriter.println("selectNews:" + 0);
        } else {
            serverConnection.printWriter.println("selectNews:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectCategory:" + 0);
        } else {
            serverConnection.printWriter.println("selectCategory:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectDish:" + 0);
        } else {
            serverConnection.printWriter.println("selectDish:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectReservation:" + 0);
        } else {
            serverConnection.printWriter.println("selectReservation:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectPreOrder:" + 0);
        } else {
            serverConnection.printWriter.println("selectPreOrder:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectEmployees:" + 0);
        } else {
            serverConnection.printWriter.println("selectEmployees:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectReports:" + 0);
        } else {
            serverConnection.printWriter.println("selectReports:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectCafeCoordinte:" + 0);
        } else {
            serverConnection.printWriter.println("selectCafeCoordinte:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectPositions:" + 0);
        } else {
            serverConnection.printWriter.println("selectPositions:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectOrders:" + 0);
        } else {
            serverConnection.printWriter.println("selectOrders:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
            serverConnection.printWriter.println("selectOrderList:" + 0);
        } else {
            serverConnection.printWriter.println("selectOrderList:" + id);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
    public static String delete(String table,int id) {
        serverConnection.printWriter.println("deleteFromTable:" +table+","+ id);
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
    public static String getValueToUpdate(String table,String what,int id) {
        serverConnection.printWriter.println("getValueToUpdate:" +table+","+what+","+ id);
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
        if(comand==null)
        {
            return "Ошибка";
        }
        serverConnection.printWriter.println(comand);
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
    public static ArrayList<ReportsGraph> getReportsGraph(String chart,String dateStart, String dateEnd) {
        if(dateStart == null || dateEnd==null)
        {
            serverConnection.printWriter.println("selectGrapgMonth:" + chart);
        }else {
            serverConnection.printWriter.println("selectGrapg:" + chart+","+dateStart+","+dateEnd);
        }
        try {
            if (serverConnection.objInputStream == null)
                serverConnection.objInputStream = new ObjectInputStream(serverConnection.connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Object object = serverConnection.objInputStream.readObject();
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
}
