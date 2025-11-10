/**
 * {@code OuijiBoardKernel} enhanced with secondary methods.
 */
public interface OuijiBoardKernel {

    /**
     * Default word for initializing an un-haunted and lame ouiji board.
     */
    String DEFAULT_WORD = "spirit";

    /**
     * Appends c to this.
     *
     * @param c
     *            the character to append
     * @ensures <pre>
     *  [this.spirit length > 0]
     * </pre>
     */
    void add(char c);

    /**
     * returns the difference in char value between c and this at the current
     * index.
     *
     * @param c
     *            the character to check
     * @return the distance between c and the current character
     */
    int distance(char c);

    /**
     * increment the index of the character to check.
     */
    void advance();

    /**
     * get the spirit value from the board, for access in secondary methods.
     *
     * @return this.spirit
     */
    String getSpirit();

    /**
     * sets the spirit value and returns the old value.
     *
     * @param newSpirit
     *            the word to replace spirit with
     * @return the old value of spirit
     */
    String setSpirit(String newSpirit);

}
