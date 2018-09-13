package com.batiaev.java2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class Client extends JFrame implements ClientUI {

    private JTextField jtf;
    private JTextArea jta;
    private Controller controller;

    public enum commandID {HELP, AUTH, REG, WHISP, USERS, EXIT}

    public static final Map<commandID, String> commands = new HashMap<commandID, String>() {{
        put(commandID.HELP, "/help");
        put(commandID.AUTH, "/auth");
        put(commandID.REG, "/reg");
        put(commandID.WHISP, "/w");
        put(commandID.USERS, "/users");
        put(commandID.EXIT, "/exit");
    }};

    public Client(Controller controller) {

        this.controller = controller;

        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);


        jbSend.addActionListener(e -> sendMsg());
        jtf.addActionListener(e -> sendMsg());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                controller.closeConnection();
            }
        });
    }

    private void sendMsg() {
        if (!jtf.getText().trim().isEmpty()) {
            if (jtf.getText().startsWith(Client.commands.get(commandID.HELP))) {
                addMessage("Доступные команды:\n" +
                           "/auth [login] [password] - подключиться к серверу используя свои [login] и [password];\n" +
                           "/reg [login] [password] [nick] - зарегистрироваться на сервере;\n" +
                           "/w [nick] [message] - приватное сообщение пользователю с укзанным [nick];\n" +
                           "/users - показывает участников, которые онлайн в данный момент времени;\n" +
                           "/exit - выход из чата;\n" +
                           "/help - отображение доступных команд\n");
            } else if (jtf.getText().startsWith(Client.commands.get(commandID.EXIT))){
                controller.closeConnection();
                Runtime.getRuntime().exit(0);
            }
            else {
                controller.sendMessage(jtf.getText());
            }
            jtf.setText("");
            jtf.grabFocus();
        }
    }

    @Override
    public void addMessage(String w) {
        jta.append(w);
        jta.append("\n");
    }

    @Override
    public void showUI() {
        setVisible(true);
    }
}
