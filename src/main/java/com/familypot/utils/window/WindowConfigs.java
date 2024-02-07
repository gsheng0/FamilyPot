package com.familypot.utils.window;

public class WindowConfigs {
    public static final int BOTTOM_ROW_Y_COORDINATE = 550;
    public static final int TOP_ROW_Y_COORDINATE = 250;
    public static final int UPPER_CORNER_Y_COORDINATE = 325;
    public static final int LOWER_CORNER_Y_COORDINATE = 475;
    public static final int LEFT_SIDE_X_COORDINATE = 175;
    public static final int MIDDLE_LEFT_X_COORDINATE = 400;
    public static final int MIDDLE_RIGHT_X_COORDINATE = 650;
    public static final int RIGHT_SIDE_X_COORDINATE = 875;
    public static final int[] SMALL_BLIND_COORDS = new int[]{LEFT_SIDE_X_COORDINATE, LOWER_CORNER_Y_COORDINATE};
    public static final int[] BIG_BLIND_COORDS = new int[]{MIDDLE_LEFT_X_COORDINATE, BOTTOM_ROW_Y_COORDINATE};
    public static final int[] UNDER_THE_GUN_COORDS = new int[]{MIDDLE_RIGHT_X_COORDINATE, BOTTOM_ROW_Y_COORDINATE};
    public static final int[] UNDER_THE_GUN_PLUS_ONE_COORDS = new int[]{RIGHT_SIDE_X_COORDINATE, LOWER_CORNER_Y_COORDINATE};
    public static final int[] LOJACK_COORDS = new int[]{RIGHT_SIDE_X_COORDINATE, UPPER_CORNER_Y_COORDINATE};
    public static final int[] HIJACK_COORDS = new int[]{MIDDLE_RIGHT_X_COORDINATE, TOP_ROW_Y_COORDINATE};
    public static final int[] CUTOFF_COORDS = new int[]{MIDDLE_LEFT_X_COORDINATE, TOP_ROW_Y_COORDINATE};
    public static final int[] BUTTON_COORDS = new int[]{LEFT_SIDE_X_COORDINATE, UPPER_CORNER_Y_COORDINATE};
    public static final int[][] PLAYER_COORDS = new int[][]{
            SMALL_BLIND_COORDS, BIG_BLIND_COORDS, UNDER_THE_GUN_COORDS,
            UNDER_THE_GUN_PLUS_ONE_COORDS, LOJACK_COORDS, HIJACK_COORDS,
            CUTOFF_COORDS, BUTTON_COORDS};
    public static final int FIRST_FLOP_CARD_X_COORDINATE = 200;
    public static final int BOARD_CARDS_Y_COORDINATE = 350;
    public static final int BOARD_CARDS_X_AXIS_SPACING = 110;
    public static final int[] FIRST_FLOP_CARD_COORDS = new int[]{FIRST_FLOP_CARD_X_COORDINATE, BOARD_CARDS_Y_COORDINATE};
    public static final int[] SECOND_FLOP_CARD_COORDS = new int[]{FIRST_FLOP_CARD_X_COORDINATE + BOARD_CARDS_X_AXIS_SPACING, BOARD_CARDS_Y_COORDINATE};
    public static final int[] THIRD_FLOP_CARD_COORDS = new int[]{FIRST_FLOP_CARD_X_COORDINATE + 2 * BOARD_CARDS_X_AXIS_SPACING, BOARD_CARDS_Y_COORDINATE};
    public static final int[] TURN_CARD_COORDS = new int[]{FIRST_FLOP_CARD_X_COORDINATE + 3 * BOARD_CARDS_X_AXIS_SPACING, BOARD_CARDS_Y_COORDINATE};

}
