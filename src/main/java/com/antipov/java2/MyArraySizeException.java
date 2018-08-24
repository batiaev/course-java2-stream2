package com.antipov.java2;

public class MyArraySizeException extends RuntimeException {

    /**
     * Constructs a <code>MyArraySizeException</code> with no
     * detail message.
     */
    public MyArraySizeException() {super();}

    /**
     * Constructs a <code>MyArraySizeException</code> with the
     * specified detail message.
     *
     * @param   message   the detail message.
     */
    public MyArraySizeException(String message) {super(message);}
}
