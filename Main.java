package com.example.jcore.lesson_2;

public class Main {
    public static void main(String[] args) {

        String[] obstacles = { "2010", "2012", "2o14", "2016" };

        Main.takeAnArray( obstacles );
    }

    private static void takeAnArray ( String[] any_array ) throws MyArraySizeException {

        int[] int_array = new int[4];
        int num_coll = 0;
        int arr_summ = 0;

        if ( any_array.length > 4 ) {
            throw new MyArraySizeException( "Превышена допустимая величина массива!" );
        }

        try {
            for ( int i = 0; i < any_array.length; i++ ) {
                num_coll = i;
                int_array[i] = new Integer( any_array[i] );
            }

            for ( int i = 0; i < int_array.length; i++ ) {
                arr_summ += int_array[i];
            }

            System.out.println( "Общая сумма массива: " + arr_summ );
        }
        catch ( NumberFormatException e ) {
            System.out.println( "Ошибка в ячейке №: " + num_coll );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}