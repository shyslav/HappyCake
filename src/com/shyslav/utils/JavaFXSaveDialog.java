package com.shyslav.utils;

import com.happycake.editablemodel.EditableEntity;
import com.happycake.editablemodel.EditableFieldException;
import com.happycake.editablemodel.EditableParser;
import com.shyslav.io.IOUtils;
import com.shyslav.mysql.interfaces.DBEntity;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author Shyshkin Vladyslav on 08.05.17.
 */
public class JavaFXSaveDialog {
    private static final Logger log = Logger.getLogger(JavaFXSaveDialog.class.getName());

    private Stage stage;
    //link to main stage
    private Stage primaryStage;
    private final EditableParser editableParser;
    private final ISaveDialog dialogComplete;

    public JavaFXSaveDialog(Stage primaryStage, DBEntity entity, ISaveDialog dialogComplete) throws EditableFieldException {
        this.primaryStage = primaryStage;
        this.dialogComplete = dialogComplete;
        this.stage = new Stage();
        try {
            this.editableParser = new EditableParser(entity);
        } catch (EditableFieldException e) {
            LazyJavaFXAlert.systemError();
            throw new EditableFieldException("Unable to pars entity " + entity.toString());
        }
        start();
    }

    /**
     * Load grid pane by parsed object
     */
    private void start() throws EditableFieldException {
        GridPane gridPane = new GridPane();
        //set element padding
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //set grid column
        ColumnConstraints leftColumn = new ColumnConstraints(100);
        ColumnConstraints rightColumn = new ColumnConstraints(50, 250, 400);
        rightColumn.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(leftColumn, rightColumn);

        int columnIndex = 0;
        int rowIndex = 0;
        for (EditableEntity editableEntity : editableParser.getEntityHashMap().values()) {
            Label label = new Label(editableEntity.getFieldName());
            gridPane.add(label, columnIndex, rowIndex);
            switch (editableEntity.getType()) {
                case CALENDAR: {
                    DatePicker datePicker = new DatePicker();
                    datePicker.setMaxWidth(Double.MAX_VALUE);
                    //set selected date
                    LocalDate localDate =
                            Instant.ofEpochMilli(editableEntity.getLongValue() * 1000).atZone(ZoneId.systemDefault()).toLocalDate();
                    datePicker.setValue(localDate);

                    checkRegex(datePicker, editableEntity);
                    //on change date event
                    datePicker.valueProperty().addListener(event -> {
                        LocalDate startLocal = datePicker.getValue();
                        Instant startInstant = Instant.from(startLocal.atStartOfDay(ZoneId.systemDefault()));
                        Date date = Date.from(startInstant);
                        editableEntity.setValue((int) (date.getTime() / 1000));
                        checkRegex(datePicker, editableEntity);
                    });

                    gridPane.add(datePicker, columnIndex + 1, rowIndex);
                    break;
                }
                case TEXTAREA: {
                    TextArea textArea = new TextArea();
                    textArea.setMaxHeight(50);
                    textArea.setWrapText(true);
                    textArea.setText(String.valueOf(editableEntity.getValue()));

                    //on text change event
                    checkRegex(textArea, editableEntity);
                    textArea.textProperty().addListener(event -> {
                        editableEntity.setValue(textArea.getText());
                        checkRegex(textArea, editableEntity);
                    });
                    gridPane.add(textArea, columnIndex + 1, rowIndex);
                    break;
                }
                case TEXTFIELD: {
                    TextField textField = new TextField();
                    gridPane.add(textField, columnIndex + 1, rowIndex);

                    //on text change event
                    checkRegex(textField, editableEntity);
                    textField.setText(String.valueOf(editableEntity.getValue()));
                    textField.textProperty().addListener(event -> {
                        editableEntity.setValue(textField.getText());
                        checkRegex(textField, editableEntity);
                    });
                    break;
                }
                case FILEFIELD: {
                    TextField textField = new TextField();
                    textField.setPromptText("Click to upload image file");
                    //on file change
                    checkRegex(textField, editableEntity);
                    textField.setOnMouseClicked(event -> {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image file", "*.png", "*.jpg", "*.jpeg"));
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        if (selectedFile != null) {
                            try {
                                byte[] bytes = IOUtils.readFile(selectedFile);
                                editableEntity.setValue(bytes);
                            } catch (IOException e) {
                                log.error("Unable to read file bytes " + e.getMessage(), e);
                            }
                            textField.setText(selectedFile.getAbsolutePath());
                        }
                        checkRegex(textField, editableEntity);
                    });
                    gridPane.add(textField, columnIndex + 1, rowIndex);
                    break;
                }
                case NUMBERFIELD: {
                    TextField textField = new TextField();
                    //on text change event
                    checkRegex(textField, editableEntity);
                    textField.setText(String.valueOf(editableEntity.getValue()));
                    textField.textProperty().addListener(event -> {
                        if (editableEntity.getFieldJavaType().equalsIgnoreCase("int")) {
                            int value = 0;
                            if (textField.getText().length() != 0) {
                                value = Integer.parseInt(textField.getText());
                            }
                            editableEntity.setValue(value);
                            checkRegex(textField, editableEntity);
                        } else if (editableEntity.getFieldJavaType().equalsIgnoreCase("double")) {
                            double value = 0;
                            if (textField.getText().length() != 0) {
                                value = Double.parseDouble(textField.getText());
                            }
                            editableEntity.setValue(value);
                            checkRegex(textField, editableEntity);
                        }
                    });
                    gridPane.add(textField, columnIndex + 1, rowIndex);
                    break;
                }
                case SELECTFIELD: {
                    ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
                            editableEntity.getSelectableMap().keySet())
                    );
                    choiceBox.setMaxWidth(Double.MAX_VALUE);
                    //select selected element
                    int index = 0;
                    for (Integer integer : editableEntity.getSelectableMap().values()) {
                        if (integer == (int) editableEntity.getValue()) {
                            choiceBox.getSelectionModel().select(index);
                            break;
                        }
                        index++;
                    }
                    checkRegex(choiceBox, editableEntity);
                    choiceBox.valueProperty().addListener(event -> {
                        Object value = choiceBox.getValue();
                        editableEntity.setValue(editableEntity.getSelectableMap().get(value.toString()));
                        checkRegex(choiceBox, editableEntity);
                    });
                    gridPane.add(choiceBox, columnIndex + 1, rowIndex);
                    break;
                }
                case ENUMTOSTRING: {
                    ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
                            editableEntity.getEnumClassValues())
                    );
                    choiceBox.setMaxWidth(Double.MAX_VALUE);

                    Object[] enumClassValues = editableEntity.getEnumClassValues();
                    for (Object enumClassValue : enumClassValues) {
                        if (enumClassValue.toString().equals(editableEntity.getValue())) {
                            choiceBox.setValue(enumClassValue);
                            break;
                        }
                    }
                    checkRegex(choiceBox, editableEntity);
                    choiceBox.valueProperty().addListener(event -> {
                        Object value = choiceBox.getValue();
                        editableEntity.setValue(value.toString());
                        checkRegex(choiceBox, editableEntity);
                    });
                    gridPane.add(choiceBox, columnIndex + 1, rowIndex);
                    break;
                }
                case CHECKBOX: {
                    CheckBox checkBox = new CheckBox();
                    editableEntity.setValue(false);
                    checkBox.selectedProperty().addListener(event -> {
                        boolean result = checkBox.selectedProperty().get();
                        editableEntity.setValue(result);
                    });
                    gridPane.add(checkBox, columnIndex + 1, rowIndex);
                    break;
                }
            }
            rowIndex++;
        }

        // Save button
        Button saveButt = new Button("Save");
        saveButt.setOnAction(event -> {
            for (EditableEntity editableEntity : editableParser.getEntityHashMap().values()) {
                if (!editableEntity.checkWithRegExp()) {
                    LazyJavaFXAlert.alert("Заполните все данные", null, "Крастным подсвечены те поля которые заполненны не правильно, исправьте ошибки", Alert.AlertType.ERROR);
                    return;
                }
            }
            dialogComplete.complete();
            closeStage();
        });
        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridPane.add(saveButt, columnIndex + 1, rowIndex);

        //show scene
        Scene scene = new Scene(gridPane);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setResizable(false);
        stage.setTitle(editableParser.getModelName());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * check input if text is correct
     *
     * @param inputControl   input control
     * @param editableEntity editable entity
     */
    private void checkRegex(Control inputControl, EditableEntity editableEntity) {
        if (editableEntity.checkWithRegExp()) {
            Border border = new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
            inputControl.setBorder(border);
        } else {
            Border border = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
            inputControl.setBorder(border);
        }
    }

    /**
     * Action to close current stage
     */
    private void closeStage() {
        stage.close();
    }
}
