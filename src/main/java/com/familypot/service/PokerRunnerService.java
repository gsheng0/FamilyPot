package com.familypot.service;

import com.familypot.model.Player;
import java.util.List;

public interface PokerRunnerService {
    void runHand(List<Player> players, int smallBlindSize, int bigBlindSize);
}
