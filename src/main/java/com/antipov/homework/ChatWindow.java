package com.antipov.homework;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JTextArea outputText;
    private JTextField inputText;
    private JButton sendButton;
    private SendMessageListener listener;

    public ChatWindow() {
        setTitle("Chat window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (screenSize.getWidth() / 2 - 200),
                  (int) (screenSize.getHeight() / 2 - 200),
                  WINDOW_WIDTH,
                  WINDOW_HEIGHT);

        setLayout(new BorderLayout());

        outputText = createTextArea();
        inputText = createInputField();
        sendButton = createButton("Send");
        listener = new SendMessageListener(inputText, outputText);
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(inputText, BorderLayout.CENTER);
        jp.add(sendButton, BorderLayout.EAST);

        add(outputText, BorderLayout.CENTER);
        add(jp, BorderLayout.SOUTH);

        setListeners();

        outputText.append("======= " + dateFormat.format(LocalDateTime.now()) + " =======\n");
        setVisible(true);
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea();
        area.setBackground(new Color(220, 220, 255));
        area.setEditable(false);
        return area;
    }

    private JTextField createInputField() {
        //Настроить поле при необходимости
        return new JTextField();
    }

    private JButton createButton(String text) {
        JButton button = new JButton();
        sendButton.setText(text);
        return button;
    }

    private void setListeners(){
        inputText.addActionListener(listener);
        sendButton.addActionListener(listener);
    }
}
