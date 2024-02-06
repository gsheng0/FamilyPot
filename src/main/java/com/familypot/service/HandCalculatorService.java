package com.familypot.service;

import com.familypot.model.cards.Card;

public interface HandCalculatorService {
    int calculateHandRepresentation(Card[] cards);
}
