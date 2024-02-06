package com.familypot.service;

import com.familypot.model.Player;
import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;
import com.familypot.utils.CardUtils;
import com.familypot.model.Action;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TexasHoldemRunnerService implements PokerRunnerService{
    @Override
    public void runHand(List<Player> players, int littleBlindSize, int bigBlindSize) {
        int pot = players.get(0).bet(littleBlindSize) + players.get(1).bet(bigBlindSize);
        Deck deck = new Deck();
        deck.shuffle();
        List<Card[]> holeCards = CardUtils.dealHoleCards(players.size(), 2, deck);
        Card[] board = new Card[5];


        List<Player> leftToAct = new ArrayList<>();
        for(int i = 2; i < players.size(); i++){
            leftToAct.add(players.get(i));
        }
        leftToAct.add(players.get(0));
        leftToAct.add(players.get(1));

        int raiseAmount = bigBlindSize;
        List<Action> actionList = new ArrayList<>();
        List<Player> callers = new ArrayList<>();
        for(int i = 0; i < leftToAct.size(); i++){
            Player player = leftToAct.get(i);
            List<Action> options = new ArrayList<>(Arrays.asList(Action.fold(), Action.call(raiseAmount), Action.raise(2 * raiseAmount)));
            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if(action.isCall()){
                pot += player.bet(raiseAmount);
                callers.add(player);
            }
            else if(action.isRaise()){
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            }
        }

        deck.deal();
        for(int i = 0; i < 3; i++){
            board[i] = deck.deal();
        }

        leftToAct = callers;
        callers = new ArrayList<>();

        for(int i = 0; i < leftToAct.size(); i++){

        }
        System.out.println(actionList);
    }
}
