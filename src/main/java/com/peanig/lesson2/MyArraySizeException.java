package com.peanig.lesson2;


public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Array size mismatch");
    }
}