package com.batiaev.java2.HomeWork_8;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private String nick;
    private Channel channel;

    private static final int TIME_OUT = 12000;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        long current = System.currentTimeMillis();
        try {
            channel = ChannelBase.of(socket);
            Thread t = new Thread(() -> {

                if (auth()) {
                    // Если авторизация прошла успешно, выполняется блок кода с
                    // обработкой сообщений.Если авторизация прошла успешно,
                    // выполняется блок кода с обработкой сообщений.
                    System.out.println(nick + " handler waiting for new massages");
                    while (socket.isConnected()) {
                        Message msg = channel.getMessage();
                        if (msg == null) continue;
                        switch (msg.getType()) {
                            case EXIT_COMMAND:
                                server.unsubscribe(this);
                                break;
                            case PRIVATE_MESSAGE:
                                sendPrivateMessage(msg.getBody());
                                break;
                            case BROADCAST_CHAT:
                                server.sendBroadcastMessage(
                                        nick + " : " + msg.getBody()
                                );
                            default:
                                System.out.println("invalid message type");
                        }
                    }
                } else {
                    System.out.println("not authorized");

                    //Если пользователь не авторизовался, (пока не придумал ничего другого)
                    // запускается цикл проверяющий продолжительность подключения.
                    while (System.currentTimeMillis() - current < TIME_OUT) {
                        // ???
                    }

                    // После превышения таймаута, сокет закрывается.
                    try {
                        System.out.println("Trying to close socket");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("socket closed: " + socket.isClosed());
                }
            });
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) {
            server.sendPrivateMessage(nick, nickTo, nick + " -> " + nickTo + " : " + message);
        } else {
            sendMessage(nickTo + " not taken!");
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    // Изменения в методе: теперь метод возвращает значение true,
    // если авторизация прошла успешно, и false  в другом случае.
    private boolean auth() {
        while (true) {
            if (!channel.hasNextLine()) continue;
            Message message = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(message.getType())) {
                String[] commands = message.getBody().split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println(
                            "Try to login with " + login + " and " + password
                    );

                    String nick = server
                            .getAuthService()
                            .authByLoginAndPassword(login, password);

                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        sendMessage(msg);
                        server.subscribe(this);

                        return true;
                    }
                }
            } else {
                sendMessage("Invalid command!");
            }
            return false;
        }
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }
}
