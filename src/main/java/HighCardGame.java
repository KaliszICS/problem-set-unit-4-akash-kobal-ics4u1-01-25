import java.util.Scanner;

/**
 * The HighCardGame class runs a simple "High Card" game between two players.
 * Each player is created with a name and draws 5 cards from a shuffled deck.
 * For each of the 5 rounds, both players play their highest-value card.
 * The player with the higher-valued card earns a point.
 * After all rounds are complete, the player with the most points wins.
 * 
 * Handles setup, input, gameplay flow, scoring, and output.
 * 
 * @author Akash K.
 * @version 1.0
 */

public class HighCardGame {

    /**
     * Main method that sets up the game, gets player names,
     * deals cards, runs the rounds, and prints the results.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Player 1 Name: ");
        String player1 = input.nextLine();
        System.out.print("Player 1 Age: ");
        int age1 = input.nextInt();
        input.nextLine(); 

        System.out.print("Player 2 Name: ");
        String player2 = input.nextLine();
        System.out.print("Player 2 Age: ");
        int age2 = input.nextInt();
        input.nextLine(); 

        Player p1 = new Player(player1, age1);
        Player p2 = new Player(player2, age2);

        Deck deck = new Deck();
        deck.shuffle();

        // Deal 5 cards to each player
        for (int i = 0; i < 5; i++) {
            p1.draw(deck);
            p2.draw(deck);
        }
        int p1Points = 0;
        int p2Points = 0;
        System.out.println("");

        // Title Art
        System.out.println(
                "$$\\   $$\\ $$\\           $$\\              $$$$$$\\                            $$\\        $$$$$$\\                                    \r\n"
                        + //
                        "$$ |  $$ |\\__|          $$ |            $$  __$$\\                           $$ |      $$  __$$\\                                   \r\n"
                        + //
                        "$$ |  $$ |$$\\  $$$$$$\\  $$$$$$$\\        $$ /  \\__| $$$$$$\\   $$$$$$\\   $$$$$$$ |      $$ /  \\__| $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\  \r\n"
                        + //
                        "$$$$$$$$ |$$ |$$  __$$\\ $$  __$$\\       $$ |       \\____$$\\ $$  __$$\\ $$  __$$ |      $$ |$$$$\\  \\____$$\\ $$  _$$  _$$\\ $$  __$$\\ \r\n"
                        + //
                        "$$  __$$ |$$ |$$ /  $$ |$$ |  $$ |      $$ |       $$$$$$$ |$$ |  \\__|$$ /  $$ |      $$ |\\_$$ | $$$$$$$ |$$ / $$ / $$ |$$$$$$$$ |\r\n"
                        + //
                        "$$ |  $$ |$$ |$$ |  $$ |$$ |  $$ |      $$ |  $$\\ $$  __$$ |$$ |      $$ |  $$ |      $$ |  $$ |$$  __$$ |$$ | $$ | $$ |$$   ____|\r\n"
                        + //
                        "$$ |  $$ |$$ |\\$$$$$$$ |$$ |  $$ |      \\$$$$$$  |\\$$$$$$$ |$$ |      \\$$$$$$$ |      \\$$$$$$  |\\$$$$$$$ |$$ | $$ | $$ |\\$$$$$$$\\ \r\n"
                        + //
                        "\\__|  \\__|\\__| \\____$$ |\\__|  \\__|       \\______/  \\_______|\\__|       \\_______|       \\______/  \\_______|\\__| \\__| \\__| \\_______|\r\n"
                        + //
                        "              $$\\   $$ |                                                                                                          \r\n"
                        + //
                        "              \\$$$$$$  |                                                                                                          \r\n"
                        + //
                        "               \\______/");
        System.out.println(""); // adds line

        // Play 5 rounds
        for (int round = 1; round <= 5; round++) {
            Card p1High = getHighestCard(p1);
            Card p2High = getHighestCard(p2);

            System.out.println("\nRound " + round + ":");
            System.out.println(p1.getName() + " plays: " + p1High);
            System.out.println(p2.getName() + " plays: " + p2High);

            if (p1High.getValue() > p2High.getValue()) {
                System.out.println(p1.getName() + " wins the round!");
                p1Points++;
            } else if (p2High.getValue() > p1High.getValue()) {
                System.out.println(p2.getName() + " wins the round!");
                p2Points++;
            } else {
                System.out.println("Tie, no points awarded.");
            }
            // Put the cards back into the deck
            p1.returnCard(p1High, deck);
            p2.returnCard(p2High, deck);
        }
        // Final results
        System.out.println("\nFinal Scores:");
        System.out.println(p1.getName() + ": " + p1Points);
        System.out.println(p2.getName() + ": " + p2Points);

        if (p1Points > p2Points) {
            System.out.println("Winner: " + p1.getName());
        } else if (p2Points > p1Points) {
            System.out.println("Winner: " + p2.getName());
        } else {
            System.out.println("It's a tie!");
        }

        input.close();
    }

    /**
     * Returns the highest-value card currently in the player's hand.
     *
     * @param p - the player whose highest card is checked
     * @return the highest-valued card in that player's hand
     */
    private static Card getHighestCard(Player p) {
        Card[] hand = p.getHand();
        Card highest = hand[0];

        for (Card c : hand) {
            if (c.getValue() > highest.getValue()) {
                highest = c;
            }
        }
        return highest;
    }
}
