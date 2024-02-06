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
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            readFile(filePath);
        }
        String content = readFile(filePath);
        System.out.println(content);
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1200, 800);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileViewer().setVisible(true));
    }
}
