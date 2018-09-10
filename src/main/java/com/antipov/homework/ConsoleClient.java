package com.antipov.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 4040;
    private static Socket socket;
    private static Scanner input;
    private static PrintWriter output;

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
        Thread socketListener = new Thread(() -> {
            try {
                while (true) {
                    if (input.hasNext()) {
                        String in = input.nextLine();
                        if (in.equals("end")) {
                            output.println(in);
                            output.flush();
                            break;
                        }
                        System.out.println("Server: " + in);
                    }
                }
            }catch (Exception e){e.printStackTrace();}
            System.out.println("Server disconnected.");
        });
        socketListener.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connected to server. For quit write \"end\"");
        while (socketListener.isAlive()) {
            if (scanner.hasNext()) {
                String str = scanner.nextLine();
                output.println(str);
                output.flush();
                if (str.equals("end")) {
                    break;
                }
            }
        }
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e){e.printStackTrace();}

    }
}
