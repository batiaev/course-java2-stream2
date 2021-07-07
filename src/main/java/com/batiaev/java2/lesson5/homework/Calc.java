package com.batiaev.java2.lesson5.homework;

public class Calc {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) throws InterruptedException {
        float[] arr = new float[size];

        initArray(arr);
        calcInMainThread(arr);

        System.out.println();

        initArray(arr);
        calcInThreads(arr);
    }

    private static void initArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    private static void calcInMainThread(float[] arr) {
        calcWithTimeStatistic(arr, 0);
    }

    private static void calcWithTimeStatistic(float[] arr, int delta) {

        long a = System.currentTimeMillis();
        System.out.println("Begin calc in thread " + Thread.currentThread().getName());

        calculate(arr, delta);

        System.out.println("Calc time in thread " + Thread.currentThread().getName() + ": " + (System.currentTimeMillis() - a));
    }

    private static void calculate(float[] arr, int delta) {
        int index = 0;
        for (int i = delta; i < arr.length + delta; i++) {
            arr[index] = (float) (arr[index] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            index++;
        }
    }

    private static void calcInThreads(float[] arr) throws InterruptedException {
        initArray(arr);
        long start = System.currentTimeMillis();

        System.out.println("Begin calc in threads");

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        splitArray(arr, a1, a2);

        Thread thread1 = new Thread(() -> {
            calcWithTimeStatistic(a1, 0);
        });
        thread1.setName("thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            calcWithTimeStatistic(a2, h);
        });
        thread2.setName("thread2");
        thread2.start();

        thread1.join();
        thread2.join();

        mergeArrays(arr, a1, a2);

        System.out.println("End calc in threads: " + (System.currentTimeMillis() - start));
    }

    private static void mergeArrays(float[] arr, float[] a1, float[] a2) {
        System.out.println("Begin merge arrays");

        long start = System.currentTimeMillis();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long endMerge = System.currentTimeMillis();

        System.out.println("End merge arrays: " + (endMerge - start));
    }

    private static void splitArray(float[] arr, float[] a1, float[] a2) {
        long start = System.currentTimeMillis();

        System.out.println("Begin split array");

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        System.out.println("End split array: " + (System.currentTimeMillis() - start));
    }
}
