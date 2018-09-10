package com.peanig.lesson6;


import java.net.*;
import java.io.*;


public class ChatClient {
    final String hostname = "127.0.0.1";
    final int port = 4040;


    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");

            new ReadThread(socket).start();
            new WriteThread(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.execute();
    }
}