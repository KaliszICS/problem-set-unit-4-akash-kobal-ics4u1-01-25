import java.util.ArrayList;

/**
 * Deck Class represents a collection of Card objects
 * Can be created off array of cards, or made as a full,
 * unshuffled standard deck with all four suits
 * Provides methods to draw, shuffle, add cards, reshuffle,
 * and check the current size of the deck.
 * 
 * @author Akash K.
 * @version 1.0
 */
public class Deck {
    private ArrayList<Card> cards;
    private static final int CARD_VALUE_OFFSET = 1;
    private static final int SHUFFLE_LAST_INDEX_OFFSET = 1;

    /**
     * Creates a deck using the given array of cards.
     * Any null entries inside the array are skipped.
     *
     * @param cardArray - the array of Card objects used to form the deck
     * 
     * @throws IllegalArgumentException - if cardArray is null
     */
    public Deck(Card[] cardArray) {
        if (cardArray == null)
            throw new IllegalArgumentException("Card array cannot be null");
        cards = new ArrayList<>();
        for (Card c : cardArray) {
            if (c != null) {
                addCard(c); 
            }
        }
        shuffle();

    }

    /**
     * Creates a standard unshuffled 52-card deck.
     * The order of suits is Hearts, Clubs, Diamonds, Spades.
     * Within each suit, cards go from Ace through King.
     */
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = { "Hearts", "Clubs", "Diamonds", "Spades" };
        String[] names = { "Ace", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "Jack", "Queen", "King" };
        for (String suit : suits) {
            for (int i = 0; i < names.length; i++) {
                cards.add(new Card(names[i], suit, i + CARD_VALUE_OFFSET));
            }
        }
    }

    /**
     * Returns the number of cards currently in the deck.
     *
     * @return the size of the deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Removes and returns the top card of the deck.
     * The top of the deck is the card at index 0.
     * If the deck is empty, returns null.
     *
     * @return the top card, or null if the deck has no cards
     */
    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    /**
     * Randomly shuffles the order of all cards in the deck.
     * Uses swapping to rearrange the deck.
     */
    public void shuffle() {
        for (int i = cards.size() - SHUFFLE_LAST_INDEX_OFFSET; i > 0; i--) {
            int j = (int) (Math.random() * (i + SHUFFLE_LAST_INDEX_OFFSET));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    /**
     * Adds a single card to the bottom of the deck.
     * If the provided card is null, nothing is added.
     *
     * @param card - the card to add
     * @throws NullPointerException if card is null
     */
    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Cannot add null card to deck");
        }
        cards.add(card);
    }

    /**
     * Adds each non-null card from the provided array into the deck.
     * Once all cards are added, the deck is shuffled.
     *
     * @param cardArray - the array of cards to place back into the deck
     * @throws NullPointerException if cardArray is null
     */
    public void reshuffle(Card[] cardArray) {
        if (cardArray == null)
            throw new NullPointerException("Card array cannot be null");
        for (Card c : cardArray) {
            if (c != null) {
                addCard(c);
            }
        }
        shuffle();
    }

    /**
     * Returns the deck's contents in list form.
     *
     * @return a string showing all cards in the deck
     */
    @Override
    public String toString() {
        return cards.toString();
    }
}
