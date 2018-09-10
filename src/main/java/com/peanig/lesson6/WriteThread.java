package com.peanig.lesson6;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class WriteThread extends Thread {
    private PrintWriter writer;
    private Scanner in;
    private Socket socket;

    public WriteThread(Socket socket) {
        this.socket = socket;
        this.in = new Scanner(System.in);
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (in.hasNext()) {
            String message = in.next().trim();
            writer.println(message);
            if (message.equals("end")) break;
        }
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error writing to server: " + ex.getMessage());
        }
        System.out.println("Connection closed");
    }
}