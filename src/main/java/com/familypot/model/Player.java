package com.familypot.model;

public class Player {
    private int stackSize;
    private String name;
    private Decider decider;
    public Player(String name, int stackSize, Decider decider){
        this.name = name;
        this.stackSize = stackSize;
        this.decider = decider;
    }
    public Decider getDecider(){
        return decider;
    }
    public int bet(int betAmount){
        if(betAmount > stackSize){
            stackSize = 0;
            return stackSize;
        }
        stackSize -= betAmount;
        return betAmount;
    }
    public int getStackSize(){ return stackSize; }
    public String getName() { return name; }
}
