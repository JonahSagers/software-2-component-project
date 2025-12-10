package components.ouijiboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public abstract class OuijiBoardTest {
    /**
     * Invokes the appropriate {@code List} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new list
     * @ensures constructorTest = (<>, <>)
     */
    protected abstract OuijiBoard constructorTest();

    /*
     * KERNEL METHOD TESTS
     */

    @Test
    public final void testConstructor() {
        OuijiBoard board1 = this.constructorTest();
    }

    @Test
    public final void testStartingSpirit() {
        OuijiBoard board1 = this.constructorTest();
        // Spirit is assigned randomly, so there's no deterministic way
        // to test exactly what it is

        assertNotEquals(board1.getSpirit(), "");
    }

    @Test
    public final void testTransferFrom() {
        OuijiBoard board1 = this.constructorTest();
        String originalSpirit = board1.getSpirit();
        OuijiBoard board2 = this.constructorTest();
        board2.transferFrom(board1);

        assertEquals(originalSpirit, board2.getSpirit());
    }

    @Test
    public final void testGetSetSpiritEmpty() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("");

        assertEquals(board1.getSpirit(), "");
    }

    @Test
    public final void testGetSetSpiritNonEmpty() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");

        assertEquals(board1.getSpirit(), "test");
    }

    @Test
    public final void testGetSetSpiritSpecialChars() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test_123");

        assertEquals(board1.getSpirit(), "test_123");
    }

    @Test
    public final void testGetIndexZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");

        assertEquals(board1.getIndex(), 0);
    }

    @Test
    public final void testGetIndexNonZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        board1.advance();
        board1.advance();

        assertEquals(board1.getIndex(), 2);
    }

    @Test
    public final void testSetIndexZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        board1.advance();
        board1.advance();
        board1.setIndex(0);

        assertEquals(board1.getIndex(), 0);
    }

    @Test
    public final void testSetIndexNonZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        board1.setIndex(2);

        assertEquals(board1.getIndex(), 2);
    }

    @Test
    public final void testAddEmpty() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("");
        String originalSpirit = board1.getSpirit();
        board1.add('a');

        assertEquals(originalSpirit + "a", board1.getSpirit());
    }

    @Test
    public final void testAddNonEmpty() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        String originalSpirit = board1.getSpirit();
        board1.add('a');

        assertEquals(originalSpirit + "a", board1.getSpirit());
    }

    @Test
    public final void testAddRandom() {
        OuijiBoard board1 = this.constructorTest();
        String originalSpirit = board1.getSpirit();
        board1.add('a');

        assertEquals(originalSpirit + "a", board1.getSpirit());
    }

    @Test
    public final void testDistancePositive() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int distance = board1.distance('z');

        assertEquals(distance, 6);
    }

    @Test
    public final void testDistanceNegative() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int distance = board1.distance('s');

        assertEquals(distance, -1);
    }

    @Test
    public final void testDistanceZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int distance = board1.distance('t');

        assertEquals(distance, 0);
    }

    @Test
    public final void testAdvanceNotEnd() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int oldIndex = board1.getIndex();
        board1.advance();
        int newIndex = board1.getIndex();

        assertEquals(newIndex, oldIndex + 1);
    }

    @Test
    public final void testAdvanceEnd() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        board1.setIndex(3);
        int oldIndex = board1.getIndex();
        board1.advance();
        int newIndex = board1.getIndex();

        assertEquals(oldIndex, 3);
        assertEquals(newIndex, 0);
    }

    /*
     * SECONDARY METHOD TESTS
     */

    @Test
    public final void testGuessPositive() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int oldIndex = board1.getIndex();
        int distance = board1.guess('z');
        int newIndex = board1.getIndex();

        assertEquals(distance, 1);
        assertEquals(oldIndex, newIndex);
    }

    @Test
    public final void testGuessNegative() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int oldIndex = board1.getIndex();
        int distance = board1.guess('a');
        int newIndex = board1.getIndex();

        assertEquals(distance, -1);
        assertEquals(oldIndex, newIndex);
    }

    @Test
    public final void testGuessZero() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        int oldIndex = board1.getIndex();
        int distance = board1.guess('t');
        int newIndex = board1.getIndex();

        assertEquals(distance, 0);
        assertEquals(oldIndex + 1, newIndex);
    }

    @Test
    public final void testGuessZeroLast() {
        OuijiBoard board1 = this.constructorTest();
        board1.setSpirit("test");
        board1.setIndex(3);
        int oldIndex = board1.getIndex();
        int distance = board1.guess('t');
        int newIndex = board1.getIndex();

        assertEquals(distance, 0);
        assertEquals(oldIndex, 3);
        assertEquals(newIndex, 0);
    }

    @Test
    public final void testExorcise() {
        OuijiBoard board1 = this.constructorTest();
        SimpleWriter out = new SimpleWriter1L();
        Random random = new Random();
        // Intentionally choosing a spirit which isn't in the word bank
        // Otherwise there would be a chance of a repeat
        board1.setSpirit("test");
        String oldSpirit = board1.getSpirit();
        board1.exorcise(out, random);
        String newSpirit = board1.getSpirit();

        assertNotEquals(oldSpirit, newSpirit);
    }

    @Test
    public final void testToString() {
        OuijiBoard board1 = this.constructorTest();
        // Intentionally choosing a spirit which isn't in the word bank
        // Otherwise there would be a chance of a repeat
        board1.setSpirit("test");
        String oldSpirit = board1.getSpirit();
        String converted = board1.toString();

        assertEquals(oldSpirit, converted);
    }

    @Test
    public final void testEqualsTrue() {
        OuijiBoard board1 = this.constructorTest();
        // Intentionally choosing a spirit which isn't in the word bank
        // Otherwise there would be a chance of a repeat
        board1.setSpirit("test");
        OuijiBoard board2 = this.constructorTest();
        board2.setSpirit("test");

        assertTrue(board1.equals(board2));
    }

    @Test
    public final void testEqualsFalse() {
        OuijiBoard board1 = this.constructorTest();
        // Intentionally choosing a spirit which isn't in the word bank
        // Otherwise there would be a chance of a repeat
        board1.setSpirit("test");
        OuijiBoard board2 = this.constructorTest();

        assertFalse(board1.equals(board2));
    }
}
