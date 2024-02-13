package com.familypot.utils.window.event;

import com.familypot.model.Action;
import com.familypot.model.Player;
import com.familypot.model.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableState {
    private List<Card> boardCards;
    private List<Player> players;
    private List<Card[]> holeCards;
    private List<Integer> foldedPlayers;
    private HashMap<String, Integer> playerNameToIndexMap;
    private List<String> actionNames;
    private int pot = 0;

    public TableState(){
        boardCards = new ArrayList<>();
        players = new ArrayList<>();
        holeCards = new ArrayList<>();
        playerNameToIndexMap = new HashMap<>();
        foldedPlayers = new ArrayList<>();
        actionNames = new ArrayList<>();
    }
    public void addPlayer(String playerName, Card[] cards){
        players.add(new Player(playerName, 1000, options -> 0));
        holeCards.add(cards);
        playerNameToIndexMap.put(playerName, players.size() - 1);
        if(actionNames.size() == 0){
            actionNames.add("Blind: 1");
            pot += 1;
        } else if(actionNames.size() == 1){
            actionNames.add("Blind: 2");
            pot += 2;
        } else{
            actionNames.add("");
        }
    }
    public void apply(Event event){
        if(event instanceof CardEvent cardEvent){
            boardCards.add(cardEvent.getCard());
            actionNames = new ArrayList<>();
            for(int i = 0; i < players.size(); i++){
                actionNames.add("");
            }
        } else if(event instanceof PlayerEvent playerEvent){
            Action playerAction = playerEvent.getAction();
            int playerIndex = playerNameToIndexMap.get(playerEvent.getActor());
            pot += playerAction.getAmount();
            if(playerAction.isBet()){
                actionNames.set(playerIndex, "Bet " + playerAction.getAmount());
            } else if(playerAction.isRaise()){
                actionNames.set(playerIndex, "Raise " + playerAction.getAmount());
            } else if(playerAction.isCall()){
                actionNames.set(playerIndex, "Call " + playerAction.getAmount());
            } else if(playerAction.isFold()){
                actionNames.set(playerIndex, "Fold");
                foldedPlayers.add(playerNameToIndexMap.get(playerEvent.getActor()));
            } else if(playerAction.isCheck()){
                actionNames.set(playerIndex, "Check");
            }
        }
    }
    public List<Card> getBoardCards() { return boardCards; }
    public List<Player> getPlayers() { return players; }
    public HashMap<String, Integer> getPlayerNameToIndexMap() { return playerNameToIndexMap; }
    public int getPot(){ return pot; }
    public List<Integer> getFoldedPlayers(){ return foldedPlayers; }
    public List<String> getActions() { return actionNames; }
    public List<Card[]> getHoleCards() { return holeCards; }
}
