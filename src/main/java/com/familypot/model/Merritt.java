package com.familypot.model;

import java.util.List;

public class Merritt implements Decider{
    @Override
    public int act(List<Action> options) {
        return options.size() - 1;
    }
}
