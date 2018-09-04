package com.antipov.homework;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendMessageListener implements ActionListener {

    private JTextComponent inputText;
    private JTextArea outputText;
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public SendMessageListener(JTextComponent inputText, JTextArea outputText) {
        this.inputText = inputText;
        this.outputText = outputText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (outputText == null | inputText == null) return;
        if (!inputText.getText().replace(" ", "").equals("")) {
            outputText.append(timeFormat.format(LocalDateTime.now()) + ": " + inputText.getText() + "\n");
        }
        inputText.setText("");
    }
}
