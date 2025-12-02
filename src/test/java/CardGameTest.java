import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.util.*;

public class CardGameTest {

    // ===============================================================
    // ========================= CARD TESTS ==========================
    // ===============================================================

    @Test
    public void testCardConstructorExists() {
        try {
            Class.forName("Card")
                .getConstructor(String.class, String.class, int.class);
        } catch (Exception e) {
            fail("Card must have a constructor (String, String, int)");
        }
    }

    @Test
    public void testCardFieldsPrivate() {
        try {
            Field[] fields = Class.forName("Card").getDeclaredFields();
            for (Field f : fields) {
                assertTrue(Modifier.isPrivate(f.getModifiers()),
                        "Field '" + f.getName() + "' must be private");
            }
        } catch (Exception e) {
            fail("Unexpected error while checking field modifiers: " + e.getMessage());
        }
    }

    @Test
    public void testCardNullNameThrows() {
        try {
            Constructor<?> ctor = Class.forName("Card")
                    .getConstructor(String.class, String.class, int.class);
            ctor.newInstance(null, "Hearts", 1);
            fail("Expected NullPointerException for null name");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof NullPointerException,
                    "Expected NullPointerException for null name");
        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }

    @Test
    public void testCardEmptyNameThrows() {
        try {
            Constructor<?> ctor = Class.forName("Card")
                    .getConstructor(String.class, String.class, int.class);
            ctor.newInstance("   ", "Hearts", 1);
            fail("Expected IllegalArgumentException for empty name");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }

    @Test
    public void testCardNullSuitThrows() {
        try {
            Constructor<?> ctor = Class.forName("Card")
                    .getConstructor(String.class, String.class, int.class);
            ctor.newInstance("Ace", null, 1);
            fail("Expected NullPointerException for null suit");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }

    @Test
    public void testCardToStringCorrect() {
        try {
            Constructor<?> ctor = Class.forName("Card")
                    .getConstructor(String.class, String.class, int.class);
            Object card = ctor.newInstance("Queen", "Diamonds", 12);
            Method toString = Class.forName("Card").getMethod("toString");
            assertEquals("Queen of Diamonds", toString.invoke(card));
        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }



    // ===============================================================
    // ======================== DECK TESTS ===========================
    // ===============================================================

    @Test
    public void testDeckDefaultConstructorHasCorrectOrder() {
        try {
            Class<?> deckClass = Class.forName("Deck");
            Object deck = deckClass.getConstructor().newInstance();

            Method draw = deckClass.getMethod("draw");

            String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
            String[] names = {"Ace", "2", "3", "4", "5", "6", "7",
                    "8", "9", "10", "Jack", "Queen", "King"};

            for (String suit : suits) {
                for (String name : names) {
                    Object c = draw.invoke(deck);
                    Method getName = c.getClass().getMethod("getName");
                    Method getSuit = c.getClass().getMethod("getSuit");

                    assertEquals(name, getName.invoke(c));
                    assertEquals(suit, getSuit.invoke(c));
                }
            }

        } catch (Exception e) {
            fail("Default deck constructor order incorrect: " + e.getMessage());
        }
    }

    @Test
    public void testDeckShuffleChangesOrder() {
        try {
            Class<?> deckClass = Class.forName("Deck");
            Object deck1 = deckClass.getConstructor().newInstance();
            Object deck2 = deckClass.getConstructor().newInstance();

            Method shuffle = deckClass.getMethod("shuffle");

            shuffle.invoke(deck1);

            Method draw = deckClass.getMethod("draw");

            boolean allSame = true;
            for (int i = 0; i < 10; i++) {
                Object c1 = draw.invoke(deck1);
                Object c2 = draw.invoke(deck2);
                if (!c1.equals(c2)) {
                    allSame = false;
                    break;
                }
            }
            assertFalse(allSame, "Shuffle must change deck order");

        } catch (Exception e) {
            fail("Shuffle test failed: " + e.getMessage());
        }
    }

    @Test
    public void testDeckAddNullThrows() {
        try {
            Class<?> deckClass = Class.forName("Deck");
            Object deck = deckClass.getConstructor().newInstance();

            Method add = deckClass.getMethod("addCard", Class.forName("Card"));
            add.invoke(deck, new Object[]{null});

            fail("Expected NullPointerException for null card");
        } catch (InvocationTargetException e) {
            assertTrue(e.getCause() instanceof NullPointerException);
        } catch (Exception e) {
            fail("Unexpected: " + e.getMessage());
        }
    }



    // ===============================================================
    // ===================== DISCARDPILE TESTS =======================
    // ===============================================================

    @Test
    public void testDiscardPileRemoveCard() {
        try {
            Class<?> cardClass = Class.forName("Card");
            Class<?> dpClass = Class.forName("DiscardPile");

            Object c1 = cardClass.getConstructor(String.class, String.class, int.class)
                    .newInstance("Ace", "Hearts", 1);

            Object pile = dpClass.getConstructor().newInstance();
            Method add = dpClass.getMethod("addCard", cardClass);
            Method remove = dpClass.getMethod("removeCard", cardClass);

            add.invoke(pile, c1);
            Object removed = remove.invoke(pile, c1);

            assertEquals(c1, removed);

        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }

    @Test
    public void testDiscardPileToStringFormat() {
        try {
            Class<?> cardClass = Class.forName("Card");
            Class<?> dpClass = Class.forName("DiscardPile");

            Object c1 = cardClass.getConstructor(String.class, String.class, int.class)
                    .newInstance("Jack", "Spades", 11);
            Object c2 = cardClass.getConstructor(String.class, String.class, int.class)
                    .newInstance("3", "Hearts", 3);

            Object pile = dpClass.getConstructor().newInstance();
            Method add = dpClass.getMethod("addCard", cardClass);
            Method toString = dpClass.getMethod("toString");

            add.invoke(pile, c1);
            add.invoke(pile, c2);

            String out = (String) toString.invoke(pile);
            assertEquals("Jack of Spades, 3 of Hearts.", out);

        } catch (Exception e) {
            fail("toString formatting incorrect: " + e.getMessage());
        }
    }



    // ===============================================================
    // ========================= PLAYER TESTS ========================
    // ===============================================================

    @Test
    public void testPlayerDrawAddsCard() {
        try {
            Class<?> playerClass = Class.forName("Player");
            Class<?> deckClass = Class.forName("Deck");

            Object player = playerClass.getConstructor(String.class, int.class)
                    .newInstance("Joe", 30);
            Object deck = deckClass.getConstructor().newInstance();

            Method size = playerClass.getMethod("size");
            Method draw = playerClass.getMethod("draw", deckClass);

            int before = (int) size.invoke(player);
            draw.invoke(player, deck);
            int after = (int) size.invoke(player);

            assertEquals(before + 1, after);

        } catch (Exception e) {
            fail("Player draw() failed: " + e.getMessage());
        }
    }

    @Test
    public void testPlayerDiscardMovesCardToPile() {
        try {
            Class<?> playerClass = Class.forName("Player");
            Class<?> cardClass = Class.forName("Card");
            Class<?> dpClass = Class.forName("DiscardPile");

            Object card = cardClass.getConstructor(String.class, String.class, int.class)
                    .newInstance("Ace", "Clubs", 1);

            Object player = playerClass.getConstructor(String.class, int.class, Card[].class)
                    .newInstance("Bob", 25, new Card[]{(Card)card});

            Object pile = dpClass.getConstructor().newInstance();

            Method discard = playerClass.getMethod("discardCard", cardClass, dpClass);
            Method getCards = dpClass.getMethod("getCards");

            discard.invoke(player, card, pile);

            Object[] pileArr = (Object[]) getCards.invoke(pile);
            assertEquals(1, pileArr.length);

        } catch (Exception e) { fail("Unexpected: " + e.getMessage()); }
    }
}
