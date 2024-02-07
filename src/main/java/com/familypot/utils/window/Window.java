package com.familypot.utils.window;

import com.familypot.model.cards.Card;
import com.familypot.model.Action;
import com.familypot.utils.window.event.Event;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Window extends JFrame {
    private String filePath;
    private List<Event> events;
    private HashMap<String, Card[]> nameToHoleCardMap;
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
    public Window(String actionsString){
        String[] lines = actionsString.split("\n");
        events = new ArrayList<>();
        nameToHoleCardMap = new HashMap<>();
        int index = 0;
        while(lines[index].startsWith("[")){
            String[] cardStrings = lines[index].substring(lines[index].indexOf("\t") + 1).split(", ");
            nameToHoleCardMap.put(
                    lines[index].substring(1, lines[index].indexOf("]")),
                    new Card[]{
                            Card.parseCard(cardStrings[0]),
                            Card.parseCard(cardStrings[1])
                    }
            );
            index++;
        }
        for(int i = index + 1; i < lines.length; i++){
            events.add(Event.parseEvent(lines[i]));
        }
        for(String string : nameToHoleCardMap.keySet()){
            System.out.println(string + ": "+ nameToHoleCardMap.get(string)[0] + " " + nameToHoleCardMap.get(string)[1]);
        }
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
        GraphicsUtil.drawCard(Card.ACE_OF_SPADES, 300, 350);
        GraphicsUtil.drawCard(Card.KING_OF_HEARTS, 410, 350);
        GraphicsUtil.drawCard(Card.QUEEN_OF_CLUBS, 520, 350);
        GraphicsUtil.drawCard(Card.JACK_OF_DIAMONDS, 630, 350);
        GraphicsUtil.drawCard(Card.TEN_OF_SPADES, 740, 350);

        g2d.setColor(Color.BLACK);

        for(int[] coords : WindowConfigs.PLAYER_COORDS){
            int x = coords[0];
            int y = coords[1];
            g2d.drawRect(x, y, 50, 50);
            g2d.drawString("Stack: " + 1000, x + 5, y + 70);
        }
    }



    public static void main(String[] args) {
        new Window("[Player 0]: \tFour of Diamonds, King of Diamonds\n" +
                "[Player 1]: \tSeven of Clubs, Jack of Clubs\n" +
                "[Player 2]: \tNine of Clubs, Six of Clubs\n" +
                "[Player 3]: \tFour of Spades, Ten of Diamonds\n" +
                "[Player 4]: \tTen of Clubs, Three of Spades\n" +
                "[Merritt]: \tKing of Hearts, Two of Clubs\n" +
                "\n" +
                "[Player 2]: CALL 2\n" +
                "[Player 3]: CALL 2\n" +
                "[Player 4]: CALL 2\n" +
                "[Merritt]: RAISE 4\n" +
                "[Player 0]: CALL 4\n" +
                "[Player 1]: CALL 4\n" +
                "[Player 2]: CALL 4\n" +
                "[Player 3]: CALL 4\n" +
                "[Player 4]: CALL 4\n" +
                "\n" +
                "\tEight of Clubs\n" +
                "\tKing of Spades\n" +
                "\tNine of Hearts\n" +
                "\n" +
                "[Player 0]: CHECK\n" +
                "[Player 1]: CHECK\n" +
                "[Player 2]: CHECK\n" +
                "[Player 3]: CHECK\n" +
                "[Player 4]: CHECK\n" +
                "[Merritt]: BET 16\n" +
                "[Player 0]: RAISE 32\n" +
                "[Player 1]: RAISE 64\n" +
                "[Player 2]: CALL 64\n" +
                "[Player 3]: CALL 64\n" +
                "[Player 4]: CALL 64\n" +
                "[Merritt]: RAISE 128\n" +
                "[Player 0]: CALL 128\n" +
                "[Player 1]: CALL 128\n" +
                "[Player 2]: CALL 128\n" +
                "[Player 3]: CALL 128\n" +
                "[Player 4]: CALL 128\n" +
                "\n" +
                "\tSix of Diamonds\n" +
                "\n" +
                "[Player 0]: CHECK\n" +
                "[Player 1]: CHECK\n" +
                "[Player 2]: FOLD\n" +
                "[Player 3]: CHECK\n" +
                "[Player 4]: CHECK\n" +
                "[Merritt]: BET 552\n" +
                "[Player 0]: CALL 552\n" +
                "[Player 1]: CALL 552\n" +
                "[Player 3]: CALL 552\n" +
                "[Player 4]: CALL 552\n" +
                "\n" +
                "\tSeven of Spades\n" +
                "\n" +
                "[Player 0]: CHECK\n" +
                "[Player 1]: CHECK\n" +
                "[Player 3]: CHECK\n" +
                "[Player 4]: CHECK\n" +
                "[Merritt]: BET 1932\n" +
                "[Player 0]: FOLD\n" +
                "[Player 1]: CALL 1932\n" +
                "[Player 3]: CALL 1932\n" +
                "[Player 4]: CALL 1932\n" +
                "\n" +
                "\tEight of Clubs\n" +
                "\tKing of Spades\n" +
                "\tNine of Hearts\n" +
                "\tSix of Diamonds\n" +
                "\tSeven of Spades\n" +
                "\n" +
                "Player 3 has won with Straight\n").setVisible(true);
    }
}
