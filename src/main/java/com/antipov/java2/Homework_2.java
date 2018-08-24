package com.antipov.java2;

/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
 *
 * P.S. Задание вроде выполнил, но осталось ощущение, что все слишком просто и где-то может быть подвох))
 * Так что напишите, если надо будет что-то переделать/дополнить.
 */
public class Homework_2 {
    private static int[][] matrix;

    public static void main(String[] args) {
        String[][] strs = {{"1", "2", "3", "7"},
                           {"5", "4", "9", "6"},
                           {"9", "3", "0", "1"},
                           {"8", "7", "2", "4"}};
        try {
            matrix = getArrayFour(strs);
            printArray(matrix);
            System.out.println(sumArray(matrix));
        } catch (MyArraySizeException ex) {
            System.out.println("Ошибка: \n" + ex);
        } catch (MyArrayDataException exd) {
            System.out.println("Ошибка: \n" + exd);
        }

    }

    private static void printArray(int[][] matrix) {
        System.out.println();
        for (int[] arr : matrix) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static int sumArray(int[][] matrix) {
        int sum = 0;
        for (int[] arr : matrix) {
            for (int num : arr) {
                sum += num;
            }
            System.out.println();
        }
        return sum;
    }

    private static int[][] getArrayFour(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4 || arr[0].length != 4)
            throw new MyArraySizeException("Array size mismatch. Expected array[4][4], but get: ["
                                           + arr.length + "][" + arr[0].length + "]");
        int[][] myArr = new int[4][4];
        int     i     = 0, j = 0;
        try {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    myArr[i][j] = Integer.parseInt(arr[i][j]);
                }
            }
        } catch (Exception ex) {
            throw new MyArrayDataException(
                    "Not a number in array [" + i + "][" + j + "]=\"" + arr[i][j] + "\"");
        }
        return myArr;
    }
}
