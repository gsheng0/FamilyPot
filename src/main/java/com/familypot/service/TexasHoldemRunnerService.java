package com.familypot.service;

import com.familypot.model.Player;
import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;
import com.familypot.utils.CardUtils;
import com.familypot.model.Action;
import com.familypot.utils.Constants;
import com.familypot.utils.FileWriter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TexasHoldemRunnerService implements PokerRunnerService{
    HandCalculatorService handCalculatorService = new HandCalculatorServiceImpl();
    @Override
    public void runHand(List<Player> players, int littleBlindSize, int bigBlindSize) {
        FileWriter fileWriter = FileWriter.getInstance();
        fileWriter.randomizeFilename();
        int pot = players.get(0).bet(littleBlindSize) + players.get(1).bet(bigBlindSize);
        Deck deck = new Deck();
        deck.shuffle();
        List<Card[]> holeCards = CardUtils.dealHoleCards(players.size(), 2, deck);
        Card[] board = new Card[5];

        for(int i = 0; i < holeCards.size(); i++) {
            Card[] cards = holeCards.get(i);
            fileWriter.writeln("[" + players.get(i).getName() + "]: \t" + cards[0] + ", " + cards[1]);
        }
        fileWriter.writeln("");


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
            List<Action> options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player,2 * raiseAmount)));
            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if(action.isCall()){
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if(action.isRaise()){
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if(action.isFold()){
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }
        fileWriter.writeln("");
        deck.deal();
        for(int i = 0; i < 3; i++){
            board[i] = deck.deal();
            fileWriter.writeln("\t" + board[i]);
        }
        fileWriter.writeln("");

        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for(int i = 0; i < leftToAct.size(); i++){
            Player player = leftToAct.get(i);
            List<Action> options;
            if(raiseAmount == 0){
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.check(player),
                        Action.bet(player, pot/2)));
            } else{
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.call(player, raiseAmount),
                        Action.raise(player,2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if(action.isCall() || action.isCheck()){
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if(action.isRaise() || action.isBet()){
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if(action.isFold()){
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }
        deck.deal();
        fileWriter.writeln("");
        board[3] = deck.deal();
        fileWriter.writeln("\t" + board[3]);
        fileWriter.writeln("");
        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for(int i = 0; i < leftToAct.size(); i++){
            Player player = leftToAct.get(i);
            List<Action> options;
            if(raiseAmount == 0){
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.check(player),
                        Action.bet(player, pot/2)));
            } else{
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.call(player, raiseAmount),
                        Action.raise(player,2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if(action.isCall() || action.isCheck()){
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if(action.isRaise() || action.isBet()){
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if(action.isFold()){
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }

        deck.deal();
        fileWriter.writeln("");
        board[4] = deck.deal();
        fileWriter.writeln("\t" + board[4]);
        fileWriter.writeln("");
        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for(int i = 0; i < leftToAct.size(); i++){
            Player player = leftToAct.get(i);
            List<Action> options;
            if(raiseAmount == 0){
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.check(player),
                        Action.bet(player, pot/2)));
            } else{
                options = new ArrayList<>(Arrays.asList(
                        Action.fold(player),
                        Action.call(player, raiseAmount),
                        Action.raise(player,2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if(action.isCall() || action.isCheck()){
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if(action.isRaise() || action.isBet()){
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if(action.isFold()){
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }
        fileWriter.writeln("END");
        int[] handValues = new int[holeCards.size()];
        for(int i = 0; i < board.length; i++){
            fileWriter.writeln("\t" + board[i].toString());
        }
        fileWriter.writeln("");
        for(int i = 0; i < holeCards.size(); i++){
            Card[] cards = holeCards.get(i);
            Card[] hand = new Card[7];
            for(int x = 0; x < board.length; x++){
                hand[x] = board[x];
            }
            hand[5] = cards[0];
            hand[6] = cards[1];
            handValues[i] = handCalculatorService.calculateHandRepresentation(hand);
        }
        int max = -1;
        int index = -1;
        for(int i = 0; i < handValues.length; i++){
            if(handValues[i] > max){
                index = i;
                max = handValues[i];
            }
        }
        fileWriter.writeln("Player " + index + " has won with " + CardUtils.classifyHand(max));
    }
}
