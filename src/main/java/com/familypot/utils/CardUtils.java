package com.familypot.utils;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;

import java.util.ArrayList;
import java.util.List;
public class CardUtils {
    public static List<Card[]> dealHoleCards(int numPlayers, int numCards, Deck deck){
        List<Card[]> holeCards = new ArrayList<>();

        for(int i = 0; i < numPlayers; i++){
            holeCards.add(new Card[numCards]);
        }

        for(int i = 0; i < numCards; i++){
            for(int x = 0; x < numPlayers; x++){
                holeCards.get(x)[i] = deck.deal();
            }
        }
        return holeCards;
    }
}
