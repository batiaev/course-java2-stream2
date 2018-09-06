package geekbrains.dmitriymx.java2.homework5;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

    /**
     * Второй подход к заполнению массива в несколько потоков
     * @param array произвольный массив float-чисел
     * @param threadCount колличество потоков
     */
    private static void method2(final float[] array, int threadCount) {
        long saveTime;
        long diffTime;

        // 1. Заполняем массив единицами
        saveTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }
        diffTime = System.currentTimeMillis() - saveTime;
        log.info("[{}] 1. Заполнение массива единицами заняло {} ms ({} s)", "Method-2", diffTime, diffTime/1000f);

        // 2. Вычисляем для каждой ячейки новое значение
        final int[] nextIdx = new int[]{0}; // грязный хак для обхода final ограничений в лямбдах

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        Runnable task = () -> {
            int idx;

            while (true) {
                synchronized (nextIdx) {
                    idx = nextIdx[0];
                    nextIdx[0]++;
                }
                if (idx >= array.length) {
                    break;
                }

                array[idx] = calcValue(array[idx], idx);
            }
        };
        saveTime = System.currentTimeMillis();
        for(int i = 0; i < threadCount; i++) {
            threadPool.execute(task);
        }

        while (threadPool.getActiveCount() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.warn("Interrupted sleep");
                break;
            }
        }
        diffTime = System.currentTimeMillis() - saveTime;
        log.info("[{}] 2. Вычисление новых значений для массива заняло {} ms ({} s)", "Method-2", diffTime, diffTime/1000f);

        threadPool.shutdown();
    }

    public static void main(String[] args) {
        method1(createArray());

        int threadCount = 2;
        if (args.length > 0) {
            try {
                threadCount = Integer.valueOf(args[0]);
            } catch (Exception e) {
                log.error(MessageFormatter.format("Incorrect argument: \"{}\"", args[0]).getMessage(), e);
            }
        }
        method2(createArray(), threadCount);
    }
}
