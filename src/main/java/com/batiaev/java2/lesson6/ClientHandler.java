package com.batiaev.java2.lesson6;

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
                    String s = sc.nextLine();
                    if (s != null && s.equals(Client.commands.get(Client.commandID.EXIT)))
                        server.unsubscribe(this);
                    else if (s != null && !s.isEmpty()) {
                        if (s.startsWith(Client.commands.get(Client.commandID.WHISP))) {
                            String[] commands = s.split(" ");// /w nick message
                            if (commands.length >= 3) {
                                String nickTo  = commands[1];
                                String message = commands[2];
                                server.sendMessageTo(nick, nickTo, nick + " : " + message);
                            }
                        } else if (s.startsWith(Client.commands.get(Client.commandID.USERS))) {
                            server.getUsersOnline(nick);
                        } else {
                            server.sendBroadcastMessage(nick + " : " + s);
                        }

                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth() {
        while (true) {
            if (!sc.hasNextLine()) continue;
            String s = sc.nextLine();
            if (s.startsWith(Client.commands.get(Client.commandID.AUTH))) {
                String[] commands = s.split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login    = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
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
            } else if (s.startsWith(Client.commands.get(Client.commandID.REG))) {
                String[] commands = s.split(" ");// /reg login password nick
                if (commands.length >= 3) {
                    String newLogin    = commands[1];
                    String newPassword = commands[2];
                    String newNick     = commands[3];
                    String response    = server.getAuthService().regNewUser(newLogin, newPassword, newNick);
                    if (response != null)
                        pw.println(response);
                    else {
                        this.nick = newNick;
                        String msg = "Reg & auth ok!";
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
