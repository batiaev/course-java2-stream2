package geekbrains.dmitriymx.java2.homework5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    // выделил в отдельный метод для удобства
    private static float[] createArray() {
        return new float[Short.MAX_VALUE * 100];
    }

    /**
     * Вычисление нового значения по формуле
     * @param value значение
     * @param idx позиция в массиве
     * @return новое значение
     */
    private static float calcValue(float value, int idx) {
        return (float) (value * Math.sin(0.2f + idx / 5) * Math.cos(0.2f + idx / 5) * Math.cos(0.4f + idx / 2));
    }

    /**
     * Первый подход к заполнению массива одним потоком
     * @param array произвольный массив float-чисел
     */
    private static void method1(final float[] array) {
        long saveTime;
        long diffTime;

        // 1. Заполняем массив единицами
        saveTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }
        diffTime = System.currentTimeMillis() - saveTime;
        log.info("[{}] 1. Заполнение массива единицами заняло {} ms ({} s)", "Method-1", diffTime, diffTime/1000f);

        // 2. Вычисляем для каждой ячейки новое значение
        saveTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = calcValue(array[i], i);
        }
        diffTime = System.currentTimeMillis() - saveTime;
        log.info("[{}] 2. Вычисление новых значений для массива заняло {} ms ({} s)", "Method-1", diffTime, diffTime/1000f);
    }

    public static void main(String[] args) {
        method1(createArray());
    }
}
