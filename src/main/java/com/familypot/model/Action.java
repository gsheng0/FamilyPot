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
    public Player getPlayer() { return player; }
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
    public static Action parseAction(String action){
        String playerName = action.substring(action.indexOf("["), action.indexOf("]"));
        String[] parts = action.substring(action.indexOf(":") + 2).split(" " );
        Player player = new Player(playerName, 1000, options -> 0);
        if(parts[0].equals("CHECK")){
            return Action.check(player);
        } else if(parts[0].equals("FOLD")){
            return Action.fold(player);
        } else if(parts[0].equals("BET")){
            return Action.bet(player, Integer.parseInt(parts[1]));
        } else if(parts[0].equals("CALL")){
            return Action.call(player, Integer.parseInt(parts[1]));
        } else if(parts[0].equals("RAISE")){
            return Action.raise(player, Integer.parseInt(parts[1]));
        }
        throw new RuntimeException("Invalid action format: " + action);
    }
}

