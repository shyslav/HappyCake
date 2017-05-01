package com.shyslav.controller.alert;

import appmodels.localmodels.LocalRoles;
import com.shyslav.resources.DomReader;
import com.shyslav.server.ServerCommands;
import com.shyslav.start.StartApplication;
import com.shyslav.validation.UpdateInsertValid;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Current class open edit and update table data dialog
 *
 * @author Shyshkin Vladyslav on 21.05.2016.
 */
@SuppressWarnings("unused")
public class LazyEditUpdate {
    private final DomReader domReader;
    //list of database fields rules
    private ArrayList<LocalRoles> roles = new ArrayList<>();
    private Stage updateStage = new Stage();
    //link to main stage
    private Stage primaryStage;
    //stage title
    private String title;
    private String tableName;
    private String command;
    //element id in database
    private int id;

    public LazyEditUpdate(Stage primaryStage, String title, String tableName, String command, int id) {
        this.primaryStage = primaryStage;
        this.title = title;
        this.tableName = tableName;
        this.command = command;
        this.id = id;
        this.domReader = new DomReader();
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate update and edit form
     */
    private void start() {
        //read from xml table rules
        roles = domReader.parseFunc(tableName, command, id);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2);

        //map to get fx element by db table name
        Map<String, TextField> textFieldMap = new HashMap<>();
        Map<String, Label> labelMap = new HashMap<>();
        Map<String, TextArea> textAreaMap = new HashMap<>();

        //generate map data
        for (LocalRoles role : roles) {
            if (role.getText().equals("text")) {
                labelMap.put(role.getKeyValue().getKey(), new Label(role.getLabelName()));
                textFieldMap.put(role.getKeyValue().getKey(), new TextField(role.getKeyValue().getValue()));
            } else if (role.getText().equals("textArea")) {
                labelMap.put(role.getKeyValue().getKey(), new Label(role.getLabelName()));
                textAreaMap.put(role.getKeyValue().getKey(), new TextArea(role.getKeyValue().getValue()));
                textAreaMap.get(role.getKeyValue().getKey()).setWrapText(true);
            }
        }

        //generate form
        for (int i = 0; i < roles.size(); i++) {
            //set label
            GridPane.setHalignment(labelMap.get(roles.get(i).getKeyValue().getKey()), HPos.LEFT);
            gridPane.add(labelMap.get(roles.get(i).getKeyValue().getKey()), 0, i);
            if (roles.get(i).getText().equals("textArea")) {
                //set textaread element
                GridPane.setHalignment(textAreaMap.get(roles.get(i).getKeyValue().getKey()), HPos.RIGHT);
                gridPane.add(textAreaMap.get(roles.get(i).getKeyValue().getKey()), 1, i);
            } else if (roles.get(i).getText().equals("text")) {
                //set textfield element
                GridPane.setHalignment(textFieldMap.get(roles.get(i).getKeyValue().getKey()), HPos.RIGHT);
                gridPane.add(textFieldMap.get(roles.get(i).getKeyValue().getKey()), 1, i);
            }
        }
        // generate save btn
        Button saveButt = new Button("Save");
        // error list
        ArrayList<String> errors = new ArrayList<>();
        //set button handler
        saveButt.setOnAction(e -> {
            errors.clear();
            for (LocalRoles role : roles) {
                if (role.getText().equals("textArea")) {
                    if (!UpdateInsertValid.MaxMinLenght(role.getMaxLenght(), role.getMinLenght(), textAreaMap.get(role.getKeyValue().getKey()).getText())) {
                        //alert textArea не удовлетворяет параметрам
                        errors.add("Поле " + role.getLabelName() + " не удовлетворяет требованиям");
                    } else {
                        role.getKeyValue().setValue(textAreaMap.get(role.getKeyValue().getKey()).getText());
                    }
                } else if (role.getText().equals("text")) {
                    if (!UpdateInsertValid.MaxMinLenght(role.getMaxLenght(), role.getMinLenght(), textFieldMap.get(role.getKeyValue().getKey()).getText())) {
                        //alert textField не удовлетворяет параметрам
                        errors.add("Поле " + role.getLabelName() + " не удовлетворяет требованиям");
                    } else {
                        role.getKeyValue().setValue(textFieldMap.get(role.getKeyValue().getKey()).getText());
                    }
                }
            }
            if (errors.size() == 0) {
                switch (command) {
                    case "update":
                        createUpdateCommand();
                        break;
                    case "insert":
                        createInsertCommand();
                        break;
                }
            } else {
                LazyJavaFXAlert.alert("Ошибка заполнения", "Дейтсвие не возможно, допущенны следующие ошибки", String.join("\n", errors), Alert.AlertType.ERROR);
            }
        });

        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridPane.add(saveButt, 1, roles.size() + 1);

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.getChildren().addAll(gridPane);
        AnchorPane.setRightAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setTopAnchor(gridPane, 0.0);


        Scene scene = new Scene(anchorpane);
        updateStage.initModality(Modality.WINDOW_MODAL);
        updateStage.initOwner(primaryStage);
        updateStage.setResizable(false);
        updateStage.setTitle(title);
        updateStage.setScene(scene);
        updateStage.show();
    }

    /**
     * Action to generate insert command to server
     */
    private void createInsertCommand() {
        String com = "insert:" + tableName + ",";
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getKeyValue().getValue().contains("'")) {
                roles.get(i).getKeyValue().setValue(roles.get(i).getKeyValue().getValue().replace("'", "`"));
            }
            if (roles.get(i).getKeyValue().getValue().contains(":")) {
                roles.get(i).getKeyValue().setValue(roles.get(i).getKeyValue().getValue().replace(":", "|"));
            }
            if (i == roles.size() - 1) {
                com += "'" + roles.get(i).getKeyValue().getValue() + "'";
            } else {
                com += "'" + roles.get(i).getKeyValue().getValue() + "',";
            }
        }
        comandEvent(com);
    }

    /**
     * Action to generate update command to server
     */
    private void createUpdateCommand() {
        String com = "update:" + tableName + "," + id + ",";
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getKeyValue().getValue().contains("'")) {
                roles.get(i).getKeyValue().setValue(roles.get(i).getKeyValue().getValue().replace("'", "`"));
            }
            if (roles.get(i).getKeyValue().getValue().contains(":")) {
                roles.get(i).getKeyValue().setValue(roles.get(i).getKeyValue().getValue().replace(":", "|"));
            }
            if (i == roles.size() - 1) {
                com += roles.get(i).getKeyValue().getKey() + " = '" + roles.get(i).getKeyValue().getValue() + "'";
            } else {
                com += roles.get(i).getKeyValue().getKey() + " = '" + roles.get(i).getKeyValue().getValue() + "',";
            }
        }
        comandEvent(com);
    }

    /**
     * Action to sent command to server
     *
     * @param com insert or update command
     */
    private void comandEvent(String com) {
        String result = ServerCommands.executeComand(com);
        if (result == null) {
            LazyJavaFXAlert.connectionError();
        } else if (result.equals("done")) {
            StartApplication.controllerAdminItems.ReInit();
            closeScene();
        } else {
            LazyJavaFXAlert.alert("Ошибка", "Дейтсвие не возможно, обратитесь к разработчикам", result, Alert.AlertType.ERROR);
        }
        System.out.println(com);
    }

    /**
     * Action to close current stage
     */
    private void closeScene() {
        updateStage.close();
    }

    /**
     * Get scene size
     *
     * @return scene size
     */
    private int getSize() {
        int textArea = 0;
        int textField = 0;
        for (LocalRoles role : roles) {
            if (role.getText().equals("textArea")) {
                textArea++;
            } else if (role.getText().equals("text")) {
                textField++;
            }
        }
        return 165 * textArea + 35 * textField + 35;
    }

}
