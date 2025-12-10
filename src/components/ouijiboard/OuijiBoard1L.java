package components.ouijiboard;

import java.util.Random;

/**
 * {@code OuijiBoardKernel} enhanced with secondary methods.
 */

public class OuijiBoard1L extends OuijiBoardSecondary {

    /**
     * String representation of the spirit inside the board.
     */
    private String spirit;

    /**
     * The current character to check.
     */
    private int index;

    /**
     * Handler for random word generation.
     */
    private Random random;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.random = new Random();
        this.spirit = WORD_BANK[this.random.nextInt(WORD_BANK.length)] + "bye";
        this.index = 0;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public OuijiBoard1L() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final OuijiBoard newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(OuijiBoard source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof OuijiBoard1L : ""
                + "Violation of: source is of dynamic type OuijiBoard";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type OuijiBoard
         */
        OuijiBoard1L localSource = (OuijiBoard1L) source;
        this.spirit = localSource.spirit;
        localSource.createNewRep();
    }

    @Override
    public final void add(char c) {
        this.spirit += c;
    }

    @Override
    public final int distance(char c) {
        int spiritVal = this.spirit.charAt(this.index);
        int guessVal = c;
        return guessVal - spiritVal;
    }

    @Override
    public final void advance() {
        this.index += 1;
        this.index %= this.spirit.length();
    }

    @Override
    public final String getSpirit() {
        return this.spirit;
    }

    @Override
    public final String setSpirit(String newSpirit) {
        String oldSpirit = this.spirit;
        this.spirit = newSpirit;
        return oldSpirit;
    }

    @Override
    public final int getIndex() {
        return this.index;
    }

    @Override
    public final int setIndex(int newIndex) {
        int oldIndex = this.index;
        this.index = newIndex;
        return oldIndex;
    }

}
