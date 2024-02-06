package com.familypot.model;

import java.util.List;

public interface Decider {
    int act(List<Action> options);
}
