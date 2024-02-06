package com.familypot.model;

public class Action {
    public enum ActionType {
        BET, FOLD, RAISE, CHECK, CALL
    }
    private ActionType type;
    private int amount;
    private Player player;
    public static Action fold(Player player){
        return new Action(player, ActionType.FOLD);
    }
    public static Action call(Player player, int amount){
        return new Action(player, ActionType.CALL, amount);
    }
    public static Action raise(Player player, int amount){
        return new Action(player, ActionType.RAISE, amount);
    }
    public static Action check(Player player){
        return new Action(player, ActionType.CHECK);
    }
    public static Action bet(Player player, int amount){
        return new Action(player, ActionType.BET, amount);
    }
    private Action(Player player, ActionType type) {
        this.player = player;
        this.type = type;
        this.amount = 0;
    }
    private Action(Player player, ActionType type, int amount) {
        this.player = player;
        this.type = type;
        this.amount = amount;
    }
    public ActionType getType() {
        return type;
    }
    public int getAmount() {
        return amount;
    }
    public boolean isFold() {
        return type == ActionType.FOLD;
    }
    public boolean isCall() {
        return type == ActionType.CALL;
    }
    public boolean isRaise() {
        return type == ActionType.RAISE;
    }
    public boolean isCheck() {
        return type == ActionType.CHECK;
    }
    public boolean isBet() {
        return type == ActionType.BET;
    }
    @Override
    public String toString() {
        if (type == ActionType.BET || type == ActionType.RAISE || type == ActionType.CALL) {
            return "[" + player.getName() + "]: " + type + " " + amount;
        } else {
            return "[" + player.getName() + "]: " + type.toString();
        }
    }
}

