import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
 * ----------------------------------------------------------------------------
 * THIS FILE WILL NOT WORK IN THIS FOLDER probably.
 *
 * Since we're cleaning up the repo I moved this out of src
 * ----------------------------------------------------------------------------
 * /



/**
 * OuijiBoard data type.
 */
public class OuijiBoardPOC {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Default value for the board.
     */
    private static final String DEFAULT_WORD = "spirit";

    /**
     * Buckets for hashing.
     */
    private String spirit;
    /**
     * Index of current character.
     */
    private int index;

    /**
     * Creator of initial representation.
     *
     * @param spiritValue
     *            the size of the hash table
     * @requires spiritValue.length > 0
     * @ensures spirit.length > 0
     */
    private void createNewRep(String spiritValue) {
        /*
         * With "new Map<K, V>[...]" in place of "new Map[...]" it does not
         * compile; as shown, it results in a warning about an unchecked
         * conversion, though it cannot fail.
         */
        this.spirit = spiritValue;
        this.index = 0;

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public OuijiBoardPOC() {

        this.createNewRep(DEFAULT_WORD);

    }

    /**
     * Constructor resulting in a hash table of size {@code hashTableSize}.
     *
     * @param spiritValue
     *            value of ouijiBoard
     * @requires spiritValue.length > 0
     * @ensures this = {}
     */
    public OuijiBoardPOC(String spiritValue) {

        this.createNewRep(spiritValue);

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    /**
     * Create a new ouijiboard.
     *
     * @return ouijiboard
     */
    public final OuijiBoardPOC newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    /**
     * Clears the board.
     */
    public final void clear() {
        this.createNewRep(DEFAULT_WORD);
    }

    /**
     * Transfer data from another board.
     *
     * @param source
     */
    public final void transferFrom(OuijiBoardPOC source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof OuijiBoardPOC : ""
                + "Violation of: source is of dynamic type OuijiBoard";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type OuijiBoard
         */
        OuijiBoardPOC localSource = (OuijiBoardPOC) source;
        this.spirit = localSource.spirit;
        this.index = 0;
        localSource.createNewRep(DEFAULT_WORD);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /**
     * Add a character to spirit.
     *
     * @param c
     */
    public final void add(char c) {
        //Char is never null, check against empty ascii value instead
        assert c != '\u0000' : "Violation of: value is not null";

        this.spirit += c;
    }

    /**
     * Guess what the next value in the spirit is.
     *
     * @param c
     *            the character to guess
     * @return distance between characters
     */
    public final int distance(char c) {
        int spiritVal = this.spirit.charAt(this.index);
        int guessVal = c;
        return guessVal - spiritVal;
    }

    /**
     * Increment index.
     *
     * @param i
     *            the amount by which to increment
     */
    public final void advance(int i) {
        this.index += i;
        this.index %= this.spirit.length();
    }

    /*
     * Secondary methods ------------------------------------------------------
     * Idk if this is supposed to go here but it will for now
     */

    /**
     * Guess the next value in spirit.
     *
     * @param c
     *            the character to guess
     * @return -1 for too small, 0 for correct, 1 for too large
     */
    public final int guess(char c) {
        int dist = this.distance(c);
        if (dist == 0) {
            this.advance(1);
        } else {
            dist = Integer.signum(dist);
        }
        return dist;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader inConsole = new SimpleReader1L();
        SimpleWriter outConsole = new SimpleWriter1L();
        OuijiBoardPOC ouijiBoard = new OuijiBoardPOC();
        char input = ' ';
        while (input != '0') {
            outConsole.println("Guess a letter: ");
            //Only take the first letter inputted
            String inputString = inConsole.nextLine();
            if (inputString.length() > 0) {
                input = inputString.charAt(0);
                int result = ouijiBoard.guess(input);
                if (result == 0) {
                    outConsole.println("OOOOOOOOOOOO spooky");
                } else if (result < 0) {
                    outConsole.println("Too low...");
                } else {
                    outConsole.println("Too high...");
                }
            }

        }

        inConsole.close();
        outConsole.close();
    }

}
