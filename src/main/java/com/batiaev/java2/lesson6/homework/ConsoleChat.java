package com.batiaev.java2.lesson6.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleChat {
    private Socket sock;

    public ConsoleChat(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        try {
            Thread netToConsoleThread = new Thread(new Translator(new Scanner(sock.getInputStream()), new PrintWriter(System.out)));
            netToConsoleThread.start();

            Thread consoleToNetThread = new Thread(new Translator(new Scanner(System.in), new PrintWriter(sock.getOutputStream())));
            consoleToNetThread.start();

            while (true) {
                if (netToConsoleThread.getState() == Thread.State.TERMINATED) {
                    consoleToNetThread.interrupt();
                    break;
                }
                if (consoleToNetThread.getState() == Thread.State.TERMINATED) {
                    netToConsoleThread.interrupt();
                    break;
                }
                Thread.sleep(300);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("конец связи");

    }
}
