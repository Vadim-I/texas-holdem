package com.pokerapp;

import com.pokerapp.exceptions.InvalidInputHandException;

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

    // sorting cards by ranks, replacing ranks with letter symbols
    private List<Character> sortCardsByRanks(String stringHand) {
        List<String> cards = new ArrayList<>();
        try {
            cards = checkAndSplitInputHand(stringHand);
        } catch (NullPointerException | InvalidInputHandException e) {
            System.out.println("Exception: invalid or null input hand");
        }
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

    // sorting cards by suits
    private List<Character> sortCardsBySuits(String stringHand) {
        List<String> cards = new ArrayList<>();
        try {
            cards = checkAndSplitInputHand(stringHand);
        } catch (NullPointerException | InvalidInputHandException e) {
            System.out.println("Exception: invalid or null input hand");
        }
        List<Character> sortedCardsBySuits = new ArrayList<>();
        cards.forEach(x -> sortedCardsBySuits.add(x.charAt(1)));
        Collections.sort(sortedCardsBySuits);
        return sortedCardsBySuits;
    }

    // checking string to validity, splitting into list
    private ArrayList<String> checkAndSplitInputHand(String stringHand) throws InvalidInputHandException {
        if (stringHand == null) throw new NullPointerException();
        if (stringHand.length() != 14) throw new InvalidInputHandException();
        for (int i = 0; i < stringHand.length(); i += 3) {
            if (!ranks.contains(stringHand.charAt(i))) throw new InvalidInputHandException();
        }
        for (int i = 1; i < stringHand.length(); i += 3) {
            if (!suits.contains(stringHand.charAt(i))) throw new InvalidInputHandException();
        }
        for (int i = 2; i < stringHand.length(); i += 3) {
            if (stringHand.charAt(i) != ' ') throw new InvalidInputHandException();
        }
        ArrayList<String> cards = new ArrayList<>(Arrays.asList(stringHand.split(" ")));
        Set<String> set = new HashSet<>(cards);
        if (set.size() != cards.size()) throw new InvalidInputHandException();
        return cards;
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

    // counting number of duplicate ranks
    private List<Integer> countDuplicates() {
        List<Integer> duplicates = new ArrayList<>();
        sortCardsMap().entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .forEach(x -> duplicates.add(x.getValue()));
        return duplicates;
    }

    // map (keys = ranks of cards, values = their number in the hand), reverse sorted by value
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

    private void appendCardRanks(StringBuilder string, Character symbol, List<Character> cardRanks) {
        string.append(symbol);
        for (Character c : cardRanks) {
            string.append(c);
        }
    }

    private String defineCombination() {
        List<Integer> duplicates = countDuplicates();
        List<Character> cardRanks = new ArrayList<>(sortCardsMap().keySet());
        StringBuilder stringHand = new StringBuilder();

        if (isRoyalFlush()) {
            stringHand.append('A');                                           // Royal flush
        } else if (isStraightFlush()) {                                       // Straight flush
            appendCardRanks(stringHand, 'B', cardRanks);
        } else if (duplicates.equals(Collections.singletonList(4))) {         // Four of a kind
            appendCardRanks(stringHand, 'C', cardRanks);
        } else if (duplicates.equals(Arrays.asList(3, 2))) {                  // Full house
            appendCardRanks(stringHand, 'D', cardRanks);
        } else if (isFlush()) {                                               // Flush
            appendCardRanks(stringHand, 'E', cardRanks);
        } else if (isStraight()) {                                            // Straight
            appendCardRanks(stringHand, 'F', cardRanks);
        } else if (duplicates.equals(Collections.singletonList(3))) {         // Three of a kind
            appendCardRanks(stringHand, 'G', cardRanks);
        } else if (duplicates.equals(Arrays.asList(2, 2))) {                  // Two pairs
            appendCardRanks(stringHand, 'H', cardRanks);
        } else if (duplicates.equals(Collections.singletonList(2))) {         // Pair
            appendCardRanks(stringHand, 'I', cardRanks);
        } else {                                                              // Highcard
            appendCardRanks(stringHand, 'J', cardRanks);
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

    public String getOutputHand() {
        return outputHand;
    }
}



