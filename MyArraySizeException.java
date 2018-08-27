package com.example.jcore.lesson_2;

/**
 * MyArraySizeException -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_2
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    public MyArraySizeException ( String message ) {
        super( message );
    }
}
