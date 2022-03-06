package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        final int APP_SIZE = 800;

        JFrame mainWindow = new JFrame();

        mainWindow.setTitle("Змейка");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(APP_SIZE,APP_SIZE));
        mainWindow.getContentPane().add(panel);

        mainWindow.pack();
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
