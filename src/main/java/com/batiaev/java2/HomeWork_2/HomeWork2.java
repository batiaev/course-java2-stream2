package com.batiaev.java2.HomeWork_2;


public class HomeWork2 {

    // Массивы для тестирования, один только цифры,
    private static String[][] numbers = {
            { "5", "49", "53", "17" },
            { "56", "44", "65", "27" },
            { "91", "9", "18", "57" },
            { "20", "27", "32", "85" }
    };

    // второй перемешан с буквами,
    private static String[][] numbersAndLetters = {
            { "5", "49", "53", "17" },
            { "56", "44", "65", "d7" },
            { "91", "9", "a", "57" },
            { "h", "27", "32", "8j" }
    };

    // третий другого размера.
    private static String[][] diffSize = {
            { "5", "49", "53", "17", "15" },
            { "56", "44", "65", "d7", "77" },
            { "91", "9", "a", "57", "18c" },
            { "h", "27", "32", "8j", "l" }
    };

    public static void main(String[] args) {
        HomeWork2 hm = new HomeWork2();

        // Блок try/catch  для обработки исключений.
        try {
            //hm.taking2DArray(diffSize);
            hm.taking2DArray(numbersAndLetters);
            //hm.taking2DArray(numbers);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    // Метод принимающий в качестве аргумента двумерный массив типа String.
    private void taking2DArray(String[][] s) throws
            MyArraySizeException,
            MyArrayDataException {

        int size = s.length;
        int size2 = s[0].length;
        System.out.println(" size : " + size + " size2: " + size2);

        // Проверка размера массива, если размер отличен от ожидаемого,
        // получаем исключение.
        if (size != 4 || size2 != 4) {
            throw new MyArraySizeException(Msg.WRONG_SIZE);
        }

        int sum = 0;

        // Перебираем массив
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size2; j++) {

                // Если в массиве по данному индексу содержится не цифра,
                // получаем исключение.
                if (!isNumeric(s[i][j])) {
                    throw new MyArrayDataException(
                            String.format(Msg.WRONG_DATA, i, j, s[i][j])
                    );
                } else {
                    // Если все в порядке, суммируем все цифры и выводим результат.
                    int first = Integer.parseInt(s[i][j]);
                    sum += first;
                    System.out.println(" sum: " + sum);
                }
            }
        }

        System.out.println(" Returned correct value ");

    }

    //Метод проверяющий, является ли String цифрой.
    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
