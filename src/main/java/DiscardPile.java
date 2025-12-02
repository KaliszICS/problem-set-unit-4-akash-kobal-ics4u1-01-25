import java.util.ArrayList;

/**
 * The DiscardPile class represents a pile of discarded Card objects.
 * Can be created from an existing array of cards or as an empty pile.
 * Provides methods for adding cards, removing specific cards,
 * removing all cards, checking the size, and returning the pile as an array.
 * 
 * @author Akash K.
 * @version 1.0
 */
public class DiscardPile {
    private ArrayList<Card> pile = new ArrayList<Card>();

    /**
     * Constructs a DiscardPile using the provided array of cards.
     * Any null card inside the array is ignored.
     *
     * @param cards - the array of cards used to create the discard pile
     * @throws IllegalArgumentException if cards is null
     */
    public DiscardPile(Card[] cards) {
        if (cards == null)
            throw new IllegalArgumentException("Card array cannot be null");
        pile = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                addCard(cards[i]); 
            }
        }
    }

    /**
     * Constructs an empty discard pile with no cards.
     */
    public DiscardPile() {
        pile = new ArrayList<Card>();
    }

    /**
     * Returns all cards currently in the discard pile as an array.
     *
     * @return an array containing all cards in the pile
     */
    public Card[] getCards() {
        Card[] arr = new Card[pile.size()];
        return pile.toArray(arr);
    }

    /**
     * Returns the amount of cards in the discard pile.
     *
     * @return the size of the pile
     */
    public int size() {
        return pile.size();
    }

    /**
     * Adds a card to the discard pile.
     *
     * @param card - the card to add
     * @throws IllegalArgumentException if card is null
     */
    public void addCard(Card card) {
        if (card == null)
            throw new IllegalArgumentException("Cannot add null card to discard pile");
        pile.add(card);
    }

    /**
     * Removes the specified card from the discard pile and returns it.
     * If the card does not exist in the pile, returns null.
     *
     * @param card - the card to remove
     * @return the removed card, or null if not found
     * @throws IllegalArgumentException if card is null
     */
    public Card removeCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Cannot remove null card from discard pile");
        }
        for (int i = 0; i < pile.size(); i++) {
            if (pile.get(i).equals(card)) {
                return pile.remove(i);
            }
        }
        return null;
    }

    /**
     * Removes and returns all cards in the discard pile.
     * If empty, returns an empty Card array.
     *
     * @return an array containing all removed cards
     */
    public Card[] removeAll() {
        if (pile.isEmpty()) {
            return new Card[0];
        }
        Card[] all = getCards();
        pile.clear();
        return all;
    }

    /**
     * Returns a formatted string listing all cards in the discard pile.
     * Cards are separated by commas, and the last card ends with a period.
     *
     * @return a string representing all the cards in the pile
     */
    @Override
    public String toString() {
        if (pile.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pile.size(); i++) {
            sb.append(pile.get(i).toString());
            if (i < pile.size() - 1)
                sb.append(", ");
            else
                sb.append(".");
        }

        return sb.toString();
    }
}