package com.pokerapp;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {

    private final static int NUMBER_OF_CARDS = 5;
    private final static List<Character> ranks = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
    private final static List<Character> suits = Arrays.asList('S', 'H', 'D', 'C');
    private final String inputHand;
    private final String outputHand;

    public PokerHand(String inputHand) {
        this.inputHand = inputHand;
        this.outputHand = defineCombination();
    }

    // TODO check inputhand

    private List<Character> sortCardsByRanks(String stringHand) {
        // TODO check inputhand
        String[] cards = stringHand.split(" ");
        List<Character> sortedCardsByRank = new ArrayList<>();
        for (String card : cards) {
            for (int i = 0; i < ranks.size(); i++) {
                if (card.charAt(0) == ranks.get(i)) {
                    sortedCardsByRank.add((char) (77 - i));
                }
            }
        }
        Collections.sort(sortedCardsByRank);
        return sortedCardsByRank;
    }

    private List<Character> sortCardsBySuits(String stringHand) {
        // TODO check inputhand
        String[] cards = stringHand.split(" ");
        List<Character> sortedCardsBySuits = new ArrayList<>();
        for (String card : cards) {
            sortedCardsBySuits.add(card.charAt(1));
        }
        Collections.sort(sortedCardsBySuits);
        return sortedCardsBySuits;
    }

    private boolean isFlush() {
        List<Character> sortedCardsBySuits = sortCardsBySuits(inputHand);
        return sortedCardsBySuits.get(0) == sortedCardsBySuits.get(4);
    }

    private boolean isStraight() {
        if (isFlush()) return false;
        return checkStraightCards(inputHand);
    }

    private boolean isStraightFlush() {
        if (!isFlush() || (int) sortCardsByRanks(inputHand).get(NUMBER_OF_CARDS - 1) == 69) return false;
        return checkStraightCards(inputHand);
    }

    private boolean isRoyalFlush() {
        if (!isFlush() || (int) sortCardsByRanks(inputHand).get(NUMBER_OF_CARDS - 1) != 69) return false;
        return checkStraightCards(inputHand);
    }

    private boolean checkStraightCards(String stringHand) {
        for (int i = 0; i < NUMBER_OF_CARDS - 1; i++) {
            if ((int) sortCardsByRanks(stringHand).get(i) + 1 != (int) sortCardsByRanks(stringHand).get(i + 1))
                return false;
        }
        return true;
    }

    private List<Integer> countDuplicates() {
        List<Integer> duplicates = new ArrayList<>();
        sortCardsMap().entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .forEach(x -> duplicates.add(x.getValue()));
        return duplicates;
    }

    private Map<Character, Integer> sortCardsMap() {
        Map<Character, Integer> map = new HashMap<>();
        for (Character rank : sortCardsByRanks(inputHand)) {
            map.put(rank, map.containsKey(rank) ? map.get(rank) + 1 : 1);
        }
        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    private void appendKeys(StringBuilder string, Character symbol, List<Character> mapKeys) {
        string.append(symbol);
        for (Character c : mapKeys) {
            string.append(c);
        }
    }

    private String defineCombination() {
        List<Integer> duplicates = countDuplicates();
        List<Character> mapKeys = new ArrayList<>(sortCardsMap().keySet());
        StringBuilder stringHand = new StringBuilder();

        if (isRoyalFlush()) {
            stringHand.append('A');                                           // Royal flush
        } else if (isStraightFlush()) {                                       // Straight flush
            appendKeys(stringHand, 'B', mapKeys);
        } else if (duplicates.equals(Collections.singletonList(4))) {         // Four of a kind
            appendKeys(stringHand, 'C', mapKeys);
        } else if (duplicates.equals(Arrays.asList(3, 2))) {                  // Full house
            appendKeys(stringHand, 'D', mapKeys);
        } else if (isFlush()) {                                               // Flush
            appendKeys(stringHand, 'E', mapKeys);
        } else if (isStraight()) {                                            // Straight
            appendKeys(stringHand, 'F', mapKeys);
        } else if (duplicates.equals(Collections.singletonList(3))) {         // Three of a kind
            appendKeys(stringHand, 'G', mapKeys);
        } else if (duplicates.equals(Arrays.asList(2, 2))) {                  // Two pairs
            appendKeys(stringHand, 'H', mapKeys);
        } else if (duplicates.equals(Collections.singletonList(2))) {         // Pair
            appendKeys(stringHand, 'I', mapKeys);
        } else {                                                              // Highcard
            appendKeys(stringHand, 'J', mapKeys);
        }

        return stringHand.toString();
    }

    @Override
    public int compareTo(PokerHand p) {
        return outputHand.compareTo(p.outputHand);
    }

    @Override
    public String toString() {
        return inputHand + " (" + outputHand + ")";
    }

}



