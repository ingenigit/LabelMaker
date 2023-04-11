package com.genipos.labelmaker.model;

public class PrintSetting {
    String fontSize, label_Name, label_Value;
    int x_axis, y_axis, rotation, x_multiplication, y_multiplication;
    String  type, value;
    int x, y, height,humanRead, rotate, narrow, wide;

    public PrintSetting(int x_axis, int y_axis, String fontSize, int rotation, int x_multiplication, int y_multiplication, String label_Name, String label_Value) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.fontSize = fontSize;
        this.rotation = rotation;
        this.x_multiplication = x_multiplication;
        this.y_multiplication = y_multiplication;
        this.label_Name = label_Name;
        this.label_Value = label_Value;
    }

    public PrintSetting(int x, int y, String type, int height, int humanRead, int rotate, int narrow, int wide, String value) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.height = height;
        this.humanRead = humanRead;
        this.rotate = rotate;
        this.narrow = narrow;
        this.wide = wide;
        this.value = value;
    }

    public int getX_axis() {
        return x_axis;
    }

    public int getY_axis() {
        return y_axis;
    }

    public String getFontSize() {
        return fontSize;
    }

    public int getRotation() {
        return rotation;
    }

    public int getX_multiplication() {
        return x_multiplication;
    }

    public int getY_multiplication() {
        return y_multiplication;
    }

    public String getLabel_Name() {
        return label_Name;
    }

    public String getLabel_Value() {
        return label_Value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    public int getHumanRead() {
        return humanRead;
    }

    public int getRotate() {
        return rotate;
    }

    public int getNarrow() {
        return narrow;
    }

    public int getWide() {
        return wide;
    }

    public String getValue() {
        return value;
    }
}
