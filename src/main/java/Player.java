import java.util.ArrayList;

/**
 * The Player class represents a player in the card game.
 * Each player has a name, age, and a hand of cards.
 * A player can draw cards from a deck, discard cards to a discard pile,
 * return cards to a deck, and view their current hand.
 * 
 * @author Akash K.
 * @version 1.0
 */
public class Player {
    private String name;
    private int age;
    private ArrayList<Card> hand = new ArrayList<Card>();

    /**
     * Constructs a Player with a name, age, and an initial starting hand.
     * Null cards in the starting hand are ignored.
     *
     * @param name         - the player's name
     * @param age          - the player's age
     * @param startingHand - the initial cards given to the player
     * 
     * @throws IllegalArgumentException if name is null, empty, age is negative,
     *                                  or startingHand is null
     */
    public Player(String name, int age, Card[] startingHand) {
        if (name == null)
            throw new IllegalArgumentException("Player name cannot be null");

        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Player name cannot be empty");

        if (age < 0)
            throw new IllegalArgumentException("Player age cannot be negative");

        if (startingHand == null)
            throw new IllegalArgumentException("Starting hand cannot be null");
        this.name = name.trim();
        this.age = age;

        for (int i = startingHand.length - 1; i >= 0; i--) {
            Card c = startingHand[i];
            if (c != null) {
                hand.add(c);
            }
        }
    }

    /**
     * Constructs a Player with an empty hand.
     *
     * @param name - the player's name
     * @param age  - the player's age
     * 
     * @throws IllegalArgumentException if name is null, empty or age is negative
     */
    public Player(String name, int age) {
        if (name == null)
            throw new IllegalArgumentException("Player name cannot be null");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Player name cannot be empty");
        if (age < 0)
            throw new IllegalArgumentException("Player age cannot be negative");
        this.name = name.trim();
        this.age = age;
        hand = new ArrayList<Card>();
    }

    /**
     * Returns the player's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the player's hand as an array of cards.
     * The returned array goes from top of the hand to bottom.
     *
     * @return the player's hand as an array
     */
    public Card[] getHand() {
        Card[] arr = new Card[hand.size()];
        for (int i = 0; i < hand.size(); i++) {
            arr[i] = hand.get(hand.size() - 1 - i);
        }
        return arr;
    }

    /**
     * Returns the number of cards in the player's hand.
     *
     * @return the hand size
     */
    public int size() {
        return hand.size();
    }

    /**
     * Draws a card from the given deck and adds it to the player's hand.
     * If the deck has no cards left, nothing is added.
     *
     * @param deck - the deck to draw from
     * @throws IllegalArgumentException if deck is null
     */

    public void draw(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck cannot be null");
        }
        Card pulled = deck.draw();
        if (pulled != null) {
            hand.add(pulled);
        }
    }

    /**
     * Discards a card from the player's hand into the specified discard pile.
     * Returns true if the card was found and removed, false otherwise.
     *
     * @param card - the card to discard
     * @param pile - the discard pile to place the card into
     * @return true if the card was discarded, false if not found
     * 
     * @throws IllegalArgumentException if card or pile is null
     */
    public boolean discardCard(Card card, DiscardPile pile) {
        if (card == null)
            throw new IllegalArgumentException("Card cannot be null");
        if (pile == null)
            throw new IllegalArgumentException("Discard pile cannot be null");

        if (hand.contains(card)) {
            hand.remove(card);
            pile.addCard(card);
            return true;
        }
        return false;
    }

    /**
     * Returns a card from the player's hand back into the given deck.
     * Returns true if the card was found in the hand, false otherwise.
     *
     * @param card - the card to return
     * @param deck - the deck to return the card to
     * @return true if the card existed and was returned, false otherwise
     * 
     * @throws IllegalArgumentException if card or deck is null
     */
    public boolean returnCard(Card card, Deck deck) {
        if (card == null)
            throw new IllegalArgumentException("Card cannot be null");
        if (deck == null)
            throw new IllegalArgumentException("Deck cannot be null");
        if (hand.contains(card)) {
            hand.remove(card);
            deck.addCard(card);
            return true;
        }
        return false;
    }

    /**
     * Produces a formatted string containing the player's name, age,
     * and a complete ordered listing of all cards in the player's hand,
     * starting from the top of the hand to the bottom, ending in a period.
     * 
     * @return a string showing the player's information and hand ending in period
     */
    @Override
    public String toString() {
        String s = name + ", " + age;
        for (int i = hand.size() - 1; i >= 0; i--) {
            s += ", " + hand.get(i).toString();
        }
        return s + ".";
    }
}
