package com.familypot.service;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class BruteForceTexasHoldemEquityCalculatorServiceImpl implements EquityCalculatorService{
    private final HandCalculatorService handCalculatorService = new HandCalculatorServiceImpl();
    private int total = 0;
    private int wins = 0;
    @Override
    public double calculateEquity(List<Card> hand1, List<Card> hand2, List<Card> board) {
        Deck deck = new Deck();
        hand1.forEach(deck::deal);
        hand2.forEach(deck::deal);
        board.forEach(deck::deal);

        List<Card> remainingCards = deck.getRemainingCards();
        total = 0;
        wins = 0;
        calculateEquityRecursive(hand1, hand2, board, remainingCards, 0, new ArrayList<>(), 5 - board.size());
        return ((double) wins) / total;
    }

    private void calculateEquityRecursive(List<Card> hand1, List<Card> hand2, List<Card> board, List<Card> remainingCards,
                                          int depth, List<Card> currentBoard, int limit) {
        if (limit == 3) {
            total++;
            int handStrength1 = handCalculatorService.calculateHandRepresentation(currentBoard, board, hand1);
            int handStrength2 = handCalculatorService.calculateHandRepresentation(currentBoard, board, hand2);
            if (handStrength1 > handStrength2) {
                wins++;
            }
            return;
        }

        for (int i = 0; i < remainingCards.size(); i++) {
            List<Card> newBoard = new ArrayList<>(currentBoard);
            newBoard.add(remainingCards.get(i));
            calculateEquityRecursive(hand1, hand2, board, remainingCards.subList(i + 1, remainingCards.size()), depth + 1, newBoard, limit);
        }
    }
}
