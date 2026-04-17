package com.leadmanager.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Lead Intelligence Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Lead Intelligence Manager", SwingConstants.CENTER);
        add(label);
    }
}