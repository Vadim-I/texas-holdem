package com.pokerapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PokerHandTest {

    @Test
    public void testRoyalFlush() {
        PokerHand hand = new PokerHand("KD TD AD JD QD");
        Assertions.assertEquals('A', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testStraightFlush() {
        PokerHand hand = new PokerHand("6H 7H 8H TH 9H");
        Assertions.assertEquals('B', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testFourOfAKind() {
        PokerHand hand = new PokerHand("JH JS JD JC QH");
        Assertions.assertEquals('C', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testFullHouse() {
        PokerHand hand = new PokerHand("JH JS QD QC QH");
        Assertions.assertEquals('D', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testFlush() {
        PokerHand hand = new PokerHand("KH 5H QH 2H AH");
        Assertions.assertEquals('E', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testStraight() {
        PokerHand hand = new PokerHand("3S 4H 5D 7D 6C");
        Assertions.assertEquals('F', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testThreeOfAKind() {
        PokerHand hand = new PokerHand("3H AH AS AD 6S");
        Assertions.assertEquals('G', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testTwoPairs() {
        PokerHand hand = new PokerHand("2C AH QS QD AS");
        Assertions.assertEquals('H', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testPair() {
        PokerHand hand = new PokerHand("7D KH QS 8D KS");
        Assertions.assertEquals('I', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testHighcard() {
        PokerHand hand = new PokerHand("3H QH JC TD 7C");
        Assertions.assertEquals('J', hand.getOutputHand().charAt(0));
    }

    @Test
    public void testCompareDiffCombinations() {
        PokerHand hand1 = new PokerHand("6H 7H 8H TH 9H");
        PokerHand hand2 = new PokerHand("KH 5H QH 2H AH");
        PokerHand hand3 = new PokerHand("7D KH QS 8D KS");
        PokerHand hand4 = new PokerHand("3H AH AS AD 6S");
        PokerHand hand5 = new PokerHand("JH JS JD JC QH");
        PokerHand hand6 = new PokerHand("3H QH JC TD 7C");
        PokerHand hand7 = new PokerHand("KD TD AD JD QD");
        PokerHand hand8 = new PokerHand("3S 4H 5D 7D 6C");
        PokerHand hand9 = new PokerHand("JH JS QD QC QH");
        PokerHand hand10 = new PokerHand("2C AH QS QD AS");
        ArrayList<PokerHand> hands = new ArrayList<>(Arrays.asList(hand1, hand2, hand3, hand4, hand5,
                hand6, hand7, hand8, hand9, hand10));
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    // Royal Flushes have the same strength
    @Test
    public void testCompareRoyalFlushes() {
        PokerHand hand1 = new PokerHand("KD TD AD JD QD");
        PokerHand hand2 = new PokerHand("KC TC AC JC QC");
        Assertions.assertEquals(hand1.getOutputHand().charAt(0), hand2.getOutputHand().charAt(0));
    }

    @Test
    public void testCompareStraightFlushes() {
        PokerHand hand1 = new PokerHand("6H 7H 8H TH 9H");
        PokerHand hand2 = new PokerHand("KC QC JC TC 9C");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareFourOfAKind() {
        PokerHand hand1 = new PokerHand("JH JS JD JC QH");
        PokerHand hand2 = new PokerHand("TH 9S 9D 9C 9H");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareFullHouses() {
        PokerHand hand1 = new PokerHand("JH JS JD AC AH");
        PokerHand hand2 = new PokerHand("TH AS AD TC TS");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareFlushes() {
        PokerHand hand1 = new PokerHand("KH 5H TH 2H AH");
        PokerHand hand2 = new PokerHand("AD KD 2D JD QD");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareStraights() {
        PokerHand hand1 = new PokerHand("JD TH 8D 7S 9C");
        PokerHand hand2 = new PokerHand("3S 4H 5D 7D 6C");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareThreeOfAKind() {
        PokerHand hand1 = new PokerHand("3H AH AS AD 6S");
        PokerHand hand2 = new PokerHand("8H QH 9S QD QS");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareTwoPairs() {
        PokerHand hand1 = new PokerHand("2C AH QS QD AS");
        PokerHand hand2 = new PokerHand("9S 9D TS TH 8C");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testComparePairs() {
        PokerHand hand1 = new PokerHand("7D KH QS 8D KS");
        PokerHand hand2 = new PokerHand("6C TS TH 5H AD");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }

    @Test
    public void testCompareHighcards() {
        PokerHand hand1 = new PokerHand("3H QH JC TD 7C");
        PokerHand hand2 = new PokerHand("7S 9D AS JD 2C");
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        Collections.sort(hands);
        hands.forEach(System.out::println);
    }
}