package com.familypot.service;

import com.familypot.model.cards.Card;

import java.util.List;

public interface HandCalculatorService {
    int calculateHandRepresentation(List<Card>... cards);
}
