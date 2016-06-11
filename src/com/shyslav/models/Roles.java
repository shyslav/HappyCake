package com.shyslav.models;

/**
 * Created by Shyshkin Vladyslav on 19.05.2016.
 */
public class Roles {
    private String labelName;
    private String type; //{textArea, textField};
    private int maxLenght;
    private int minLenght;
    private boolean emptyOrNot;
    private KeyValue keyValue;

    public Roles(String LabelName, String text, int maxLenght, int minLenght, boolean emptyOrNot, KeyValue keyValue) {
        labelName = LabelName;
        type = text;
        this.maxLenght = maxLenght;
        this.minLenght = minLenght;
        this.emptyOrNot = emptyOrNot;
        this.keyValue = keyValue;
    }

    public KeyValue getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(KeyValue keyValue) {
        this.keyValue = keyValue;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        labelName = labelName;
    }

    public String getText() {
        return type;
    }

    public void setText(String text) {
        type = text;
    }

    public int getMaxLenght() {
        return maxLenght;
    }

    public void setMaxLenght(int maxLenght) {
        this.maxLenght = maxLenght;
    }

    public int getMinLenght() {
        return minLenght;
    }

    public void setMinLenght(int minLenght) {
        this.minLenght = minLenght;
    }

    public boolean isEmptyOrNot() {
        return emptyOrNot;
    }

    public void setEmptyOrNot(boolean emptyOrNot) {
        this.emptyOrNot = emptyOrNot;
    }
}
