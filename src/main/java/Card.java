/**
 * The Card class represents a card deck intaking a name, suit, and value.
 * It provides getter methods for name, suit, and value
 * 
 * @author Akash K.
 * @version 1.0
 */
public class Card {
    private String name;
    private String suit;
    private int value;
    
    /**
     * Constructs a Card object with the specified values
     * 
     * @param name - the card's name or rank
     * @param suit - the card's suit
     * @param value - the card's value
     * 
     * @exception NullPointerException - if name, or suit is null
     * @exception IllegalArgumentException - if card, suit, is empty, or if value is negative 
     */
    public Card(String name, String suit, int value) {
        if (name == null)
            throw new NullPointerException("Card name cannot be null");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Card name cannot be empty");
        if (suit == null)
            throw new NullPointerException("Card suit cannot be null");
        if (suit.trim().isEmpty())
            throw new IllegalArgumentException("Card suit cannot be empty");
        if (value < 0)
            throw new IllegalArgumentException("Card value cannot be negative");

        this.name = name.trim();
        this.suit = suit.trim();
        this.value = value;
    }
    /**
     * Returns the name
     * 
     * @return the name given
     */
    public String getName() {
        return this.name;
    }
    /**
     * Returns the suit
     * 
     * @return the suit given
     */
    public String getSuit() {
        return this.suit;
    }
    /**
     * Returns the value
     * 
     * @return the value given
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns "(name) of (suit)" 
     * 
     * @return the name followed by "of" and then the suit
     */
    @Override
    public String toString() {
        return (name + " of " + suit);
    }
    /**
     * Checks if two cards have the same name, suit, and value, and returns true if so.
     * Also checks if this card is equal to the obj input.
     * If they are not the same, returns false.
     * 
     * @param obj the object to compare
     * @return true if first and second card is equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return this.name.equals(other.name) 
            && this.suit.equals(other.suit)
            && this.value == other.value;
    }
}    

