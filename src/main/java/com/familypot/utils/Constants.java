package com.familypot.utils;

public class Constants {
    public static final int PRE_FLOP_ACTION = 0;
    public static final int FLOP_ACTION = 1;
    public static final int TURN_ACTION = 2;
    public static final int RIVER_ACTION = 3;
    public static final int HIGH_CARD = 0;
    public static final int ONE_PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;
    public static final String[] HAND_RANKINGS = new String[]{
            "High Card", "One Pair", "Two Pair", "Three of a Kind",
            "Straight", "Flush", "Full House", "Four of a Kind",
            "Straight Flush"};
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String BASE_DIRECTORY = "/Users/gsheng/Downloads/";
}
