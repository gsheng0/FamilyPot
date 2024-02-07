package com.familypot.model.cards;

public class Rank implements Comparable<Rank>{
    public static final Rank TWO = new Rank("2", "Two", 2);
    public static final Rank THREE = new Rank("3", "Three", 3);
    public static final Rank FOUR = new Rank("4", "Four", 4);
    public static final Rank FIVE = new Rank("5", "Five", 5);
    public static final Rank SIX = new Rank("6", "Six", 6);
    public static final Rank SEVEN = new Rank("7", "Seven", 7);
    public static final Rank EIGHT = new Rank("8", "Eight", 8);
    public static final Rank NINE = new Rank("9", "Nine", 9);
    public static final Rank TEN = new Rank("10", "Ten", 10);
    public static final Rank JACK = new Rank("J", "Jack", 11);
    public static final Rank QUEEN = new Rank("Q", "Queen", 12);
    public static final Rank KING = new Rank("K", "King", 13);
    public static final Rank ACE = new Rank("A", "Ace", 14);
    public static final Rank[] RANKS = new Rank[]{TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};
    public final String symbol;
    public final String name;
    public final int number;
    private Rank(String symbol, String name, int number) {
        this.symbol = symbol;
        this.name = name;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Rank other)){
            return false;
        }
        return other.number == this.number;
    }
    public boolean greaterThan(Rank other){
        return this.number > other.number;
    }
    public boolean lessThan(Rank other){
        return this.number < other.number;
    }
    @Override
    public int compareTo(Rank o) {
        return Integer.compare(this.number, o.number);
    }
    public static Rank parseRank(String rankName) {
        switch (rankName) {
            case "Ace":
                return Rank.ACE;
            case "Two":
                return Rank.TWO;
            case "Three":
                return Rank.THREE;
            case "Four":
                return Rank.FOUR;
            case "Five":
                return Rank.FIVE;
            case "Six":
                return Rank.SIX;
            case "Seven":
                return Rank.SEVEN;
            case "Eight":
                return Rank.EIGHT;
            case "Nine":
                return Rank.NINE;
            case "Ten":
                return Rank.TEN;
            case "Jack":
                return Rank.JACK;
            case "Queen":
                return Rank.QUEEN;
            case "King":
                return Rank.KING;
            default:
                throw new IllegalArgumentException("Invalid rank name: " + rankName);
        }
    }
}
