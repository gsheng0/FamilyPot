package com.familypot.model.cards;

import java.util.ArrayList;
import java.util.HashMap;
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
    public boolean deal(Card card){
        if(cardStack.remove(card)){
            dealtCards.add(card);
            return true;
        }
        return false;
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

    public int count(Suit suit){
        return (int) cardStack.stream().filter(card -> card.suit.equals(suit)).count();
    }
    public int count(Rank rank){
        return (int) cardStack.stream().filter(card -> card.rank.equals(rank)).count();
    }
    public HashMap<Rank, Integer> countRanks(){
        HashMap<Rank, Integer> map = new HashMap<>();
        cardStack.forEach(card -> {
            Rank rank = card.rank;
            if(!map.containsKey(rank)){
                map.put(rank, 0);
            }
            map.replace(rank, map.get(rank) + 1);
        });
        return map;
    }
    public HashMap<Suit, Integer> countSuits(){
        HashMap<Suit, Integer> map = new HashMap<>();
        cardStack.forEach(card -> {
            Suit suit = card.suit;
            if(!map.containsKey(suit)){
                map.put(suit, 0);
            }
            map.replace(suit, map.get(suit) + 1);
        });
        return map;
    }
    public int getCardStackSize(){
        return cardStack.size();
    }

    private static int randomInteger(int start, int end){
        return (int)(Math.random() * (end - start)) + start;
    }
}
