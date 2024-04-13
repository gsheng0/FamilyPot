package com.familypot.service;

import com.familypot.model.cards.Card;

import java.util.List;

public interface EquityCalculatorService {
    double calculateEquity(List<Card> hand1, List<Card> hand2, List<Card> board);

}
