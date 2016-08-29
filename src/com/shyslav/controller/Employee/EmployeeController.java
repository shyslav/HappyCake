package com.shyslav.controller.Employee;

import appmodels.localmodels.LocalServerCassir;
import com.shyslav.controller.alert.LazyConfirmDialog;
import com.shyslav.controller.alert.LazyAlert;
import appmodels.*;
import com.shyslav.server.ServerCommands;
import data.DataUpdater;
import javafx.event.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EmployeeController {
    private Map<_Dish, TextField> mapTextFields = new HashMap<>();
    private ArrayList<LocalServerCassir> cas = new ArrayList<>();
    private ArrayList<TreeItem> treeItems = new ArrayList<>();
    private ArrayList<_OrderList> orderLists = new ArrayList<>();
    private GridPane gridPane = new GridPane();
    @FXML
    private AnchorPane AnchorTest;
    @FXML
    private TreeView treeView;
    @FXML
    private VBox VboxButtons;
    @FXML
    private GridPane gridPaneCheck;
    @FXML
    private ScrollPane scPane;
    @FXML
    private Button btnAll;
    @FXML
    private ScrollPane scrollTest;

    @FXML
    private void initialize() {
        //Авторазширение ScrollPane
        scrollTest.setFitToWidth(true);
        handlerTreeView();
        generateList();
        if (cas.size() == 0) {
            LazyAlert.ConnectionError();
        }
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        generateVbox(gridPane);


        scPane.setContent(gridPane);

        for (LocalServerCassir cs : cas) {
            Button btn = generateBtnCategory(cs.getName());
            VboxButtons.getChildren().add(btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println(btn.getText());
                    ArrayList<LocalServerCassir> clone = new ArrayList<LocalServerCassir>(cas);
                    ArrayList<LocalServerCassir> tmp = new ArrayList<>();
                    for (int i = 0; i < cas.size(); i++) {
                        if (cas.get(i).getName().equals(btn.getText())) {
                            tmp.add(cas.get(i));
                        }
                    }
                    cas = tmp;
                    ReUse();
                    cas = clone;
                }
            });
        }
    }

    private void ReUse() {
        mapTextFields.clear();
        gridPane.getChildren().clear();
        generateVbox(gridPane);
        scPane.setContent(gridPane);
    }

    private void handlerTreeView() {
        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    TreeItem item = (TreeItem) treeView.getSelectionModel().getSelectedItem();
                    item.setExpanded(true);
                    if (item.getValue().equals("Удалить блюдо")) {
                        for (int i = 0; i < orderLists.size(); i++) {
                            if (orderLists.get(i).getDishName().equals(item.getParent().getValue())) {
                                if (LazyConfirmDialog.confirmAlert("Удаление", "Вы действиетельно хотите удалить " + item.getParent().getValue() + " из заказа", "Действие не возвратимо")) {
                                    orderLists.remove(i);
                                    addToTree();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void addToTree() {
        //Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("img/5.jpg")));
        treeItems.clear();
        TreeItem<String> root = new TreeItem<String>("Заказ");
        for (int i = 0; i < orderLists.size(); i++) {
            TreeItem<String> tempos = new TreeItem<String>(orderLists.get(i).getDishName()/*,rootIcon*/);
            tempos.getChildren().addAll(new TreeItem<String>("Количество: " + orderLists.get(i).getAmount()),
                    new TreeItem<String>("Цена: " + orderLists.get(i).getPrice()),
                    new TreeItem<String>("Удалить блюдо"));
            tempos.setExpanded(true);
            treeItems.add(tempos);
        }
        for (int i = 0; i < treeItems.size(); i++) {
            root.getChildren().addAll(treeItems.get(i));
        }
        treeView.setShowRoot(false);
        treeView.setRoot(root);
        treeView.setEditable(false);
    }

    private void generateVbox(GridPane gridPane) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int vertical = 0;
        int horizontal = 0;
        int columnSize = screenSize.width / 320;
        for (int i = 0; i < cas.size(); i++) {
            for (int j = 0; j < cas.get(i).getDish().size(); j++) {
                VBox vBox = new VBox();
                vBox.setStyle("-fx-background-color:white; -fx-background-radius: 5px;");
                vBox.setPadding(new Insets(5, 5, 5, 5));
                vBox.getChildren().addAll(generateLabel(cas.get(i).getDish().get(j).getName()), generateImageVew(cas.get(i).getDish().get(j).getImage()), generatePlusMinus(cas.get(i).getDish().get(j)), generateBtnAdd("Добавить", cas.get(i).getDish().get(j)));
                if (vertical == columnSize) {
                    horizontal++;
                    vertical = 0;
                }
                gridPane.add(vBox, vertical++, horizontal);
            }
        }
    }

    private void generateList() {
        cas = ServerCommands.getCassirData();
    }


    private Label generateLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font("Cambria", 18));
        label.setPrefWidth(210);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private ImageView generateImageVew(String path) {
        path = path.replace("/images", "img");
        ImageView imgView = new ImageView(new Image(String.valueOf(DataUpdater.class.getResource(path))));
        imgView.setFitWidth(210);
        imgView.setFitHeight(170);
        return imgView;
    }

    private Button generateBtnAdd(String name, _Dish ds) {
        Button btnSubmit = new Button(name);
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(mapTextFields.get(ds).getText());
                double sum = Integer.parseInt(mapTextFields.get(ds).getText()) * ds.getPrice();
                for (_OrderList od : orderLists) {
                    if (od.getDishID() == ds.getId()) {
                        //текущее кол-во + в текстовом поле
                        od.setAmount(od.getAmount() + Integer.parseInt(mapTextFields.get(ds).getText()));
                        //сумма = прайс * на новое кол-во
                        sum = od.getAmount() * ds.getPrice();
                        //изменить сумму
                        od.setPrice(sum);
                        addToTree();
                        return;
                    }
                }
                orderLists.add(new _OrderList(0, 0, ds.getId(), ds.getName(), Integer.parseInt(mapTextFields.get(ds).getText()), sum));
                addToTree();
            }
        });
        btnSubmit.setPrefHeight(30);
        btnSubmit.setMaxWidth(Double.MAX_VALUE);
        return btnSubmit;
    }

    private Button generateBtnCategory(String name) {
        Button btnSubmit = new Button(name);
        btnSubmit.setPrefHeight(50);
        btnSubmit.setMaxWidth(Double.MAX_VALUE);
        return btnSubmit;
    }

    private HBox generatePlusMinus(_Dish ds) {
        HBox hb = new HBox();
        hb.setPadding(new Insets(5, 0, 5, 0));

        Button btnPlus = new Button("+");
        btnPlus.setStyle("-fx-font-weight: 800; -fx-font-size: 14");
        btnPlus.setPrefSize(30, 30);

        Button btnMinus = new Button("-");
        btnMinus.setStyle("-fx-font-weight: 800; -fx-font-size: 14");
        btnMinus.setPrefSize(30, 30);

        TextField amount = new TextField("1");
        amount.setPrefHeight(30);
        amount.setAlignment(Pos.CENTER);

        mapTextFields.put(ds, amount);

        btnPlus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int tmp = Integer.parseInt(amount.getText()) + 1;
                amount.setText(String.valueOf(tmp));
            }
        });
        btnMinus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int tmp = Integer.parseInt(amount.getText()) - 1;
                if (tmp >= 1) {
                    amount.setText(String.valueOf(tmp));
                }
            }
        });

        hb.setMargin(amount, new Insets(0, 1, 0, 1));
        hb.getChildren().addAll(btnPlus, amount, btnMinus);

        return hb;
    }

    public void checkClose(Event event) {
        if (orderLists.size() != 0) {
            String message = "";
            for (int i = 0; i < orderLists.size(); i++) {
                message += orderLists.get(i).getDishName() + " - " + orderLists.get(i).getAmount() + " шт. - " + orderLists.get(i).getPrice() + " грн.\n";
            }
            double summ = sum(orderLists);
            if (LazyConfirmDialog.confirmAlert("Подтвердить заказ на сумму " + summ + " гривен", message, "Закрить заказ?")) {
                String str = ServerCommands.cassirSent(orderLists, summ);
                LazyConfirmDialog.fifthSecondAlert("Заказ принят", str);
                orderLists.clear();
                addToTree();
                ReUse();
            }
        } else {
            LazyAlert sa = new LazyAlert("Ошибка", "Чек пустой", "Чек не должен быть пустой, добавьте в заказ блюда", Alert.AlertType.ERROR);
        }
    }

    public void btnALlClicked(Event event) {
        ReUse();
    }

    private double sum(ArrayList<_OrderList> ord) {
        double summ = 0.0;
        for (int i = 0; i < ord.size(); i++) {
            summ += ord.get(i).getPrice();
        }
        return summ;
    }
}
