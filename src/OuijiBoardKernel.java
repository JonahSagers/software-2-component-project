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

}
