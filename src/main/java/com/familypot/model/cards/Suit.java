package com.familypot.model.cards;

public class Suit {
    public static final Suit HEARTS = new Suit("Hearts");
    public static final Suit DIAMONDS = new Suit("Diamonds");
    public static final Suit CLUBS = new Suit("Clubs");
    public static final Suit SPADES = new Suit("Spades");
    public static final Suit[] SUITS = new Suit[]{HEARTS, DIAMONDS, CLUBS, SPADES};
    public final String name;
    private Suit(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Suit other)){
            return false;
        }
        return other.name.equals(this.name);
    }
}
