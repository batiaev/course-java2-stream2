package com.batiaev.java2.HomeWork4;

import javax.swing.*;
import java.awt.*;

public class Chat extends JFrame {

    private JTextArea textArea;
    private JTextField textField;
    private JButton button;
    private String message;
    private static final int WIDTH = 360;
    private static final int HEIGHT = 720;

    //
    private GridBagLayout layout;

    private GridBagConstraints sPaneConstr;
    private GridBagConstraints textFieldConstr;
    private GridBagConstraints buttonConstr;

    public Chat() {
        layout = new GridBagLayout();
        sPaneConstr = new GridBagConstraints();
        textFieldConstr = new GridBagConstraints();
        buttonConstr = new GridBagConstraints();
        setLayout(layout);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        init();
        setVisible(true);
    }

    // Создание компонента JTextArea для отображения переписки.
    private JScrollPane makeJScrollPane() {

        textArea = new JTextArea(40, 0);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getHorizontalScrollBar().setEnabled(false);

        sPaneConstr.weightx = 1;
        sPaneConstr.weighty = 1;
        sPaneConstr.ipadx = 340;
        sPaneConstr.ipady = 560;
        sPaneConstr.fill = GridBagConstraints.BOTH;
        sPaneConstr.insets = new Insets(4, 4, 4, 4);
        sPaneConstr.anchor = GridBagConstraints.NORTH;
        sPaneConstr.gridwidth = 2;

        layout.setConstraints(scrollPane, sPaneConstr);

        return scrollPane;
    }

    // Создание поля для ввода текста
    private JTextField makeJTextField() {

        textField = new JTextField();
        textField.setFocusable(true);

        textFieldConstr.gridy = GridBagConstraints.PAGE_END;
        textFieldConstr.gridwidth = GridBagConstraints.REMAINDER;
        textFieldConstr.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstr.insets = new Insets(4, 4, 4, 4);
        layout.setConstraints(textField, textFieldConstr);

        return textField;
    }

    // Создание кнопки
    private JButton makeJButton() {

        button = new JButton("Send");

        buttonConstr.anchor = GridBagConstraints.LAST_LINE_END;
        buttonConstr.gridwidth = GridBagConstraints.REMAINDER;
        buttonConstr.insets = new Insets(4,4, 4, 4);
        layout.setConstraints(button, buttonConstr);

        return button;
    }

    // Инициализация всех элементов GUI
    private void init() {
        add(makeJScrollPane());
        add(makeJTextField());
        add(makeJButton());
        setListeners();
    }

    // Регистрация слушателей событий
    private void setListeners() {

        textField.addActionListener(e -> {
            message = textField.getText().trim();
            textArea.append(message + " ");
            textField.setText("");
            textField.grabFocus();
        });

        button.addActionListener(e -> {
            message = textField.getText().trim();
            textArea.append(message + " ");
            textField.setText("");
            textField.grabFocus();
        });
    }

}
