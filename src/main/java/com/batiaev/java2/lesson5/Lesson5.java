package com.batiaev.java2.lesson5;

public class Lesson5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(Lesson5::process);
        thread.setName("My custom thread");
        thread.setPriority(7);
        thread.start();
        thread.join();
        Thread.State state = thread.getState();
        process();
    }

    private static void process() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized void example1() {
        //FIXME
    }

    private synchronized void example2() {
        //FIXME
    }

    private synchronized void example22() {
        //FIXME
    }

    private void example3() {
        Object monitor = new Object();
        Object monitor2 = new Object();
        synchronized (monitor) {
            //FIXME
        }
        synchronized (monitor2) {
            //FIXME
        }
    }
}
