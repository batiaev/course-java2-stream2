package com.batiaev.java2.HomeWork_6;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private final static String SERVER = "localhost";
    private final static int SERVER_PORT = 3500;

    public static void main(String[] args) {
        new ChatClient();
    }

    private ChatClient() {
        try {
            Socket sock = new Socket(SERVER, SERVER_PORT);

            // Создается поток, который выводит сообщения в консоль
            IOThreads t = new IOThreads(sock, false);
            t.start();

            // Создается поток из которого высылаются сообщения на сервер
            IOThreads t2 = new IOThreads(sock, true);
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
