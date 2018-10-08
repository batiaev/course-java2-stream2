package com.batiaev.java2.HW6;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vedeshkin on 08.10.2018.
 */
public class ArrayUtil {

      int [] splitFromFour( int [] data){
        if(data == null) throw new NullPointerException();
        for(int i = data.length-1;0<=i;i--){
            if (data [i] == 4 && i!= data.length -1){
                return Arrays.copyOfRange(data,i+1,data.length);
            }
        }
        throw new RuntimeException();

    }
    public static void main(String args[])
    {


    }
}
