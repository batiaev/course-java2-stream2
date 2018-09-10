package com.batiaev.java2;

import java.util.Arrays;

public class HomeWork5 {


    private static final int size = 10000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];

    public static void main(String[] args) {
        HomeWork5 hm = new HomeWork5();

        hm.firstMethod();
        hm.secondMethod();

    }

    // Метод заполняет массив единицами,
    // а затем рассчитывает новые значения по данной формуле.
    private void firstMethod() {

        Arrays.fill(arr, 1f);

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения расчетов в первом методе: " +
                        (System.currentTimeMillis() - a));

    }

    // Метод заполняет массив единицами, а затем разбивает его на две части
    // и рассчитывает новые значения по данной формуле в разных потоках.
    private void secondMethod() {
        Arrays.fill(arr, 1f);
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        NewThread t1 = new NewThread(arr1);
        NewThread t2 = new NewThread(arr2);
        try {
            t1.t.join();
            t2.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println("Время выполнения расчетов в втором методе: " +
                (System.currentTimeMillis() - a));
    }
}

class NewThread implements Runnable {

    private float[] arr;
    private int size;
    Thread t;

    NewThread(float[] arr) {
        this.arr = arr;
        size = arr.length;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }
}

