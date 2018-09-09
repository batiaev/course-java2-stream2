package com.batiaev.java2.HomeWork_6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args) {
       new ChatServer();
    }

    private ChatServer() {
        try {
            ServerSocket serv = new ServerSocket(3500);
            System.out.println("Server running...");
            Socket sock = serv.accept();
            System.out.println("Client connected.");

            // Создается отдельный поток для вывода на консоль
            IOThreads t = new IOThreads(sock, false);
            t.start();

            //Создается поток, в котором высылаются сообщения с сервера
            IOThreads t2 = new IOThreads(sock, true);
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
