package com.antipov.java2;

public class Homework implements Runnable {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static final int q = size / 4;
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

    private static void quadCalculate(float[] array) {
        float[] a1        = new float[q];
        float[] a2        = new float[q];
        float[] a3        = new float[q];
        float[] a4        = new float[q];
        long    startTime = System.currentTimeMillis();
        System.arraycopy(array, 0, a1, 0, q);
        System.arraycopy(array, q, a2, 0, q);
        System.arraycopy(array, 2 * q, a3, 0, q);
        System.arraycopy(array, 3 * q, a4, 0, q);
        Thread thread1 = new Thread(new Homework(a1));
        Thread thread2 = new Thread(new Homework(a2));
        Thread thread3 = new Thread(new Homework(a3));
        Thread thread4 = new Thread(new Homework(a4));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        do {
            if (thread1.getState().equals(Thread.State.TERMINATED) &
                thread2.getState().equals(Thread.State.TERMINATED) &
                thread3.getState().equals(Thread.State.TERMINATED) &
                thread4.getState().equals(Thread.State.TERMINATED))
                break;
        } while (true);
        System.arraycopy(a1, 0, array, 0, q);
        System.arraycopy(a2, 0, array, q, q);
        System.arraycopy(a3, 0, array, 2 * q, q);
        System.arraycopy(a4, 0, array, 3 * q, q);
        System.out.println("Calculated in four thread(ms) = " + (System.currentTimeMillis() - startTime));
    }

    private static void TestingThread() {
        Thread thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                long i = 0;
                while (i < 100000) {
                    i++;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.State currentState = thrd.getState();
        long         timeStart    = System.currentTimeMillis();
        System.out.println("Current thread state = " + currentState);
        thrd.start();
        do {
            if (!currentState.equals(thrd.getState())) {
                currentState = thrd.getState();
                System.out.println("Current thread state = " + currentState);
            }
        } while (System.currentTimeMillis() <= timeStart + 100000);
    }
}
