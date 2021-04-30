package com.batiaev.java2.lesson6.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");

            Socket sock = server.accept();

            System.out.println("Клиент подключился");

            new ConsoleChat(sock).run();

        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
