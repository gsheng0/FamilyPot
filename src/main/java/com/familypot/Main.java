package com.familypot;

import com.familypot.model.Action;
import com.familypot.model.Decider;
import com.familypot.model.Merritt;
import com.familypot.model.Player;
import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;
import com.familypot.model.cards.Rank;
import com.familypot.model.cards.Suit;
import com.familypot.service.*;
import com.familypot.utils.Constants;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        List<Player> players = new ArrayList<>();
//        for(int i = 0; i < 7; i++){
//            players.add(new Player("Player " + i, 1000, new Decider() {
//                @Override
//                public int act(List<Action> options) {
//                    if((int)(Math.random() * 10) > 1){
//                        return 1;
//                    }
//                    if((int)(Math.random() * 10) > 5){
//                        return 0;
//                    }
//                    return 2;
//                }
//            }));
//        }
//        players.add(new Player("Merritt", 10000000, new Merritt()));
//        TexasHoldemRunnerService runnerService = new TexasHoldemRunnerService();
//        runnerService.runHand(players, 1, 2);
//        HashMap<String, Long> hands = calculateHandFrequencies();
//        printSortedHashMap(hands);
        EquityCalculatorService equityCalculatorService = new BruteForceTexasHoldemEquityCalculatorServiceImpl();
        System.out.println(equityCalculatorService.calculateEquity(List.of(Card.ACE_OF_CLUBS, Card.ACE_OF_SPADES), List.of(Card.KING_OF_CLUBS, Card.KING_OF_SPADES), new ArrayList<>()));

    }
    public static void printSortedHashMap(HashMap<String, Long> hashMap) {
        // Convert the HashMap into a List of Map.Entry objects
        List<Map.Entry<String, Long>> entryList = new ArrayList<>(hashMap.entrySet());

        // Sort the List based on the values of Map.Entry objects
        Collections.sort(entryList, Map.Entry.comparingByValue());

        // Iterate through the sorted List and print out the key-value pairs
        System.out.println("Sorted HashMap by Value:");
        for (Map.Entry<String, Long> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static HashMap<String, Long> calculateHandFrequencies(){
        HashMap<String, Long> map = new HashMap<>();
        List<Card> deck = Arrays.asList(Card.CARD_LIST);
        long counter = 0L;
        for(int a = 0; a < deck.size(); a++){
            for(int b = a + 1; b < deck.size(); b++){
                for(int c = b + 1; c < deck.size(); c++) {
                    for (int d = c + 1; d < deck.size(); d++) {
                        for (int e = d + 1; e < deck.size(); e++) {
                            for (int f = e + 1; f < deck.size(); f++) {
                                for(int g = f + 1; g < deck.size(); g++) {
                                    for(int h = g + 1; h < deck.size(); h++) {
                                        List<Card> cards = new ArrayList<>();
                                        cards.add(deck.get(a));
                                        cards.add(deck.get(b));
                                        cards.add(deck.get(c));
                                        cards.add(deck.get(d));
                                        cards.add(deck.get(e));
                                        cards.add(deck.get(f));
                                        List<String> hands = classify(cards);
                                        for (String hand : hands) {
                                            if (!map.containsKey(hand)) {
                                                map.put(hand, 1L);
                                            } else {
                                                map.replace(hand, map.get(hand) + 1L);
                                            }
                                        }
                                        counter++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("HANDS: " + counter);
        return map;
    }
    public static List<String> classify(List<Card> cards) {
        List<String> output = new ArrayList<>();
        HashMap<Rank, Integer> rankCounts = new HashMap<>();
        HashMap<Suit, Integer> suitCounts = new HashMap<>();
        cards.sort(Comparator.comparingInt(card -> card.rank.number));

        for (Card card : cards) {
            Rank rank = card.rank;
            Suit suit = card.suit;
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
            suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
        }

        for (int count : suitCounts.values()) {
            if (count >= 5) {
                output.add(FLUSH_5);
                if (count >= 6) {
                    output.add(FLUSH_6);
                }
            }
        }

        int pairCount = 0;
        int tripCount = 0;
        int quadCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            } else if (count == 3) {
                tripCount++;
            } else if (count == 4) {
                quadCount++;
            }
        }

        if (quadCount > 0) {
            output.add(FOUR_OF_A_KIND);
            if(pairCount > 0){
                output.add(FULLER_HOUSE);
            }
        }
        if (tripCount > 0 && pairCount > 0) {
            output.add(FULL_HOUSE);
        } else if (tripCount > 0) {
            output.add(THREE_OF_A_KIND);
            if (tripCount == 2) {
                output.add(DOUBLE_THREE_OF_A_KIND);
            }
        }
        if (pairCount > 0) {
            output.add(ONE_PAIR);
            if (pairCount >= 2) {
                output.add(TWO_PAIR);
                if(pairCount == 3){
                    output.add(THREE_PAIR);
                }
            }
        }

        // Check for straights
        List<Card> straight = new ArrayList<>();
        for (Card card : cards) {
            if (straight.isEmpty() || card.rank.number == straight.get(straight.size() - 1).rank.number + 1) {
                straight.add(card);
            } else {
                if (straight.size() >= 5) {
                    output.add(STRAIGHT_5);
                    if (straight.size() == 6) {
                        output.add(STRAIGHT_6);
                    }
                }
                straight.clear();
                straight.add(card);
            }
        }
        if (straight.size() >= 5) {
            output.add(STRAIGHT_5);
            if (straight.size() == 6) {
                output.add(STRAIGHT_6);
            }
        }

        // Check for straight flush
        if (output.contains(STRAIGHT_5) && output.contains(FLUSH_5)) {
            output.add(STRAIGHT_FLUSH_5);
        }
        if (output.contains(STRAIGHT_6) && output.contains(FLUSH_6)) {
            output.add(STRAIGHT_FLUSH_6);
        }

        return output;
    }
//    public static List<String> classify(List<Card> cards) {
//        List<String> output = new ArrayList<>();
//        HashMap<Rank, Integer> rankCounts = new HashMap<>();
//        HashMap<Suit, Integer> suitCounts = new HashMap<>();
//        cards.sort(Comparator.comparingInt(card -> card.rank.number));
//        cards.forEach(card -> {
//            Rank rank = card.rank;
//            Suit suit = card.suit;
//            if (!rankCounts.containsKey(rank)) {
//                rankCounts.put(rank, 1);
//            } else {
//                rankCounts.replace(rank, rankCounts.get(rank) + 1);
//            }
//
//            if (!suitCounts.containsKey(suit)) {
//                suitCounts.put(suit, 1);
//            } else {
//                suitCounts.put(suit, suitCounts.get(suit) + 1);
//            }
//        });
//        for (int count : suitCounts.values()) {
//            if (count == 5) {
//                output.add(FLUSH_5);
//            }
//            if (count > 5) {
//                output.add(FLUSH_5);
//                output.add(FLUSH_6);
//            }
//        }
//        int pairCount = 0;
//        int tripCount = 0;
//        int quadCount = 0;
//        for (int count : rankCounts.values()) {
//            if (count == 2) {
//                pairCount++;
//            } else if (count == 3) {
//                tripCount++;
//            } else if (count == 4) {
//                quadCount++;
//            }
//        }
//        if (quadCount != 0) {
//            if (pairCount != 0) {
//                output.add(FULLER_HOUSE);
//            }
//            output.add(FOUR_OF_A_KIND);
//        }
//        if (tripCount != 0) {
//            if (pairCount != 0) {
//                output.add(FULL_HOUSE);
//            }
//            if (tripCount == 2) {
//                output.add(DOUBLE_THREE_OF_A_KIND);
//            }
//            output.add(THREE_OF_A_KIND);
//        }
//        if (pairCount != 0) {
//            output.add(ONE_PAIR);
//            if (pairCount == 2) {
//                output.add(TWO_PAIR);
//            }
//            if (pairCount == 3) {
//                output.add(TWO_PAIR);
//                output.add(THREE_PAIR);
//            }
//        }
//
//        List<Card> straight = new ArrayList<>();
//        Rank prevRank = null;
//
//        for (Card card : cards) {
//            if (straight.isEmpty()) {
//                straight.add(card);
//                prevRank = card.rank;
//            } else {
//                if (card.rank.number == prevRank.number + 1 || (prevRank.number == 13 && card.rank.number == 1)) {
//                    straight.add(card);
//                    prevRank = card.rank;
//                } else {
//                    straight.clear();
//                    straight.add(card);
//                    prevRank = card.rank;
//                }
//            }
//        }
//        if(straight.size() >= 5){
//            output.add(STRAIGHT_5);
//            if(straight.size() == 6){
//                output.add(STRAIGHT_6);
//            }
//            int flushCount = 0;
//            Suit prevSuit = null;
//            for(Card card : straight){
//                if(card.suit != prevSuit){
//                    flushCount = 0;
//                    prevSuit = card.suit;
//                } else{
//                    flushCount++;
//                    if(flushCount == 5){
//                        output.add(STRAIGHT_FLUSH_5);
//                    }
//                    if(flushCount == 6){
//                        output.add(STRAIGHT_FLUSH_6);
//                    }
//                }
//            }
//        }
//        return output;
//    }
    public static final String STRAIGHT_FLUSH_6 = "Six card straight flush";
    public static final String STRAIGHT_FLUSH_5 = "Five card straight flush";
    public static final String FULLER_HOUSE = "Fuller house";
    public static final String FULL_HOUSE = "Full house";
    public static final String FLUSH_6 = "Six card flush";
    public static final String FLUSH_5 = "Five card flush";
    public static final String STRAIGHT_6 = "Six card straight";
    public static final String STRAIGHT_5 = "Five card straight";
    public static final String FOUR_OF_A_KIND = "Four of a kind";
    public static final String DOUBLE_THREE_OF_A_KIND = "Double three of a kind";
    public static final String THREE_PAIR = "Three pair";
    public static final String TWO_PAIR = "Two pair";
    public static final String THREE_OF_A_KIND = "Three of a kind";
    public static final String ONE_PAIR = "One pair";
    public static final String HIGH_CARD = "High card";

}