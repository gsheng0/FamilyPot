package com.familypot;

import com.familypot.model.Action;
import com.familypot.model.Decider;
import com.familypot.model.Merritt;
import com.familypot.model.Player;
import com.familypot.model.cards.Card;
import com.familypot.service.HandCalculatorService;
import com.familypot.service.HandCalculatorServiceImpl;
import com.familypot.service.TexasHoldemRunnerService;
import com.familypot.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            players.add(new Player("Player " + i, 1000, new Decider() {
                @Override
                public int act(List<Action> options) {
                    if((int)(Math.random() * 10) > 1){
                        return 1;
                    }
                    if((int)(Math.random() * 10) > 5){
                        return 0;
                    }
                    return 2;
                }
            }));
        }
        players.add(new Player("Merritt", 10000000, new Merritt()));
        TexasHoldemRunnerService runnerService = new TexasHoldemRunnerService();
        runnerService.runHand(players, 1, 2);
    }
}