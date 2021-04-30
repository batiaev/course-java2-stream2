package com.batiaev.java2.lesson6.homework;

import java.io.PrintWriter;
import java.util.Scanner;

public class Translator implements Runnable {
    private Scanner in;
    private PrintWriter out;

    public Translator(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (in.hasNext()) {
            String message = in.next().trim();
            out.println(message);
            out.flush();
            if (message.equals("end")) {
                return;
            }
        }
    }
}
