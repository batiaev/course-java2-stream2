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
            { "5", "53", "17", "8" },
            { "56", "44", "65", "7"},
            //{ "91", "9", "18c", "49" },
            { "4", "27", "32", "52"}
    };

    public static void main(String[] args) {
        HomeWork2 hm = new HomeWork2();

        // Блок try/catch  для обработки исключений.
        try {
            hm.taking2DArray(diffSize);
            //hm.taking2DArray(numbersAndLetters);
            //hm.taking2DArray(numbers);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    private boolean isAllSizesCorrect(int size) {
        return size == 4;
    }

    // Метод принимающий в качестве аргумента двумерный массив типа String.
    private void taking2DArray(String[][] s) throws
            MyArraySizeException,
            MyArrayDataException {

        int size = s.length;
        int size2 = s[0].length;

        // Проверка размера массива, если размер отличен от ожидаемого,
        // получаем исключение.
//        if (size != 4 || size2 != 4) {
//            throw new MyArraySizeException(Msg.WRONG_SIZE);
//        }


        // Исправление:
        // Проверка размера двумерного массива,
        // включая возможность не равных вложенных массивов.
        for (String[] value : s) {
            for (int a = 0; a < value.length; a++) {
                if (!isAllSizesCorrect(value.length) || size != 4) {
                    throw new MyArraySizeException(
                            String.format(Msg.WRONG_SIZE, size, value.length));
                }
            }
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
                }
            }
        }
        System.out.println(String.format("Sum: %d", sum));
    }

    //Метод проверяющий, является ли String цифрой.
    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
