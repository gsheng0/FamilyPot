package com.familypot.service;

import com.familypot.model.cards.Card;

public interface EquityCalculatorService {
    public double calculateEquity(Card[] hand1, Card[] hand2, Card[] board);

}
