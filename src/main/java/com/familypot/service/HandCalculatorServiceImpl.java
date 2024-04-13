package com.familypot.service;

import com.familypot.model.cards.Card;
import com.familypot.model.cards.Rank;
import com.familypot.model.cards.Suit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.familypot.utils.Constants.*;

public class HandCalculatorServiceImpl implements HandCalculatorService {
    @SafeVarargs
    public final int calculateHandRepresentation(List<Card>... cardLists){
        HashMap<Suit, int[]> suitMap = new HashMap<>();
        int[] valueFrequencyArray = new int[15];
        for(List<Card> cardList : cardLists){
            for (Card card : cardList) {
                if (!suitMap.containsKey(card.suit)) {
                    suitMap.put(card.suit, new int[15]);
                }
                suitMap.get(card.suit)[card.rank.number]++;
                valueFrequencyArray[card.rank.number]++;
                if (card.rank.equals(Rank.ACE)) {
                    valueFrequencyArray[1]++;
                    suitMap.get(card.suit)[1]++;
                }
            }
        }
        int bestNonSequentialHand = calculateBestNonSequentialHand(valueFrequencyArray);
        int bestSequentialHand = calculateBestSequentialHand(valueFrequencyArray, suitMap);
        return Math.max(bestSequentialHand, bestNonSequentialHand);
    }

    public int calculateBestSequentialHand(int[] valueFrequencyArray, HashMap<Suit, int[]> suitMap) {
        for (Suit suit : suitMap.keySet()) {
            int[] suitedValueFrequencyArray = suitMap.get(suit);
            int suitCount = 0;
            int straightCount = 0;
            int straightTopCard = -1;
            for (int i = 0; i < suitedValueFrequencyArray.length; i++) {
                if (suitedValueFrequencyArray[i] > 0) {
                    if (i < 14) {
                        suitCount++;
                    }
                    straightCount++;
                    if (straightCount > 4) {
                        straightTopCard = i;
                    }
                } else {
                    straightCount = 0;
                }

            }
            if (straightTopCard != -1) {
                return calculateStraightFlushStrength(straightTopCard);
            }
            if (suitCount >= 5) {
                return calculateFlushStrength(suitedValueFrequencyArray);
            }
        }

        int straightCount = 0;
        int straightTopCard = -1;
        for (int i = 0; i < valueFrequencyArray.length; i++) {
            if (valueFrequencyArray[i] > 0) {
                straightCount++;
                if (straightCount > 4) {
                    straightTopCard = i;
                }
            } else {
                straightCount = 0;
            }

        }
        if (straightTopCard != -1) {
            return calculateStraightStrength(straightTopCard);
        }
        return Integer.MIN_VALUE;
    }

    public int calculateBestNonSequentialHand(int[] valueFrequencyArray) {
        int maxFrequency1 = -1;
        int maxFrequency2 = -1;
        int maxFrequencyValue1 = -1;
        int maxFrequencyValue2 = -1;
        for (int i = 2; i < valueFrequencyArray.length; i++) {
            if (maxFrequency1 <= valueFrequencyArray[i]) {
                maxFrequency1 = valueFrequencyArray[i];
                maxFrequencyValue1 = i;
            }
        }

        for (int i = 2; i < valueFrequencyArray.length; i++) {
            if (i == maxFrequencyValue1) {
                continue;
            }
            if (maxFrequency2 <= valueFrequencyArray[i]) {
                maxFrequency2 = valueFrequencyArray[i];
                maxFrequencyValue2 = i;
            }
        }
        if (maxFrequency1 == 1) {
            return calculateHighCardStrength(valueFrequencyArray);
        }
        if (maxFrequency1 == 2 && maxFrequency2 == 1) {
            return calculateOnePairStrength(maxFrequencyValue1, valueFrequencyArray);
        }
        if (maxFrequency1 == 2 && maxFrequency2 == 2) {
            return calculateTwoPairStrength(maxFrequencyValue1, maxFrequencyValue2, valueFrequencyArray);
        }
        if (maxFrequency1 == 3 && maxFrequency2 == 1) {
            return calculateThreeOfAKindStrength(maxFrequencyValue1, valueFrequencyArray);
        }
        if (maxFrequency1 == 3 && maxFrequency2 == 2) {
            return calculateFullHouseStrength(maxFrequencyValue1, maxFrequencyValue2);
        }
        if (maxFrequency1 == 4) {
            return calculateQuadStrength(maxFrequencyValue1, valueFrequencyArray);
        }
        return Integer.MIN_VALUE;
    }

    public int calculateHighCardStrength(int[] valueFrequencyArray) {
        return calculateHandTypeConstant(HIGH_CARD) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(valueFrequencyArray, 5));
    }

    public int calculateOnePairStrength(int pairValue, int[] valueFrequencyArray) {
        return calculateHandTypeConstant(ONE_PAIR) +
                (pairValue * (int) Math.pow(16, 4)) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(valueFrequencyArray, 3));
    }

    public int calculateTwoPairStrength(int pairValue1, int pairValue2, int[] valueFrequencyArray) {
        return calculateHandTypeConstant(TWO_PAIR) +
                (pairValue1 * (int) Math.pow(16, 4)) +
                (pairValue2 * (int) Math.pow(16, 3)) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(valueFrequencyArray, 1));
    }

    public int calculateThreeOfAKindStrength(int threeOfAKindValue, int[] valueFrequencyArray) {
        return calculateHandTypeConstant(THREE_OF_A_KIND) +
                (threeOfAKindValue * (int) Math.pow(16, 4)) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(valueFrequencyArray, 2));
    }

    public int calculateFullHouseStrength(int threeOfAKindValue, int pairValue) {
        return calculateHandTypeConstant(FULL_HOUSE) +
                (threeOfAKindValue * (int) Math.pow(16, 4)) +
                (pairValue * (int) Math.pow(16, 3));
    }

    public int calculateQuadStrength(int quadValue, int[] valueFrequencyArray) {
        return calculateHandTypeConstant(FOUR_OF_A_KIND) +
                (quadValue * (int) Math.pow(16, 4)) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(valueFrequencyArray, 1));
    }

    public int calculateStraightStrength(int straightTopCard) {
        return calculateHandTypeConstant(STRAIGHT) +
                (straightTopCard * (int) (Math.pow(16, 4)));
    }

    public int calculateFlushStrength(int[] suitedValueFrequencyArray) {
        return calculateHandTypeConstant(FLUSH) +
                calculateKickerStrength(getKickersFromValueFrequencyArray(suitedValueFrequencyArray, 5));
    }

    public int calculateStraightFlushStrength(int straightTopCard) {
        return calculateHandTypeConstant(STRAIGHT_FLUSH) +
                (straightTopCard * (int) (Math.pow(16, 4)));
    }

    public int[] getKickersFromValueFrequencyArray(int[] valueFrequencyArray, int numKickers) {
        int[] kickers = new int[numKickers];
        int index = 0;
        for (int i = valueFrequencyArray.length - 1; i >= 2; i--) {
            if (valueFrequencyArray[i] == 1 && index < numKickers) {
                kickers[index] = i;
                index++;
            }
        }
        return kickers;
    }

    public int calculateKickerStrength(int[] kickers) {
        Arrays.sort(kickers);
        int kickerStrength = 0;
        for (int i = 0; i < kickers.length; i++) {
            int multiplier = (int) (Math.pow(16, i));
            kickerStrength += multiplier * kickers[i];
        }
        return kickerStrength;
    }

    public int calculateHandTypeConstant(int handRank) {
        return (int) Math.pow(16, 5) * handRank;
    }
}
