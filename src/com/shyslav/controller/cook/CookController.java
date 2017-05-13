package com.shyslav.controller.cook;

import com.happycake.sitemodels.DishesList;
import com.happycake.sitemodels.Order;
import com.shyslav.controller.alert.LazyJavaFXAlert;
import com.shyslav.start.StartDesktopApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 25.05.2016.
 */
public class CookController {
    @FXML
    private WebView webView1;
    @FXML
    private WebView webView2;
    @FXML
    private WebView webView3;
    @FXML
    private WebView webView4;
    @FXML
    private WebView webView5;
    @FXML
    private WebView webView6;
    @FXML
    private WebView webView7;
    @FXML
    private WebView webView8;
    ArrayList<WebView> webViews = new ArrayList<>();
    String[] colors = {"#fa9994", "#ffb69e", "#ffd5b3", "#fce0cb", "#fff9c2", "#f6ffa9", "#d6ff8d", "#9bff7c"};
    private CookActionHelper model;
    private DishesList dishes;

    @FXML
    private void initialize() {
        StartDesktopApplication.userEntity.getUserBean().waitLoad();
        model = new CookActionHelper(this);
        dishes = StartDesktopApplication.userEntity.getUserBean().getDishesList();

        webViews.add(webView1);
        webViews.add(webView2);
        webViews.add(webView3);
        webViews.add(webView4);
        webViews.add(webView5);
        webViews.add(webView6);
        webViews.add(webView7);
        webViews.add(webView8);
        generate();
    }

    public void updateOrders() {
        Platform.runLater(this::generate);
    }

    public void generate() {
        String style = "<html><head>" +
                "<style> \n" +
                "th, td { \n" +
                "padding: 5px; \n" +
                "text-align: left;\n" +
                "}\n" +
                "th{font-size: 16px;}\n" +
                "hr {\n" +
                "    border: 0;\n" +
                "    height: 1px;\n" +
                "    background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));\n" +
                "}" +
                "body{background : #9bff7c;font-size: 30px;text-align:center;}" +
                "</style> \n" +
                "</head> ";

        for (int i = 0; i < webViews.size(); i++) {
            Order orderFromQueue;
            StringBuilder order = new StringBuilder();
            order.append("<body style='background :").append(colors[i]).append(";font-size: 30px;text-align:center;'>");
            if (model.getQueue().size() - 1 < i) {
                orderFromQueue = null;
                order.append(" Нет заказа ");
            } else {
                orderFromQueue = model.getQueue().get(i);
                order.append(" Заказ № ").append(String.valueOf(orderFromQueue.getId()));
            }
            order.append("</body>");
            String table = "<div align=\"center\">" +
                    "<table style='background :" + colors[i] + ";'>\n" +
                    " <thead>   " +
                    generateTable(orderFromQueue) +
                    "</tbody>\n" +
                    "</table>" +
                    "</div>" +
                    "</body>" +
                    "<html>";
            WebEngine engine = webViews.get(i).getEngine();
            engine.loadContent(style + order + table);
        }
    }

    /**
     * Generate table column by order id
     *
     * @param order order
     * @return table column
     */
    private String generateTable(Order order) {
        if (order == null) {
            return "Пусто";
        } else {
            StringBuilder table = new StringBuilder();
            for (int i = 0; i < order.getOrderDetails().size(); i++) {
                table.append("<tr>");
                table.append("<td>")
                        .append(dishes.getByID(order.getOrderDetails().get(i).getDishID()).getName())
                        .append("</td>")
                        .append("<td>")
                        .append(order.getOrderDetails().get(i).getAmount())
                        .append("</td>");
                table.append("</tr>");
            }
            return table.toString();
        }
    }


    /**
     * Event on mouse click into web view
     *
     * @param event action event
     */
    public void mouseClickedWeb(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (StartDesktopApplication.userEntity.getEmp().getPositionID() == 3) {
                WebView source = (WebView) event.getSource();
                int index = Integer.parseInt(source.getId().replaceFirst(".*?(\\d+).*", "$1")) - 1;

                if (model.getQueue().size() > index) {
                    if (LazyJavaFXAlert.confirmAlert("Подтверждение закрытия", "Ваша зарплата не безгранична", "Вы точно выполнили этот заказ?")) {
                        model.closeOrder(index);
                        updateOrders();
                    }
                } else {
                    LazyJavaFXAlert.alert("Ошибка действия", "Не возможно закрыть пустой заказ", null, Alert.AlertType.ERROR);
                }
            }
        }
    }
}

