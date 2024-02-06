package com.familypot.model.cards;

public class Card {
    public static final Card TWO_OF_HEARTS = new Card(Rank.TWO, Suit.HEARTS);
    public static final Card TWO_OF_DIAMONDS = new Card(Rank.TWO, Suit.DIAMONDS);
    public static final Card TWO_OF_CLUBS = new Card(Rank.TWO, Suit.CLUBS);
    public static final Card TWO_OF_SPADES = new Card(Rank.TWO, Suit.SPADES);
    public static final Card THREE_OF_HEARTS = new Card(Rank.THREE, Suit.HEARTS);
    public static final Card THREE_OF_DIAMONDS = new Card(Rank.THREE, Suit.DIAMONDS);
    public static final Card THREE_OF_CLUBS = new Card(Rank.THREE, Suit.CLUBS);
    public static final Card THREE_OF_SPADES = new Card(Rank.THREE, Suit.SPADES);
    public static final Card FOUR_OF_HEARTS = new Card(Rank.FOUR, Suit.HEARTS);
    public static final Card FOUR_OF_DIAMONDS = new Card(Rank.FOUR, Suit.DIAMONDS);
    public static final Card FOUR_OF_CLUBS = new Card(Rank.FOUR, Suit.CLUBS);
    public static final Card FOUR_OF_SPADES = new Card(Rank.FOUR, Suit.SPADES);
    public static final Card FIVE_OF_HEARTS = new Card(Rank.FIVE, Suit.HEARTS);
    public static final Card FIVE_OF_DIAMONDS = new Card(Rank.FIVE, Suit.DIAMONDS);
    public static final Card FIVE_OF_CLUBS = new Card(Rank.FIVE, Suit.CLUBS);
    public static final Card FIVE_OF_SPADES = new Card(Rank.FIVE, Suit.SPADES);
    public static final Card SIX_OF_HEARTS = new Card(Rank.SIX, Suit.HEARTS);
    public static final Card SIX_OF_DIAMONDS = new Card(Rank.SIX, Suit.DIAMONDS);
    public static final Card SIX_OF_CLUBS = new Card(Rank.SIX, Suit.CLUBS);
    public static final Card SIX_OF_SPADES = new Card(Rank.SIX, Suit.SPADES);
    public static final Card SEVEN_OF_HEARTS = new Card(Rank.SEVEN, Suit.HEARTS);
    public static final Card SEVEN_OF_DIAMONDS = new Card(Rank.SEVEN, Suit.DIAMONDS);
    public static final Card SEVEN_OF_CLUBS = new Card(Rank.SEVEN, Suit.CLUBS);
    public static final Card SEVEN_OF_SPADES = new Card(Rank.SEVEN, Suit.SPADES);
    public static final Card EIGHT_OF_HEARTS = new Card(Rank.EIGHT, Suit.HEARTS);
    public static final Card EIGHT_OF_DIAMONDS = new Card(Rank.EIGHT, Suit.DIAMONDS);
    public static final Card EIGHT_OF_CLUBS = new Card(Rank.EIGHT, Suit.CLUBS);
    public static final Card EIGHT_OF_SPADES = new Card(Rank.EIGHT, Suit.SPADES);
    public static final Card NINE_OF_HEARTS = new Card(Rank.NINE, Suit.HEARTS);
    public static final Card NINE_OF_DIAMONDS = new Card(Rank.NINE, Suit.DIAMONDS);
    public static final Card NINE_OF_CLUBS = new Card(Rank.NINE, Suit.CLUBS);
    public static final Card NINE_OF_SPADES = new Card(Rank.NINE, Suit.SPADES);
    public static final Card TEN_OF_HEARTS = new Card(Rank.TEN, Suit.HEARTS);
    public static final Card TEN_OF_DIAMONDS = new Card(Rank.TEN, Suit.DIAMONDS);
    public static final Card TEN_OF_CLUBS = new Card(Rank.TEN, Suit.CLUBS);
    public static final Card TEN_OF_SPADES = new Card(Rank.TEN, Suit.SPADES);
    public static final Card JACK_OF_HEARTS = new Card(Rank.JACK, Suit.HEARTS);
    public static final Card JACK_OF_DIAMONDS = new Card(Rank.JACK, Suit.DIAMONDS);
    public static final Card JACK_OF_CLUBS = new Card(Rank.JACK, Suit.CLUBS);
    public static final Card JACK_OF_SPADES = new Card(Rank.JACK, Suit.SPADES);
    public static final Card QUEEN_OF_HEARTS = new Card(Rank.QUEEN, Suit.HEARTS);
    public static final Card QUEEN_OF_DIAMONDS = new Card(Rank.QUEEN, Suit.DIAMONDS);
    public static final Card QUEEN_OF_CLUBS = new Card(Rank.QUEEN, Suit.CLUBS);
    public static final Card QUEEN_OF_SPADES = new Card(Rank.QUEEN, Suit.SPADES);
    public static final Card KING_OF_HEARTS = new Card(Rank.KING, Suit.HEARTS);
    public static final Card KING_OF_DIAMONDS = new Card(Rank.KING, Suit.DIAMONDS);
    public static final Card KING_OF_CLUBS = new Card(Rank.KING, Suit.CLUBS);
    public static final Card KING_OF_SPADES = new Card(Rank.KING, Suit.SPADES);
    public static final Card ACE_OF_HEARTS = new Card(Rank.ACE, Suit.HEARTS);
    public static final Card ACE_OF_DIAMONDS = new Card(Rank.ACE, Suit.DIAMONDS);
    public static final Card ACE_OF_CLUBS = new Card(Rank.ACE, Suit.CLUBS);
    public static final Card ACE_OF_SPADES = new Card(Rank.ACE, Suit.SPADES);
    public static final Card[] CARD_LIST = new Card[]{TWO_OF_HEARTS, THREE_OF_HEARTS, FOUR_OF_HEARTS, FIVE_OF_HEARTS, SIX_OF_HEARTS, SEVEN_OF_HEARTS, EIGHT_OF_HEARTS, NINE_OF_HEARTS, TEN_OF_HEARTS, JACK_OF_HEARTS, QUEEN_OF_HEARTS, KING_OF_HEARTS, ACE_OF_HEARTS,
            TWO_OF_DIAMONDS, THREE_OF_DIAMONDS, FOUR_OF_DIAMONDS, FIVE_OF_DIAMONDS, SIX_OF_DIAMONDS, SEVEN_OF_DIAMONDS, EIGHT_OF_DIAMONDS, NINE_OF_DIAMONDS, TEN_OF_DIAMONDS, JACK_OF_DIAMONDS, QUEEN_OF_DIAMONDS, KING_OF_DIAMONDS, ACE_OF_DIAMONDS,
            TWO_OF_CLUBS, THREE_OF_CLUBS, FOUR_OF_CLUBS, FIVE_OF_CLUBS, SIX_OF_CLUBS, SEVEN_OF_CLUBS, EIGHT_OF_CLUBS, NINE_OF_CLUBS, TEN_OF_CLUBS, JACK_OF_CLUBS, QUEEN_OF_CLUBS, KING_OF_CLUBS, ACE_OF_CLUBS,
            TWO_OF_SPADES, THREE_OF_SPADES, FOUR_OF_SPADES, FIVE_OF_SPADES, SIX_OF_SPADES, SEVEN_OF_SPADES, EIGHT_OF_SPADES, NINE_OF_SPADES, TEN_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, KING_OF_SPADES, ACE_OF_SPADES};
    public final Rank rank;
    public final Suit suit;
    private Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Card card)){
            return false;
        }
        return card.rank.equals(this.rank) && card.suit.equals(this.suit);
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(rank.name);
        builder.append(" of ");
        builder.append(suit.name);
        return builder.toString();
    }
}
