package com.peanig.lesson2;

public class Lesson2 {
    private static String[][] matrixString = new String[][] {{"1", "2", "3", "4"},
                                                             {"1", "2", "3", "4"},
                                                             {"1", "2", "3", "4"},
                                                             {"1", "2", "3", "4"}};

    public static void main(String[] args) {
        try {
            System.out.println(sumArray(matrixString));
        } catch (MyArraySizeException ex) {
            System.out.println("Error: \n" + ex);
        } catch (MyArrayDataException ex) {
            System.out.println("Error: \n" + ex);
        }
    }

    private static int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int result = 0;
        if (arr.length < 4)
            throw new MyArraySizeException();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length < 4)
                throw new MyArraySizeException();
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                } catch (Exception ex) {
                    throw new MyArrayDataException(i, j, arr[i][j]);
                }
            }
        }
        return result;
    }

}
