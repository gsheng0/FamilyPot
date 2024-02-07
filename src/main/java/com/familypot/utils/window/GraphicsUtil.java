package com.familypot.utils.window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Suit;

import javax.imageio.ImageIO;

public class GraphicsUtil {
    private static Graphics g;
    public static void setGraphics(Graphics g){
        GraphicsUtil.g = g;
    }
    public static void drawCard(Card card, int x, int y){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 100, 140);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 140);

        if(card.suit.equals(Suit.DIAMONDS) || card.suit.equals(Suit.HEARTS)){
            g.setColor(Color.RED);
        }
        g.setFont(new Font("Times New Roman", Font.BOLD, 32));
        g.drawString(card.rank.symbol, x + 5, y + 30);
        drawSpade(x + 10, y + 35);
    }

    public static void drawSpade(int x, int y){
        drawImage("src/main/resources/spade.png", x, y, 80, 80);
    }
    public static void drawImage(String imagePath, int x, int y, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            g.drawImage(image, x, y, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
