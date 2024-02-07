package com.familypot.utils.window.event;

import com.familypot.model.Action;
import com.familypot.model.cards.Card;

public class Event {
    private String actor;
    public Event(String actor){
        this.actor = actor;
    }
    public String getActor() { return actor; }
    public static Event parseEvent(String eventString){
        if(eventString.length() < 1){
            throw new RuntimeException("Invalid event format: " + eventString);
        }
        if(eventString.startsWith("\t")){
            return new CardEvent(Card.parseCard(eventString.substring(1)));
        }
        return new PlayerEvent(Action.parseAction(eventString));
    }
}
