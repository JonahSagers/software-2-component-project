package components.ouijiboard;

import java.util.Random;

import components.simplewriter.SimpleWriter;

/**
 * {@code OuijiBoardKernel} enhanced with secondary methods.
 */

public interface OuijiBoard extends OuijiBoardKernel {

    /**
     * Default word for initializing an un-haunted and lame ouiji board.
     */
    String[] WORD_BANK = { "spirit", "ghost", "ghoul", "Paolo Bucci",
            "Lockheed Internship", "The ghost of Leetcode past", "COAM",
            "macbook" };

    /**
     * Check whether the guessed character is equal to, smaller than, or larger
     * than the current character.
     *
     * @param c
     *            the character to guess
     * @return -1 for smaller, 0 for equal, 1 for larger than the current
     *         character
     * @ensures <pre>
     *  [this.spirit is not null]
     * </pre>
     */
    int guess(char c);

    /**
     * Purge the spirit from this plane (select a new word).
     *
     * @param out
     *            writer for printing screams
     * @param random
     *            for selecting an element from WORD_BANK
     * @ensures <pre>
     *  [this.spirit will have unguessed letters]
     * </pre>
     */
    void exorcise(SimpleWriter out, Random random);

    /**
     * Check if the data of both boards is the same.
     *
     * @param otherBoard
     *            The board to compare to
     * @return Whether both boards share the same data
     *
     * @ensures <pre>
     * [this.spirit exists for both boards]
     * </pre>
     */
    boolean equals(OuijiBoard otherBoard);

}
