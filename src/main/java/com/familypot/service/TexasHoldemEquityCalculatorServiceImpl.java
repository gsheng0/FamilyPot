package com.familypot.service;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;
import com.familypot.model.cards.Suit;

import java.util.HashMap;
import java.util.List;

public class TexasHoldemEquityCalculatorServiceImpl implements EquityCalculatorService{
    @Override
    public double calculateEquity(List<Card> hand1, List<Card> hand2, List<Card> board) {
         boolean isPocketPair1 = hand1.get(0).rank.equals(hand1.get(1).rank);
         boolean isPocketPair2 = hand2.get(0).rank.equals(hand2.get(1).rank);
         Deck deck = new Deck();
         for(Card card : hand1){
             deck.deal(card);
         }
         for(Card card : hand2){
             deck.deal(card);
         }
         for(Card card : board){
             deck.deal(card);
         }
         return 0.0;
    }

    /**
     *
     * @param hand1 hero's pocket pair
     * @param hand2 villain's pocket pair
     * @param board board cards
     * @param deck deck object with the cards in the hands/board removed
     * @return
     */
    private double calculateEquityTwoPocketPairs(Card[] hand1, Card[] hand2, Card[] board, Deck deck){
        double output = 0.0;
        if(hand1[0].rank.equals(hand2[0].rank)){
            return calculateEquityTwoEqualPocketPairs(hand1, hand2, board, deck);
        }
        return 1.0;
    }
    private double calculateEquityTwoEqualPocketPairs(Card[] hand1, Card[] hand2, Card[] board, Deck deck){
        HashMap<Suit, Integer> suitCounts = deck.countSuits();
        double output = 0.0;
        int deckSize = deck.getCardStackSize();
        for(Card card: hand1){
            int suitCount = suitCounts.get(card.suit);
            if(suitCount < 4){
                continue;
            }
            output += calculateFlushOdds(suitCount, deckSize, 1, board.length ); //TODO: FIX THIS LATER
        }
        return output;
    }
    private double calculateFlushOdds(int suitCount, int deckSize, int heldSuit, int cardsLeft){
        double output = 1.0;
        if(cardsLeft - heldSuit <= 0){
            return 0.0;
        }
        for(int i = 0; i < cardsLeft - heldSuit; i++){
            output *= (double) suitCount /deckSize;
            suitCount--;
            deckSize--;
            if(suitCount == 0 || deckSize == 0){
                return 0;
            }
        }
        return output;
    }
}
