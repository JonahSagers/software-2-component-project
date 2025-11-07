/**
 * {@code OuijiBoardKernel} enhanced with secondary methods.
 */

public interface OuijiBoard extends OuijiBoardKernel {

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
     * @ensures <pre>
     *  [this.spirit will have unguessed letters]
     * </pre>
     */
    void exorcise();

}
