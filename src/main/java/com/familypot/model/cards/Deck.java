package com.familypot.model.cards;

import java.util.ArrayList;
import java.util.Stack;

public class Deck {
    private static final int DEFAULT_SHUFFLE_COUNT = 1000;
    private Stack<Card> cardStack;
    private ArrayList<Card> dealtCards;
    public Deck(){
        this.cardStack = new Stack<>();
        this.dealtCards = new ArrayList<>();
        for(Card card : Card.CARD_LIST){
            cardStack.push(card);
        }
    }
    public Card deal(){
        Card topCard = cardStack.pop();
        dealtCards.add(topCard);
        return topCard;
    }
    public void shuffle(){
        ArrayList<Card> tempDeck = new ArrayList<>();
        while(!cardStack.empty()){
            tempDeck.add(cardStack.pop());
        }
        for(int i = 0; i < DEFAULT_SHUFFLE_COUNT; i++){
            int firstIndex = randomInteger(0, tempDeck.size());
            int secondIndex = randomInteger(0, tempDeck.size());
            Card first = tempDeck.get(firstIndex);
            Card second = tempDeck.get(secondIndex);
            tempDeck.set(firstIndex, second);
            tempDeck.set(secondIndex, first);
        }
        for(Card card : tempDeck){
            cardStack.push(card);
        }
    }
    private static int randomInteger(int start, int end){
        return (int)(Math.random() * (end - start)) + start;
    }
}
