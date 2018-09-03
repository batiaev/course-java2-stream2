package com.peanig.lesson4;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendMessageListener implements ActionListener {
    private JTextComponent inputText;
    private JTextArea outputTextArea;
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public SendMessageListener(JTextComponent inputText, JTextArea outputTextArea) {
        this.inputText = inputText;
        this.outputTextArea = outputTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inputText.getText().trim().length() > 0) {
            outputTextArea.append(timeFormat.format(LocalDateTime.now()) + ": " + inputText.getText() + "\n");
            inputText.setText("");
        }
    }
}