package com.batiaev.java2.HomeWork_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class IOThreads extends Thread {

    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private BufferedReader in;
    private boolean isSend;
    private boolean isRunning;

    public IOThreads(Socket socket, boolean isSend) {
        this.socket = socket;
        this.isSend = isSend;
        isRunning = true;
        getInputs();
    }

    private void getInputs() {
        try {
            sc = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO доработать выходы из циклов и завершения потоков
    @Override
    public void run() {
        if (isSend) {
            while (true) {
                String str = sc.next().trim();
                if (str.equalsIgnoreCase("exit")) {
                    return;
                }
                out.println(str);
                out.flush();
            }
        } else {
            while (true) {
                String str = null;
                try {
                    str = in.readLine();
                    if (str.equalsIgnoreCase("exit")) {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(str);
            }
        }

    }
}
