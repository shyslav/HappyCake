package com.shyslav.resources;

import appmodels.localmodels.KeyValue;
import appmodels.localmodels.LocalRoles;
import com.shyslav.server.ServerCommands;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 21.05.2016.
 */
public class DomReader {
    private final ClassLoader classLoader = getClass().getClassLoader();
    public  ArrayList<LocalRoles> parseFunc(String tableName, String command, int id) {
        String labelName = null;
        String type = null;
        int maxLenght = 0;
        int minLenght = 0;
        Boolean emptyOrNot = null;
        String key = null;
        String value = null;
        ArrayList<LocalRoles> rules = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(String.valueOf(classLoader.getResource("data/databaseFields.xml")));
            NodeList personList = doc.getElementsByTagName(tableName);
            for (int i = 0 ; i < personList.getLength();i++)
            {
                Node node = personList.item(i);
                //System.out.println("Table Name : " + node.getNodeName());
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    //System.out.println("Table column: "+element.getAttribute("id"));
                    //Label для колонки
                    labelName = element.getElementsByTagName("labelName").item(0).getTextContent();
                    //Тип поля заполнения
                    type = element.getElementsByTagName("type").item(0).getTextContent();
                    //Максимальная длина поля
                    maxLenght = Integer.parseInt(element.getElementsByTagName("maxLenght").item(0).getTextContent());
                    //Минимальная длина поля
                    minLenght = Integer.parseInt(element.getElementsByTagName("minLenght").item(0).getTextContent());
                    //Возможно ли вернуть нулевой обьект
                    emptyOrNot = Boolean.valueOf(element.getElementsByTagName("emptyOrNot").item(0).getTextContent());
                    //Имя колонки
                    key = element.getAttribute("id"); //Имя колонки таблицы
                    if(command.equals("update")) {
                        value = ServerCommands.getValueToUpdate(tableName, element.getAttribute("id"), id);
                    }else
                    {
                        value = "";
                    }
                    //System.out.println(element.getElementsByTagName("labelName").item(0).getTextContent());
                    rules.add(new LocalRoles(labelName,type,maxLenght,minLenght,emptyOrNot,new KeyValue(key,value)));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return rules;
    }
}
