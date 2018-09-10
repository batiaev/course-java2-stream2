package com.antipov.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static boolean isEnd = false;
    private static Scanner scanner = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(4040);
            System.out.println("Server launched, wait connections...");
            socket = server.accept();
            System.out.println("Client connected. For quit write \"end\"");
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            //Слушаем входящие сообщения в отедльном потоке
            Thread socketListener = new Thread(() -> {
                try {
                    while (!isEnd) {
                        if (input.hasNext()) {
                            String in = input.nextLine();
                            if (in.equals("end")){
                                output.println(in);
                                output.flush();
                                break;
                            }
                            System.out.println("Client: " + in);
                        }
                    }
                }catch (Exception e){e.printStackTrace();}
                System.out.println("Client disconnected");
            });
            socketListener.start();
            //Считываем ввод на сервере в главном потоке
            scanner = new Scanner(System.in);

            while (socketListener.isAlive()) {
                if (scanner.hasNext()) {
                    String str = scanner.nextLine();
                    //Сперва отправляем сообщение
                    output.println(str);
                    output.flush();
                    //Затем проверяем были ли введена команда выхода,
                    // чтобы с выходом одной из сторон вторая ыла предупреждена
                    if (str.equals("end")) break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error. Server not initialized");
        } finally {
            try {
                server.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
