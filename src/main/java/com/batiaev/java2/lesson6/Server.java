package com.batiaev.java2.lesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static PrintWriter pw;
    private static Scanner consoleScanner = new Scanner(System.in);

    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock;
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream(), true);
            new Thread(() -> scanConsole()).start();
            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                System.out.println("User:" + str);
                pw.println("User: " + str);
            }
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scanConsole() {
        while (true) {
            String msg = consoleScanner.nextLine();
            if (msg != null && !msg.isEmpty()) {
                pw.println("Server: " + msg);
                if (msg.equals("end")) break;
            }
        }
    }
}