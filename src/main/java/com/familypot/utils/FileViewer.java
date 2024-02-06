package com.familypot.utils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FileViewer extends JFrame {
    private String filePath;
    public FileViewer() {
        setTitle("File Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
//        JFileChooser fileChooser = new JFileChooser();
//        int returnValue = fileChooser.showOpenDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            filePath = selectedFile.getAbsolutePath();
//            readFile(filePath);
//        }
//        String content = readFile(filePath);
//        System.out.println(content);
    }

    private String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(30, 92,58));
        g2d.fillRect(0, 0, 1200, 800);


        g2d.setColor(Color.BLACK);

        int startX = 150;
        int startY = 550;
        int playerSpacing = 250;
        int stackSize = 1000; // Placeholder stack size
        for (int i = 0; i < 4; i++) {
            int x = startX + i * playerSpacing;
            g2d.drawRect(x, startY, 50, 50); // Draw player seat
            g2d.drawString("Stack: " + stackSize, x + 5, startY + 70); // Draw stack size
        }
    }

    public static void main(String[] args) {
        new FileViewer().setVisible(true);
    }
}
