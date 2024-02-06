package com.familypot.model;

import com.familypot.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
    private List<Player> players;
    private int pot;
    private Card[] board;
    private List<Card[]> holeCards;
    public Hand(List<Player> players){
        this.players = players;
        this.pot = 0;
        this.board = new Card[5];
        this.holeCards = new ArrayList<>();
    }
    public abstract void runHand();
}
