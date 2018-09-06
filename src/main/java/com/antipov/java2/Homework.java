package com.antipov.java2;

public class Homework implements Runnable {
    private static final int size = 10000000;
    private static final int h = size / 2;
    float[] hArr;

    @Override
    public void run() {
        for (int i = 0; i < hArr.length; i++) {
            hArr[i] = (float) (hArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                               Math.cos(0.4f + i / 2));
        }
    }

    Homework(float[] array) {
        this.hArr = array;
    }

    public static void main(String[] args) {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1f;
        }
        calculateArray(array);
        calculateParallelArray(array);
    }

    private static void calculateArray(float[] array) {

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                                Math.cos(0.4f + i / 2));
        }
        System.out.println("Calculated in one thread(ms) = " + (System.currentTimeMillis() - a));
    }

    private static void calculateParallelArray(float[] array) {
        float[] a1        = new float[h];
        float[] a2        = new float[h];
        Thread thread1 = new Thread(new Homework(a1));
        Thread thread2 = new Thread(new Homework(a2));
        long    startTime = System.currentTimeMillis();
        System.arraycopy(array, 0, a1, 0, h);
        System.arraycopy(array, h, a2, 0, h);

        thread1.start();
        thread2.start();
        do {
            try {
                if (thread1.isAlive())
                    thread1.join(250);
                else
                    thread2.join(250);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (thread1.getState().equals(Thread.State.TERMINATED) &
                thread2.getState().equals(Thread.State.TERMINATED))
                break;
        } while (true);
        System.arraycopy(a1, 0, array, 0, h);
        System.arraycopy(a2, 0, array, h, h);
        System.out.println("\nCalculated in two threads(ms) = " + (System.currentTimeMillis() - startTime));
    }
}
