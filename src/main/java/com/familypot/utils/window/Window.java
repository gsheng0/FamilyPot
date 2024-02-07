package com.familypot.utils.window;

import com.familypot.model.cards.Card;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Window extends JFrame {
    private String filePath;
    public Window() {
        setTitle("Window");
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
        GraphicsUtil.setGraphics(g);
        GraphicsUtil.drawCard(Card.ACE_OF_SPADES, 200, 200);
        GraphicsUtil.drawCard(Card.KING_OF_SPADES, 310, 200);
        GraphicsUtil.drawCard(Card.QUEEN_OF_SPADES, 420, 200);
        GraphicsUtil.drawCard(Card.JACK_OF_SPADES, 530, 200);
        GraphicsUtil.drawCard(Card.TEN_OF_SPADES, 640, 200);

        g2d.setColor(Color.BLACK);

        for(int[] coords : WindowConfigs.PLAYER_COORDS){
            int x = coords[0];
            int y = coords[1];
            g2d.drawRect(x, y, 50, 50);
            g2d.drawString("Stack: " + 1000, x + 5, y + 70);
        }
    }
    public void drawCard(Card card, int x, int y){

    }


    public static void main(String[] args) {
        new Window().setVisible(true);
    }
}
