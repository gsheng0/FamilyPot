package com.familypot.service;

import com.familypot.model.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BruteForceTexasHoldemEquityCalculatorServiceImpl implements EquityCalculatorService{
    HandCalculatorService handCalculatorService = new HandCalculatorServiceImpl();
    @Override
    public double calculateEquity(Card[] hand1, Card[] hand2, Card[] board) {
        List<Card> boardCards = Arrays.stream(board).filter(Objects::nonNull).toList();
        if(boardCards.size() == 0){

        }

        return 0;
    }
}
