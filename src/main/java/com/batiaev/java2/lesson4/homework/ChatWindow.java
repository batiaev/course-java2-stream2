package com.batiaev.java2.lesson4.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatWindow extends JFrame implements ActionListener {
    JTextArea chatHistory;
    JButton sendButton;
    JTextField messageText;

    public ChatWindow() {
        init();
        //addMenu();
        setVisible(true);
    }

    public static void main(String[] args) {
        ChatWindow myWindow = new ChatWindow();
    }

    private void init() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setMinimumSize(new Dimension(150, 150));

        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setFocusable(false);

        JScrollPane areaScrollPane = new JScrollPane(chatHistory);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(areaScrollPane, BorderLayout.CENTER);

        JPanel endPanel = new JPanel();
        endPanel.setLayout(new BorderLayout());
        add(endPanel, BorderLayout.PAGE_END);

        sendButton = new JButton("Send");
        sendButton.setFocusable(false);
        sendButton.addActionListener(this);
        endPanel.add(sendButton, BorderLayout.EAST);

        messageText = new JTextField();
        messageText.addActionListener(this);
        endPanel.add(messageText, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (messageText.getText().trim().length() > 0) {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            chatHistory.append(df.format(today) + ": " + messageText.getText().trim() + "\n");
            messageText.setText("");
        }
    }
}
