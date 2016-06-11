package com.shyslav.controller.Cook;

import com.shyslav.controller.alert.confirmAlert;
import com.shyslav.controller.alert.sampleAlert;
import com.shyslav.server.comands;
import com.shyslav.server.serverConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 25.05.2016.
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

    @FXML
    private void initialize() {
        if (CookModel.list.size() == 0) {
            CookModel.initialize();
        }
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
    public void updateOrders()
    {
        Platform.runLater(()->generate());
    }
    public void generate() {
        int listSize = 0;
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
            String orderNumber = CookModel.list.size()>listSize?String.valueOf(CookModel.list.get(listSize).getOrderID()):"Нет заказа";
            String order = "<body style='background :" + colors[i] + ";font-size: 30px;text-align:center;'>Замовлення №"+orderNumber+" <hr> </body>";
            String table =  "<div align=\"center\">" +
                    "<table style='background :"+ colors[i] +";'>\n" +
                    " <thead>   " +
                    generateTable(listSize++) +
                    "</tbody>\n" +
                    "</table>" +
                    "</div>" +
                    "</body>" +
                    "<html>";
            WebEngine engine = webViews.get(i).getEngine();
            engine.loadContent(style+order+table);
        }
    }

    private String generateTable(int indf) {
        if (CookModel.list.size() != 0 && CookModel.list.size() > indf) {
            String table = "";
            for (int j = 0; j < CookModel.list.get(indf).getOrderListCooks().size(); j++) {
                table += "<tr>";
                table += "<td>" + CookModel.list.get(indf).getOrderListCooks().get(j).getDishName() + "</td>" +
                        "<td>" + CookModel.list.get(indf).getOrderListCooks().get(j).getAmount() + "</td>";
                table += "</tr>";
            }
            return table;
        } else
        {
            return "Пусто";
        }

    }


    public void mouseClickedWeb(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (serverConnection.emp.get(0).getPositionID() == 3) {
                Object source = event.getSource();
                WebView clicked = (WebView) source;
                System.out.println(clicked.getId());
                int firstNumber = Integer.parseInt(clicked.getId().replaceFirst(".*?(\\d+).*", "$1")) - 1;
                System.out.println(firstNumber);
                if (confirmAlert.confirmAlert("Подтверждение закрытия", "Ваша зарплата не безгранична", "Вы точно выполнили этот заказ?")) {
                    if (CookModel.list.size() > firstNumber) {
                        comands.cookCompliteOrder(CookModel.list.get(firstNumber).getOrderID());
                    } else {
                        sampleAlert sa = new sampleAlert("Ошибка действия", "Не возможно закрыть пустой заказ", null, Alert.AlertType.ERROR);
                    }
                }
            }
            else
            {
                sampleAlert.RuleError();
            }
        }
    }
}
