import java.util.Random;

import components.simplewriter.SimpleWriter;

/**
 * OuijiBoard data type.
 */
public abstract class OuijiBoardSecondary implements OuijiBoard {

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
    @Override
    public int guess(char c) {
        int dist = this.distance(c);
        if (dist == 0) {
            this.advance();
        } else {
            dist = Integer.signum(dist);
        }
        return dist;
    }

    /**
     * Purge the spirit from this plane (select a new word).
     *
     * @ensures <pre>
     *  [this.spirit will have unguessed letters]
     * </pre>
     */
    @Override
    public void exorcise(SimpleWriter out, Random random) {
        /*
         * Most methods would leave the choice of printing to the client, but
         * unfortunately spirits are very difficult to keep out of the console
         */
        out.println("OUUUUGHHAHGUUGHHHHHAAAAAAAAAAAAA");

        /*
         * Not entirely sure if I'm allowed to use the standard java random
         * library, will change in future assignments if required
         */
        this.setSpirit(WORD_BANK[random.nextInt(WORD_BANK.length)]);
        out.println("The board is set...");
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder("(");
        result.append(this.getSpirit());
        return result.toString();
    }

    @Override
    public final boolean equals(OuijiBoard otherBoard) {
        return this.getSpirit().equals(otherBoard.getSpirit());
    }

}
