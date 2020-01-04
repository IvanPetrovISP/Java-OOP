package cardRanks;

import enums.CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Ranks:");
        for (CardRank rank : CardRank.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    rank.ordinal(), rank.toString());
        }
    }
}
