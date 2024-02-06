package com.familypot;

import com.familypot.model.Action;
import com.familypot.model.Player;
import com.familypot.service.TexasHoldemRunnerService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            players.add(new Player("Player " + i, 1000, options -> (int)(Math.random() * options.size())));
        }
        TexasHoldemRunnerService runnerService = new TexasHoldemRunnerService();
        runnerService.runHand(players, 1, 2);
    }
}