package com.familypot.model;

public class Action {
    public enum ActionType {
        BET, FOLD, RAISE, CHECK, CALL
    }
    private ActionType type;
    private int amount;
    public static Action fold(){
        return new Action(ActionType.FOLD);
    }
    public static Action call(int amount){
        return new Action(ActionType.CALL, amount);
    }
    public static Action raise(int amount){
        return new Action(ActionType.RAISE, amount);
    }
    public static Action check(){
        return new Action(ActionType.CHECK);
    }
    public static Action bet(int amount){
        return new Action(ActionType.BET, amount);
    }
    private Action(ActionType type) {
        this.type = type;
        this.amount = 0;
    }
    private Action(ActionType type, int amount) {
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
            return type + " " + amount;
        } else {
            return type.toString();
        }
    }
}

