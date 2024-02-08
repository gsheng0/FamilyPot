package com.familypot.utils.window.event;

import com.familypot.model.Player;
import com.familypot.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class TableState {
    private List<Card> boardCards;
    private List<Player> players;
    private List<Card[]> holeCards;
    private int pot = 0;

    public TableState(){
        boardCards = new ArrayList<>();
        players = new ArrayList<>();
        holeCards = new ArrayList<>();
    }
    public void addPlayer(String playerName, Card[] cards){
        players.add(new Player(playerName, 1000, options -> 0));
        holeCards.add(cards);
    }
    public void apply(Event event){
        if(event instanceof CardEvent cardEvent){
            boardCards.add(cardEvent.getCard());
        } else if(event instanceof PlayerEvent playerEvent){
            
        }
    }
}
