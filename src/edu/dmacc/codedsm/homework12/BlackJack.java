package edu.dmacc.codedsm.homework12;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

public class BlackJack<dealerCards> {

    private static String dealerInput;
    private static PrintWriter printWriter;

    //public BlackJack() {
    // pw = new PrintWriter(file);


    public static void main(String[] args) {

        Map<String, List<Integer>> deck = initializeDeck();
        List<Card> playerHand = new ArrayList<>();
        List<Card> dealerHand = new ArrayList<>();
        int playerSum = 0;
        int dealerSum = 0;

        List<Card> playerCards = DeckRandomizer.chooseRandomCards(deck, 2);
        playerHand.addAll(playerCards);
        ;
        for ( Card card : playerCards ) {
            int cardVal = card.value;
            if (card.value > 10) ;
            //  {
            //    cardVal = 10;
            //  }
            playerSum = playerSum + cardVal;

            removeCardFromDeck(deck, card);
        }

        System.out.println("Player's hand is: ");
        showHand(playerHand);


        List<Card> dealerCards = DeckRandomizer.chooseRandomCards(deck, 2);
        dealerHand.addAll(dealerCards);
        for ( Card card : dealerCards ) {
            int cardVal = card.value;
            if (card.value > 10) {
                cardVal = 10;
            }
            dealerSum = dealerSum + cardVal;

            removeCardFromDeck(deck, card);
        }
        List<Card> showOneCardList = new ArrayList<>();
        showOneCardList.add(dealerCards.get(0));
        System.out.println("Dealer's hand is: ");
        showHand(showOneCardList);

        boolean continueDealerGame = true;
        boolean continueGame = true;
        Scanner in = new Scanner(System.in);
        while (continueGame) {
            System.out.printf("Player\'s Hand is %d points.\n", playerSum);

            if (playerSum > 21) {
                System.out.println("BUST");
                continueGame = false;
                continueDealerGame = false;
            }
            if (continueGame == false) break;

            System.out.println("Player enter 1 to Hit, 2 to Stand");
            String input = in.next();
            if (input.equals("1")) {
                List<Card> nextPlayerCard = DeckRandomizer.chooseRandomCards(deck, 1);

                int cardVal = nextPlayerCard.get(0).value;
                if (nextPlayerCard.get(0).value > 10) {
                    cardVal = 10;
                }
                playerSum = playerSum + cardVal;

                playerHand.addAll(nextPlayerCard);
                removeCardFromDeck(deck, nextPlayerCard.get(0));

            } else if (input.equals("2")) {
                continueGame = false;

            } else {
                showErrorMessage();
            }

            System.out.println("Player's hand is: ");
            showHand(playerHand);
        }

        while (continueDealerGame) {
            System.out.printf("Dealer/s Hand is %d points.\n", dealerSum);

            if (dealerSum < 17) {

                List<Card> nextDealerCard = DeckRandomizer.chooseRandomCards(deck, 1);

                int cardVal = nextDealerCard.get(0).value;
                if (nextDealerCard.get(0).value > 10) {
                    cardVal = 10;
                }
                dealerSum = dealerSum + cardVal;

                dealerHand.addAll(nextDealerCard);
                removeCardFromDeck(deck, nextDealerCard.get(0));

            } else if (dealerSum == 21) {
                System.out.println("Dealer wins blackjack");
                System.exit(0);
            } else {
                continueDealerGame = false;
            }
            System.out.println("Dealer's hand is: ");
        }

        boolean isPlayerTheWinner = false;
        boolean isTie = false;
        if (dealerSum == playerSum) {
            System.out.println("It's a tie");
            isTie = true;
        } else if (dealerSum > 21) {
            System.out.println("Player wins");
        } else if (playerSum > 21) {
            System.out.println("Dealer wins");
            isPlayerTheWinner = true;
        } else if (dealerSum > playerSum) {
            System.out.println("Dealer wins");
        } else {
            System.out.println("Player wins");
            isPlayerTheWinner = true;
        }

        try {
            writeToFIle(playerHand, playerSum, dealerHand, dealerSum, isTie, isPlayerTheWinner);
        } catch (Exception ex) {
            System.out.println("You have an error");

        }
    }

    private static void writeToFIle(List<Card> playerHand, int playerSum, List<Card> dealerHand, int dealerSum, boolean isTie, boolean isPlayerTheWinner) {

    }


    private static Map<String, List<Integer>> initializeDeck() {
        Map<String, List<Integer>> deck = new HashMap<>();
        deck.put("Clubs", createCards());
        deck.put("Diamonds", createCards());
        deck.put("Spades", createCards());
        deck.put("Hearts", createCards());

        return deck;
    }

    private static List<Integer> createCards() {
        List<Integer> cards = new ArrayList<>();

        for ( int i = 1; i < 14; i++ ) {
            cards.add(i);
        }
        return cards;
    }

    private static void removeCardFromDeck(Map<String, List<Integer>> deck, Card card) {
        List<Integer> cardsInSuit = deck.get(card.suit);
        cardsInSuit.remove(card.value);

    }

    private static void showHand(List<Card> playerHand) {
        for ( Card card : playerHand ) {
            System.out.printf("%s - %d, ", card.suit, card.value);
        }
        System.out.println("\n");

    }

    private static void showErrorMessage() {
        System.out.println("Invalid input");

    }

    public static void writeToFile(List<Card> playerCards, List<Card> dealerCards, File inputFile, File fileWriter, RandomAccessFile printwriter)
            throws IOException {
        FileWriter fileWriter1 = new FileWriter("blackjack_log.txt");
        Scanner in = new Scanner(inputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
                
        printWriter.printf("%n%s", "Player cards:");
        for ( Card card : playerCards ) {
            printWriter.printf(" %s - %d, ", card.suit, card.value);
        }

        printWriter.printf("%n%s", "Dealer cards:");
        for ( Card card : dealerCards ) {
            printWriter.printf(" %s - %d, ", card.suit, card.value);
        }

        System.out.println();
        System.out.printf("\nGAME OVER");
        printwriter.close();

    }

}












