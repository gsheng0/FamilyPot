package com.familypot.utils.window.event;

import com.familypot.model.Action;
public class PlayerEvent extends Event{
    private String player;
    private Action action;
    public PlayerEvent(Action action){
        super(action.getPlayer().getName());
        this.action = action;
    }
}
