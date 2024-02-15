package com.familypot.utils.window;

import com.familypot.model.cards.Card;
import com.familypot.model.Action;
import com.familypot.utils.FileHandler;
import com.familypot.utils.window.event.Event;
import com.familypot.utils.window.event.TableState;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Window extends JFrame implements MouseListener, KeyListener {
    private String filePath;
    private List<Event> events;
    private HashMap<String, Card[]> nameToHoleCardMap;
    private TableState tableState;
    int x = 0;
    int y = 0;
    int index = 0;
    public Window() {
        setTitle("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WindowConfigs.WINDOW_WIDTH, WindowConfigs.WINDOW_HEIGHT);
        addMouseListener(this);
    }
    public Window(String actionsString){
        setTitle("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WindowConfigs.WINDOW_WIDTH, WindowConfigs.WINDOW_HEIGHT);
        addMouseListener(this);
        addKeyListener(this);
        String[] lines = actionsString.split("\n");
        events = new ArrayList<>();
        nameToHoleCardMap = new HashMap<>();
        tableState = new TableState();
        int index = 0;
        while(lines[index].startsWith("[")){
            String name = lines[index].substring(1, lines[index].indexOf("]"));
            String[] cardStrings = lines[index].substring(lines[index].indexOf("\t") + 1).split(", ");
            nameToHoleCardMap.put(
                    lines[index].substring(1, lines[index].indexOf("]")),
                    new Card[]{
                            Card.parseCard(cardStrings[0]),
                            Card.parseCard(cardStrings[1])
                    }
            );
            Card[] holeCards = new Card[]{
                    Card.parseCard(cardStrings[0]),
                    Card.parseCard(cardStrings[1])};
            tableState.addPlayer(name, holeCards);
            index++;
        }
        for(int i = index + 1; i < lines.length; i++){
            if(lines[i].startsWith("END")){
                break;
            }
            if(lines[i].length() > 1){
                events.add(Event.parseEvent(lines[i]));
            }
        }
//        for(String string : nameToHoleCardMap.keySet()){
//            System.out.println(string + ": "+ nameToHoleCardMap.get(string)[0] + ", " + nameToHoleCardMap.get(string)[1]);
//        }
    }

    public void paint(Graphics g) {
        g.setColor(WindowConfigs.TABLE_COLOR);
        g.fillRect(0, 0, WindowConfigs.WINDOW_WIDTH, WindowConfigs.WINDOW_HEIGHT);
        g.setFont(WindowConfigs.DEFAULT_FONT);
        GraphicsUtil.setGraphics(g);

        List<Card> boardCards = tableState.getBoardCards();
        for(int i = 0; i < boardCards.size(); i++){
            GraphicsUtil.drawCard(boardCards.get(i),
                    WindowConfigs.BOARD_CARD_COORDS[i][0],
                    WindowConfigs.BOARD_CARD_COORDS[i][1]);
        }

        List<String> actions = tableState.getActions();
        g.setColor(Color.BLACK);
        for(int i = 0; i < actions.size(); i++){
            if(tableState.getFoldedPlayers().contains(i)){
                g.drawString("Folded", WindowConfigs.PLAYER_COORDS[i][0], WindowConfigs.PLAYER_COORDS[i][1]);
            } else{
                g.drawString(actions.get(i), WindowConfigs.PLAYER_COORDS[i][0], WindowConfigs.PLAYER_COORDS[i][1]);
            }
        }

        List<Card[]> holeCards = tableState.getHoleCards();
        for(int i = 0; i < holeCards.size(); i++){
            if(tableState.getFoldedPlayers().contains(i)){
                continue;
            }
            int holeCardXCoord = WindowConfigs.HOLE_CARD_COORDS[i][0];
            int holeCardYCoord = WindowConfigs.HOLE_CARD_COORDS[i][1];
            int playerXCoord = WindowConfigs.PLAYER_COORDS[i][0];
            int playerYCoord = WindowConfigs.PLAYER_COORDS[i][1];

            GraphicsUtil.drawCard(holeCards.get(i)[0], holeCardXCoord, holeCardYCoord);
            GraphicsUtil.drawCard(holeCards.get(i)[1],
                    holeCardXCoord + WindowConfigs.BOARD_CARDS_X_AXIS_SPACING, holeCardYCoord);
            g.setColor(Color.BLACK);
            g.drawRect(playerXCoord, playerYCoord, 50, 50);
            g.drawString("Stack: " + 1000, playerXCoord + 5, playerYCoord + 70);

        }

        g.drawString("Pot: " + tableState.getPot(), 500, 100);
        g.drawString("" + index, 10, 10);
//        GraphicsUtil.drawCard(Card.ACE_OF_HEARTS, x, y);
//        GraphicsUtil.drawCard(Card.JACK_OF_HEARTS, x + WindowConfigs.BOARD_CARDS_X_AXIS_SPACING, y);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println(x + " " + y);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            tableState.apply(events.get(index));
            index++;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {
        String text = FileHandler.readFile("The Perfect yet Bad Poker Hand");
        new Window(text).setVisible(true);
    }
}
