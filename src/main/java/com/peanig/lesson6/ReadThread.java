package com.peanig.lesson6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ReadThread extends Thread {
    private Scanner reader;
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;

        try {
            reader = new Scanner(this.socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                if (reader.hasNext()) {
                    String message = reader.nextLine();
                    System.out.println("\n" + message);
                }
            } catch (Exception ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}