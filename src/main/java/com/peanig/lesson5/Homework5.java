package com.peanig.lesson5;


public class Homework5{
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
        }
        fisrtMethod(arr.clone());
        secondMethod(arr.clone());
    }

    private static void fisrtMethod(float[] array) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread = " + (System.currentTimeMillis() - a));
    }

    private static void secondMethod(float[] array) {
        float[] a1        = new float[h];
        float[] a2        = new float[h];

        long    startTime = System.currentTimeMillis();

        System.arraycopy(array, 0, a1, 0, h);
        System.arraycopy(array, h, a2, 0, h);

        MyThread thread1 = new MyThread(a1);
        MyThread thread2 = new MyThread(a2);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a1 = thread1.getResult();
        a2 = thread2.getResult();
        System.arraycopy(a1, 0, array, 0, h);
        System.arraycopy(a2, 0, array, h, h);

        System.out.println("Two threads = " + (System.currentTimeMillis() - startTime));
    }

    static class MyThread extends Thread {
        private final float[] result = new float[h];
        private final float[] input;

        public MyThread(float[] input) {
            this.input = input;
        }

        public void run() {
            for (int i = 0; i < this.input.length; i++) {
                result[i] = (float) (input[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }

        public float[] getResult() {
            return result;
        }
    }
}