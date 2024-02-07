package com.familypot.utils.window.event;

import com.familypot.model.cards.Card;

public class CardEvent extends Event{
    private Card card;
    public CardEvent(Card card){
        super("Dealer");
        this.card = card;
    }
    public Card getCard() { return card; }
}
