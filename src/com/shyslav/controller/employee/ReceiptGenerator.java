package com.shyslav.controller.employee;

import com.shyslav.controllers.alerts.JavaFxSimpleAlert;
import com.sukhaniuk.func.HtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

/**
 * @author Shyshkin Vladyslav on 20.05.17.
 */
public class ReceiptGenerator {
    private static final String DEFAULT_MONEY_IND = " грн. ";

    /**
     * Generate receipt by purchase order list
     *
     * @param list purchase order list
     * @return html
     * @throws InterruptedException
     */
    public static String generateReceipt(int orderID, PurchaseOrderList list) {
        Document htmlFile = null;
        File input = new File(HtmlParser.class.getResource("/html/receipt.html").getFile());
        try {
            htmlFile = Jsoup.parse(input, "UTF-8");
        } catch (IOException e) {
            JavaFxSimpleAlert.FileNotFound(e);
        }
        //set order number
        Element orderNumber = htmlFile.getElementById("orderNumber");
        orderNumber.html(String.valueOf(orderID));

        //generate receipt
        Element body = htmlFile.getElementById("tbody");
        StringBuilder sb = new StringBuilder();
        for (PurchaseOrder order : list) {
            sb.append("<tr>")
                    .append("<td>").append(order.getDish().getName()).append("</td>")
                    .append("<td>")
                    .append("<p>")
                    .append(order.getDish().getPrice())
                    .append("<small> X </small>").append(order.getCount())
                    .append("</p>")
                    .append("</td>")
                    .append("<td>").append(order.getSum()).append(DEFAULT_MONEY_IND).append("</td>")
                    .append("</tr>");
        }
        body.append(sb.toString());

        //append total sum
        sb = new StringBuilder();
        sb.append("<tr>")
                .append("<td>")
                .append("<b>Сумма к оплате:</b>")
                .append("</td>")
                .append("<td></td>")
                .append("<td>").append(list.getTotalSum()).append(DEFAULT_MONEY_IND).append("</td>")
                .append("</tr>");
        body.append(sb.toString());


        if (list.isNeedCook()) {
            //generate to cook
            sb = new StringBuilder();
            Element toCookBody = htmlFile.getElementById("tbodycook");
            for (PurchaseOrder order : list) {
                if (order.getDish().isNeedCook()) {
                    sb.append("<tr>")
                            .append("<td>").append(order.getDish().getName()).append("</td>")
                            .append("<td>")
                            .append(order.getDish().getAmount())
                            .append("</td>")
                            .append("<td>").append(order.getCount()).append("</td>")
                            .append("</tr>");
                }
            }
            toCookBody.append(sb.toString());
        } else {
            Element message = htmlFile.getElementById("toCookBlock");
            message.remove();
        }
        return htmlFile.toString();
    }
}

