package com.shyslav.controller.employee;

import com.happycake.sitemodels.*;
import com.shyslav.UserBean;
import com.shyslav.utils.LazyJavaFXAlert;
import com.shyslav.defaults.HappyCakeResponse;
import com.shyslav.start.StartDesktopApplication;
import javafx.application.Platform;
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
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.happycake.sitemodels.HappyCakeNotifications.UPDATE_CATEGORIES;
import static com.happycake.sitemodels.HappyCakeNotifications.UPDATE_DISHES;

@SuppressWarnings("unused")
public class EmployeeController {
    private Map<Dish, TextField> mapTextFields = new HashMap<>();
    private ArrayList<TreeItem> treeItems = new ArrayList<>();
    private final PurchaseOrderList purchaserOrder = new PurchaseOrderList();
    private Employees employees;
    private DishesList dishesList;
    private GridPane gridPane = new GridPane();
    /**
     * Check tree view
     */
    @FXML
    private TreeView treeView;

    /**
     * Categories buttons vertically box
     */
    @FXML
    private VBox categoriesButtonsVbox;

    /**
     * Central scroll pane
     * In this pane show all dish and categories
     */
    @FXML
    private ScrollPane centralScrollPane;
    /**
     * Show all categories button
     */
    @FXML
    private Button btnShowAllCategories;
    /**
     * Categories scroll pane
     */
    @FXML
    private ScrollPane categoriesScroll;

    /**
     * Right anchor pane with treeview and check
     */
    @FXML
    private AnchorPane rightAnchorPane;


    /**
     * FXML controller constructor
     */
    @FXML
    private void initialize() {
        StartDesktopApplication.userEntity.getUserBean().waitLoad();

        //reload dishes and categories list
        StartDesktopApplication.userEntity.getUserBean().reloadByType(UserBean.RELOAD_TYPES.CATEGORIES);
        StartDesktopApplication.userEntity.getUserBean().reloadByType(UserBean.RELOAD_TYPES.DISHES);


        this.employees = StartDesktopApplication.userEntity.getEmp();
        CategoriesList categories = StartDesktopApplication.userEntity.getUserBean().getCategoriesList();
        this.dishesList = StartDesktopApplication.userEntity.getUserBean().getDishesList();

        if (employees == null) {
            LazyJavaFXAlert.connectionError();
        }

        centralScrollPane.setFitToWidth(true);
        handlerTreeView();

        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        gridPane.getColumnConstraints().addAll(column1, column2, column3);
        reinitializeDishesList(0);

        //generate categories buttons
        for (Category category : categories) {
            Button btn = generateBtnCategory(category.getName());
            btn.setOnAction(event -> reinitializeDishesList(category.getId()));
            categoriesButtonsVbox.getChildren().add(btn);
        }

        initializePingerHandlers();
    }

    /**
     * Initialize pinger handlers
     */
    private void initializePingerHandlers() {
        //register update categories listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_CATEGORIES, (event) -> {
            Platform.runLater(() -> {
                boolean confirmAlert = LazyJavaFXAlert.confirmAlert(
                        "Изменились категории",
                        "Обновить сейчас?",
                        "Если вы сейчас осбуливаете клиента, нажмите Отмена. " +
                                "Сразу после ослуживания нажмите кнопку Домой и зайдите обратно. " +
                                "Тогда изменения вступят в силу"
                );
                if (confirmAlert) {
                    StartDesktopApplication.userEntity.getUserBean().reloadByType(UserBean.RELOAD_TYPES.CATEGORIES);
                    initialize();
                }
            });
        });
        //register update dishes listener
        StartDesktopApplication.userEntity.registerPingerListener(UPDATE_DISHES, (event) -> {
            Platform.runLater(() -> {
                boolean confirmAlert = LazyJavaFXAlert.confirmAlert(
                        "Изменились блюда",
                        "Обновить сейчас?",
                        "Если вы сейчас осбуливаете клиента, нажмите Отмена. " +
                                "Сразу после ослуживания нажмите кнопку Домой и зайдите обратно. " +
                                "Тогда изменения вступят в силу");
                if (confirmAlert) {
                    StartDesktopApplication.userEntity.getUserBean().reloadByType(UserBean.RELOAD_TYPES.DISHES);
                    initialize();
                }
            });
        });
    }

    /**
     * Reinitialize central dish view
     *
     * @param categoryID category id
     */
    private void reinitializeDishesList(int categoryID) {
        mapTextFields.clear();
        gridPane.getChildren().clear();
        generateDishesCentralView(categoryID);
        centralScrollPane.setContent(gridPane);
    }

    /**
     * Tree view mouse double click
     */
    private void handlerTreeView() {
        treeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem item = (TreeItem) treeView.getSelectionModel().getSelectedItem();
                item.setExpanded(true);
                if (item.getValue().equals("Удалить блюдо")) {
                    for (PurchaseOrder order : purchaserOrder) {
                        //check if dish name equal root node
                        if (order.getDish().getName().equals(item.getParent().getValue())) {
                            if (LazyJavaFXAlert.confirmAlert("Удаление", "Вы действиетельно хотите удалить " + item.getParent().getValue() + " из заказа", "Действие не возвратимо")) {
                                //remove order
                                purchaserOrder.remove(order);
                                //addCount tree
                                addToTree();
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Add dish to tree
     */
    private void addToTree() {
        treeItems.clear();
        TreeItem<String> root = new TreeItem<>("Заказ");
        for (PurchaseOrder element : purchaserOrder) {
            TreeItem<String> itemTreeRoot = new TreeItem<>(element.getDish().getName());
            itemTreeRoot.getChildren().addAll(new TreeItem<>("Количество: " + element.getCount()),
                    new TreeItem<>("Цена: " + element.getSum()),
                    new TreeItem<>("Удалить блюдо"));
            itemTreeRoot.setExpanded(true);
            treeItems.add(itemTreeRoot);
        }

        for (TreeItem treeItem : treeItems) {
            root.getChildren().addAll(treeItem);
        }
        treeView.setShowRoot(false);
        treeView.setRoot(root);
        treeView.setEditable(false);
    }


    /**
     * Generate dishes central view
     *
     * @param categoryID
     */
    private void generateDishesCentralView(int categoryID) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int vertical = 0;
        int horizontal = 0;
        int columnSize = screenSize.width / 320;

        //get dishes list by category id
        DishesList list;
        if (categoryID == 0) {
            //load all dishes
            list = dishesList;
        } else {
            //load dishes by category
            list = dishesList.getByCategoryId(categoryID);
        }

        //load dishes vbox
        for (Dish dish : list) {
            VBox vBox = new VBox();
            vBox.setStyle("-fx-background-color:white; -fx-background-radius: 5px;");
            vBox.setPadding(new Insets(5, 5, 5, 5));
            vBox.getChildren().addAll(generateLabel(dish.getName()), generateImageVew(dish.getImage()), generatePlusMinus(dish), generateBtnAdd("Добавить", dish));
            if (vertical == columnSize) {
                horizontal++;
                vertical = 0;
            }
            gridPane.add(vBox, vertical++, horizontal);
        }
    }

    /**
     * Generate dish label
     *
     * @param name dish name
     * @return dish label
     */
    private Label generateLabel(String name) {
        Label label = new Label(name);
        label.setFont(new Font("Cambria", 18));
        label.setPrefWidth(210);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    /**
     * Generate dish image
     *
     * @param imageBytes image bytes
     * @return dish image view
     */
    private ImageView generateImageVew(byte[] imageBytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        Image image = new Image(bis);
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(210);
        imgView.setFitHeight(170);
        return imgView;
    }

    /**
     * Generate add to check button
     *
     * @param name button name
     * @param dish dish entity
     * @return generated button
     */
    private Button generateBtnAdd(String name, Dish dish) {
        Button btnSubmit = new Button(name);
        btnSubmit.setOnAction(event -> {
            int count = Integer.parseInt(mapTextFields.get(dish).getText());
            purchaserOrder.add(new PurchaseOrder(dish, count));
            addToTree();
        });
        btnSubmit.setPrefHeight(30);
        btnSubmit.setMaxWidth(Double.MAX_VALUE);
        return btnSubmit;
    }

    /**
     * Generate button for category
     *
     * @param name button name
     * @return button
     */
    private Button generateBtnCategory(String name) {
        Button btnSubmit = new Button(name);
        btnSubmit.setMinWidth(rightAnchorPane.getPrefWidth());
        btnSubmit.setMaxWidth(rightAnchorPane.getPrefWidth());
        return btnSubmit;
    }

    /**
     * Generate dish count form
     *
     * @param dish dish entity
     * @return Horizontal box with plus and minus
     */
    private HBox generatePlusMinus(Dish dish) {
        HBox hb = new HBox();
        hb.setPadding(new Insets(5, 0, 5, 0));

        Button btnPlus = new Button("+");
        btnPlus.setStyle("-fx-font-weight: 800; -fx-font-size: 14");
        btnPlus.setPrefSize(30, 30);

        Button btnMinus = new Button("-");
        btnMinus.setStyle("-fx-font-weight: 800; -fx-font-size: 14");
        btnMinus.setPrefSize(30, 30);

        //add central text field
        TextField amount = new TextField("1");
        amount.setPrefHeight(30);
        amount.setAlignment(Pos.CENTER);

        mapTextFields.put(dish, amount);

        //event on plus button click
        btnPlus.setOnAction(e -> {
            int tmp = Integer.parseInt(amount.getText()) + 1;
            amount.setText(String.valueOf(tmp));
        });
        //event on minus button click
        btnMinus.setOnAction(e -> {
            int tmp = Integer.parseInt(amount.getText()) - 1;
            if (tmp >= 1) {
                amount.setText(String.valueOf(tmp));
            }
        });

        hb.setMargin(amount, new Insets(0, 1, 0, 1));
        hb.getChildren().addAll(btnPlus, amount, btnMinus);

        return hb;
    }

    /**
     * Close check
     *
     * @param event income event
     */
    public void checkClose(Event event) {
        if (purchaserOrder.size() != 0) {
            StringBuilder message = new StringBuilder();
            for (PurchaseOrder order : purchaserOrder) {
                message
                        .append(order.getDish().getName())
                        .append(" - ");
                if (order.getDish().isNeedCook()) {
                    message.append(" (Нужно готовить) - ");
                }
                message.append(order.getCount())
                        .append(" шт. - ")
                        .append(order.getSum())
                        .append("\n");
            }
            double sum = purchaserOrder.getTotalSum();
            message.append("Сумма заказа ").append(sum).append(" гривен");
            if (LazyJavaFXAlert.confirmAlert("Подтвердить заказ на сумму " + sum + " гривен", "Заказ верный?", message.toString())) {
                Order order = purchaserOrder.generateOrder(employees.getId());
                HappyCakeResponse response = StartDesktopApplication.userEntity.getUserBean().getClientActions().saveOrderWithDetails(order);
                if (response.isSuccess()) {
                    Order ord = response.getObject(Order.class);
                    //generate html by order
                    String html = ReceiptGenerator.generateReceipt(ord.getId(), purchaserOrder);
                    //show receipt
                    StartDesktopApplication.showWebView(html);
                    purchaserOrder.clear();
                    addToTree();
                    reinitializeDishesList(0);
                } else {
                    LazyJavaFXAlert.systemError();
                }
            }
        } else {
            LazyJavaFXAlert.alert("Ошибка", "Чек пустой", "Чек не должен быть пустой, добавьте в заказ блюда", Alert.AlertType.ERROR);
        }
    }

    /**
     * Show all categories button click
     *
     * @param event income event
     */
    public void showAllCategoriesBtnClick(Event event) {
        reinitializeDishesList(0);
    }

    /**
     * Get message about need cook
     *
     * @return message
     */
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        for (PurchaseOrder order : purchaserOrder) {
            if (order.getDish().isNeedCook()) {
                builder.append(" Блюдо ").append(order.getDish().getName()).append(" нужно говотовить ").append("\n");
            }
        }
        if (builder.length() < 5) {
            builder.append(" Ни одно из блюд не требуется готовить ");
        }
        return builder.toString();
    }
}
