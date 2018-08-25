package com.batiaev.java2.lesson2.homework;

public class Main {
    public static void main(String[] args) {

        String[][] array1 = new String[][]{{"1"}, {"2"}};
        try {
            System.out.println(sumArray(array1));
        } catch (MyArraySizeException e) {
            System.out.println("Размер массива не 4*4");
        } catch (MyArrayDataException e) {
            System.out.println("Значение (" + e.getRow() + "," + e.getColumn() + ") == " + e.getValue() + " не является числом");
        }



        String[][] array2 = new String[][]{
                {"1", "2", "3", "4"},
                {"2", "2", "3h", "4"},
                {"3", "2", "3", "4"},
                {"4", "2", "3", "4"}
        };

        try {
            System.out.println(sumArray(array2));
        } catch (MyArraySizeException e) {
            System.out.println("Размер массива не 4*4");
        } catch (MyArrayDataException e) {
            System.out.println("Значение (" + e.getRow() + "," + e.getColumn() + ") == " + e.getValue() + " не является числом");
        }



        String[][] array3 = new String[][]{
                {"1", "2", "3", "4"},
                {"2", "2", "3", "4"},
                {"3", "2", "3", "4"},
                {"4", "2", "3", "4"}
        };

        try {
            System.out.println(sumArray(array3));
        } catch (MyArraySizeException e) {
            System.out.println("Размер массива не 4*4");
        } catch (MyArrayDataException e) {
            System.out.println("Значение (" + e.getRow() + "," + e.getColumn() + ") == " + e.getValue() + " не является числом");
        }

    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        testArrayFourSize(array);
        for (String[] strings : array) {
            testArrayFourSize(strings);
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }
        return result;
    }

    private static void testArrayFourSize(Object[] array) throws MyArraySizeException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException();
        }
    }
}
