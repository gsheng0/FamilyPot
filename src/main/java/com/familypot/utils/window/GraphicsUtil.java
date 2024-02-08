package com.familypot.utils.window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Suit;
import com.familypot.utils.Constants;

import javax.imageio.ImageIO;

public class GraphicsUtil {
    private static Graphics g;
    public static void setGraphics(Graphics g){
        GraphicsUtil.g = g;
    }
    public static void drawCard(Card card, int x, int y){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WindowConfigs.CARD_WIDTH, WindowConfigs.CARD_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, WindowConfigs.CARD_WIDTH, WindowConfigs.CARD_HEIGHT);

        if(card.suit.equals(Suit.DIAMONDS) || card.suit.equals(Suit.HEARTS)){
            g.setColor(Color.RED);
        }
        g.setFont(new Font("Times New Roman", Font.BOLD, WindowConfigs.CARD_FONT_SIZE));
        g.drawString(card.rank.symbol, x + WindowConfigs.CARD_SYMBOL_X_OFFSET, y + WindowConfigs.CARD_SYMBOL_Y_OFFSET);
        if(card.suit.equals(Suit.DIAMONDS)){
            drawDiamond(x + WindowConfigs.CARD_SUIT_X_OFFSET, y + WindowConfigs.CARD_SUIT_Y_OFFSET);
        } else if(card.suit.equals(Suit.SPADES)){
            drawSpade(x + WindowConfigs.CARD_SUIT_X_OFFSET, y + WindowConfigs.CARD_SUIT_Y_OFFSET);
        } else if(card.suit.equals(Suit.CLUBS)){
            drawClub(x + WindowConfigs.CARD_SUIT_X_OFFSET, y + WindowConfigs.CARD_SUIT_Y_OFFSET);
        } else if(card.suit.equals(Suit.HEARTS)){
            drawHeart(x + WindowConfigs.CARD_SUIT_X_OFFSET, y + WindowConfigs.CARD_SUIT_Y_OFFSET);
        }

    }

    public static void drawSpade(int x, int y){
        drawImage(Constants.RESOURCES_PATH + "spade.png", x, y, WindowConfigs.CARD_SUIT_SIZE, WindowConfigs.CARD_SUIT_SIZE);
    }
    public static void drawClub(int x, int y){
        drawImage(Constants.RESOURCES_PATH + "club.png", x, y, WindowConfigs.CARD_SUIT_SIZE, WindowConfigs.CARD_SUIT_SIZE);
    }
    public static void drawHeart(int x, int y){
        drawImage(Constants.RESOURCES_PATH + "heart.png", x, y, WindowConfigs.CARD_SUIT_SIZE, WindowConfigs.CARD_SUIT_SIZE);
    }
    public static void drawDiamond(int x, int y){
        drawImage(Constants.RESOURCES_PATH + "diamond.png", x, y, WindowConfigs.CARD_SUIT_SIZE, WindowConfigs.CARD_SUIT_SIZE);
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
