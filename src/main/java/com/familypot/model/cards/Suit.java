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
    public static Suit parseSuit(String suit){
        if(suit.equals("Hearts")){
            return HEARTS;
        } else if(suit.equals("Diamonds")){
            return DIAMONDS;
        } else if(suit.equals("Clubs")){
            return CLUBS;
        } else if(suit.equals("Spades")){
            return SPADES;
        }
        throw new RuntimeException("Illegal suit name: " + suit);
    }

    @Override
    public int hashCode() {
        if(this.name.equals("Heart")){
            return 0;
        } else if(this.name.equals("Diamonds")){
            return 1;
        } else if(this.name.equals("Clubs")){
            return 2;
        } else if(this.name.equals("Spades")){
            return 3;
        }
        throw new RuntimeException("Illegal suit name: " + this.name);
    }
}
