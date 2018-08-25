package com.batiaev.java2.lesson2.homework;

public class MyArrayDataException extends Exception {

    private int row, column;
    private String value;

    public MyArrayDataException(int i, int j, String value) {
        super();
        this.row = i;
        this.column = j;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
