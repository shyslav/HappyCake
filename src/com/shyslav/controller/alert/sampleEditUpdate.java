package com.shyslav.controller.alert;

import com.shyslav.models.Roles;
import com.shyslav.resources.DomReader;
import com.shyslav.validation.UpdateInsertValid;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shyshkin Vladyslav on 21.05.2016.
 */
public class sampleEditUpdate  {

    private ArrayList<Roles> roles = new ArrayList<>();
    private Stage primaryStage;
    private String title;
    private String tableName;
    private String command;

    public sampleEditUpdate(Stage primaryStage, String title, String tableName, String command) {
        this.primaryStage = primaryStage;
        this.title = title;
        this.tableName = tableName;
        this.command = command;
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        roles = DomReader.parseFunc(tableName);
        Stage updateStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2);

        Map<String,TextField> textFieldMap = new HashMap<>();
        Map<String,Label> labelMap = new HashMap<>();
        Map<String,TextArea> textAreaMap = new HashMap<>();
        for (int i = 0 ; i< roles.size();i++) {
            if(roles.get(i).getText().equals("text"))
            {
                labelMap.put(roles.get(i).getKeyValue().getKey(),new Label(roles.get(i).getLabelName()));
                textFieldMap.put(roles.get(i).getKeyValue().getKey(), new TextField());
            }else if(roles.get(i).getText().equals("textArea"))
            {
                labelMap.put(roles.get(i).getKeyValue().getKey(),new Label(roles.get(i).getLabelName()));
                textAreaMap.put(roles.get(i).getKeyValue().getKey(), new TextArea("test"));
                textAreaMap.get(roles.get(i).getKeyValue().getKey()).setWrapText(true);
            }
        }
        for (int i = 0; i<roles.size();i++)
        {
            GridPane.setHalignment(labelMap.get(roles.get(i).getKeyValue().getKey()), HPos.RIGHT);
            gridPane.add(labelMap.get(roles.get(i).getKeyValue().getKey()),0,i);
            if(roles.get(i).getText().equals("textArea")) {
                GridPane.setHalignment(textAreaMap.get(roles.get(i).getKeyValue().getKey()), HPos.RIGHT);
                gridPane.add(textAreaMap.get(roles.get(i).getKeyValue().getKey()),1,i);
            }else if (roles.get(i).getText().equals("text")){
                GridPane.setHalignment(textFieldMap.get(roles.get(i).getKeyValue().getKey()), HPos.RIGHT);
                gridPane.add(textFieldMap.get(roles.get(i).getKeyValue().getKey()),1,i);
            }
        }
        // Save button
        Button saveButt = new Button("Save");

        saveButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                for (int i = 0 ; i < roles.size();i++)
                {
                    if(roles.get(i).getText().equals("textArea")) {
                        if (!UpdateInsertValid.MaxMinLenght(roles.get(i).getMaxLenght(), roles.get(i).getMinLenght(), textAreaMap.get(roles.get(i).getKeyValue().getKey()).getText())) {
                            //alert textField не удовлетворяет параметрам
                        } else {
                            roles.get(i).getKeyValue().setValue(textAreaMap.get(roles.get(i).getKeyValue().getKey()).getText());
                        }
                    }else if (roles.get(i).getText().equals("text"))
                    {
                        if(!UpdateInsertValid.MaxMinLenght(roles.get(i).getMaxLenght(),roles.get(i).getMinLenght(),textFieldMap.get(roles.get(i).getKeyValue().getKey()).getText()))
                        {
                            //alert textField не удовлетворяет параметрам
                        }else {
                            roles.get(i).getKeyValue().setValue(textFieldMap.get(roles.get(i).getKeyValue().getKey()).getText());
                        }
                    }
                }
                roles.size();
                createInsertCommand();
                createUpdateCommand();
            }
        });

        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridPane.add(saveButt, 1, roles.size()+1);

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.getChildren().addAll(gridPane);
        anchorpane.setRightAnchor(gridPane,0.0);
        anchorpane.setLeftAnchor(gridPane,0.0);
        anchorpane.setTopAnchor(gridPane,0.0);


        Scene scene = new Scene(anchorpane);
        updateStage.initModality(Modality.WINDOW_MODAL);
        updateStage.initOwner(primaryStage);
        updateStage.setResizable(false);
        updateStage.setTitle(title);
        updateStage.setScene(scene);
        updateStage.show();
    }
    private void createInsertCommand()
    {
        String table = "insert";
        String comads = table + ":";
        for (int i = 0; i < roles.size();i++) {
            comads += roles.get(i).getKeyValue().getValue() + ",";
        }
        System.out.println(comads);
    }
    private void createUpdateCommand()
    {
        String table = "update";
        String comands = table + ":";
        for (int i = 0; i < roles.size();i++)
        {
            comands += roles.get(i).getKeyValue().getKey() +" SET " + roles.get(i).getKeyValue().getValue() + ",";
        }
        System.out.println(comands);
    }
    private int getSize()
    {
        int textArea = 0;
        int textField = 0;
        for (int i = 0 ; i < roles.size();i++)
        {
            if(roles.get(i).getText().equals("textArea"))
            {
                textArea++;
            }
            else if(roles.get(i).getText().equals("text"))
            {
                textField++;
            }
        }
        return 165*textArea+35*textField + 35;
    }

}
