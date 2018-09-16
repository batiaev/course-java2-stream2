package com.batiaev.java2.HomeWork_7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    //    private Socket socket;
    private Server server;
    private PrintWriter pw;
    private Scanner sc;
    private String nick;

    public ClientHandler(Socket socket, Server server) {
//        this.socket = socket;
        this.server = server;

        try {
            sc = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                auth();
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    String s = sc.nextLine(); // no line found sometimes
                    if (s != null && s.equals("/exit")) {
                        server.unsubscribe(this);
                    } else if (s != null && s.startsWith("/w ")) {
                        sendPrivate(s.trim());
                    } else if (s != null && !s.isEmpty())
                        server.sendBroadcastMessage(nick + " : " + s);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPrivate(String messageTo) {
        String[] privateMsg = messageTo.split(" ");
        String toUser = privateMsg[1];
        String msg = privateMsg[2];
        System.out.println(toUser + " " + msg);
        if (server.isNickTaken(toUser)) {
            server.sendPrivate(nick, toUser, nick +
                    " whispers to " + toUser + ": " + msg);
        } else {
            sendMessage(toUser + " is not in chat room.");
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth() {
        while (true) {
            if (!sc.hasNextLine()) continue;
            String s = sc.nextLine();
            if (s.startsWith("/auth")) {
                String[] commands = s.split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login +
                            " and " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        pw.println(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        pw.println(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        pw.println(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                pw.println("Invalid command!");
            }
        }
    }

    public void sendMessage(String msg) {
        pw.println(msg);
    }

    public String getNick() {
        return nick;
    }
}
