package com.familypot.service;

import com.familypot.model.Player;
import com.familypot.model.cards.Card;
import com.familypot.model.cards.Deck;
import com.familypot.utils.CardUtils;
import com.familypot.model.Action;
import com.familypot.utils.Constants;
import com.familypot.utils.FileWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TexasHoldemRunnerService implements PokerRunnerService {
    HandCalculatorService handCalculatorService = new HandCalculatorServiceImpl();

    @Override
    public void runHand(List<Player> players, int littleBlindSize, int bigBlindSize) {
        FileWriter fileWriter = FileWriter.getInstance();
        fileWriter.randomizeFilename();
        int pot = players.get(0).bet(littleBlindSize) + players.get(1).bet(bigBlindSize);
        Deck deck = new Deck();
        List<Card> board = new ArrayList<>();
        deck.shuffle();
        List<List<Card>> holeCards = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            holeCards.add(new ArrayList<>());
            holeCards.get(i).add(deck.deal());
            List<Card> cards = holeCards.get(i);
            fileWriter.writeln("[" + players.get(i).getName() + "]: \t" + cards.get(0) + ", " + cards.get(1));
        }
        fileWriter.writeln("");

        List<Player> leftToAct = new ArrayList<>(players);
        int raiseAmount = bigBlindSize;
        List<Action> actionList = new ArrayList<>();
        List<Player> callers = new ArrayList<>();
        for (int i = 0; i < leftToAct.size(); i++) {
            Player player = leftToAct.get(i);
            List<Action> options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player, 2 * raiseAmount)));
            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if (action.isCall()) {
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if (action.isRaise()) {
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if (action.isFold()) {
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }
        fileWriter.writeln("");

        for (int i = 0; i < 3; i++) {
            Card card = deck.deal();
            board.add(card);
            fileWriter.writeln("\t" + card);
        }
        fileWriter.writeln("");

        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for (int i = 0; i < leftToAct.size(); i++) {
            Player player = leftToAct.get(i);
            List<Action> options;
            if (raiseAmount == 0) {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.check(player), Action.bet(player, pot / 2)));
            } else {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player, 2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if (action.isCall() || action.isCheck()) {
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if (action.isRaise() || action.isBet()) {
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if (action.isFold()) {
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }

        Card card = deck.deal();
        board.add(card);

        fileWriter.writeln("");
        fileWriter.writeln("\t" + card);
        fileWriter.writeln("");

        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for (int i = 0; i < leftToAct.size(); i++) {
            Player player = leftToAct.get(i);
            List<Action> options;
            if (raiseAmount == 0) {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.check(player), Action.bet(player, pot / 2)));
            } else {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player, 2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if (action.isCall() || action.isCheck()) {
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if (action.isRaise() || action.isBet()) {
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if (action.isFold()) {
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }

        card = deck.deal();
        board.add(card);
        fileWriter.writeln("");
        fileWriter.writeln("\t" + card);
        fileWriter.writeln("");

        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for (int i = 0; i < leftToAct.size(); i++) {
            Player player = leftToAct.get(i);
            List<Action> options;
            if (raiseAmount == 0) {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.check(player), Action.bet(player, pot / 2)));
            } else {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player, 2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if (action.isCall() || action.isCheck()) {
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if (action.isRaise() || action.isBet()) {
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if (action.isFold()) {
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }

        card = deck.deal();
        fileWriter.writeln("");
        fileWriter.writeln("\t" + card);
        fileWriter.writeln("");

        leftToAct = new ArrayList<>(players);
        callers = new ArrayList<>();
        raiseAmount = 0;
        for (int i = 0; i < leftToAct.size(); i++) {
            Player player = leftToAct.get(i);
            List<Action> options;
            if (raiseAmount == 0) {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.check(player), Action.bet(player, pot / 2)));
            } else {
                options = new ArrayList<>(Arrays.asList(Action.fold(player), Action.call(player, raiseAmount), Action.raise(player, 2 * raiseAmount)));
            }

            Action action = options.get(player.getDecider().act(options));
            actionList.add(action);

            if (action.isCall() || action.isCheck()) {
                pot += player.bet(raiseAmount);
                callers.add(player);
            } else if (action.isRaise() || action.isBet()) {
                pot += player.bet(action.getAmount());
                raiseAmount = action.getAmount();
                leftToAct.addAll(callers);
                callers = new ArrayList<>(List.of(player));
            } else if (action.isFold()) {
                players.remove(player);
            }
            fileWriter.writeln(action.toString());
        }

        fileWriter.writeln("END");

        int[] handValues = new int[holeCards.size()];
        for (int i = 0; i < players.size(); i++) {
            List<Card> holeCard = holeCards.get(i);
            List<Card> hand = new ArrayList<>(board);
            hand.add(holeCard.get(0));
            hand.add(holeCard.get(1));
            handValues[i] = handCalculatorService.calculateHandRepresentation(hand);
        }

        int max = -1;
        int index = -1;
        for (int i = 0; i < handValues.length; i++) {
            if (handValues[i] > max) {
                index = i;
                max = handValues[i];
            }
        }
        fileWriter.writeln("Player " + index + " has won with " + CardUtils.classifyHand(max));
    }
}
