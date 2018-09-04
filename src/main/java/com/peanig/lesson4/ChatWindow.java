package com.peanig.lesson4;

import javax.swing.*;
import java.awt.*;


public class ChatWindow extends JFrame {

    private JTextArea chatHistory;
    private JTextField inputText;
    private JButton sendButton;
    private SendMessageListener listener;

    public ChatWindow() {

        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setMinimumSize(new Dimension(150, 150));

        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setFocusable(false);

        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        historyPanel.add(chatHistory, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.PAGE_END);

        sendButton = new JButton("Send");
        sendButton.setFocusable(false);
        inputPanel.add(sendButton, BorderLayout.EAST);

        inputText = new JTextField();
        inputPanel.add(inputText, BorderLayout.CENTER);

        listener = new SendMessageListener(inputText, chatHistory);
        sendButton.addActionListener(listener);
        inputText.addActionListener(listener);


        setVisible(true);

    }

    public static void main(String[] args) {
        new ChatWindow();
    }
}