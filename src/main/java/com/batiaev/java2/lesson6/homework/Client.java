package com.batiaev.java2.lesson6.homework;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;

    public static void main(String[] args) {
        try {
            System.out.println("Подключаемся к серверу...");

            Socket sock = new Socket(SERVER_ADDR, SERVER_PORT);

            System.out.println("Подключение установлено!");

            new ConsoleChat(sock).run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
