package com.leadmanager.app;

import javax.swing.SwingUtilities;
import com.leadmanager.view.MainMenuFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame();
            frame.setVisible(true);
        });
    }
}