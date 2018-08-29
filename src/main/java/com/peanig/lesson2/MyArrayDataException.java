package com.peanig.lesson2;


public class MyArrayDataException extends Exception {
    public MyArrayDataException() {
        super("Not a number in array");
    }

    public MyArrayDataException(int row, int column, String symbol) {
        super("Not a number in array [" + row + "][" + column + "]=" + symbol + "");
    }
}