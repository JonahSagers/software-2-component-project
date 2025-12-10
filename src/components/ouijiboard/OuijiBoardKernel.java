package components.ouijiboard;

import components.standard.Standard;

/**
 * {@code OuijiBoardKernel} enhanced with secondary methods.
 */
public interface OuijiBoardKernel extends Standard<OuijiBoard> {

    /**
     * Default word for initializing an un-haunted and lame ouiji board.
     */
    String DEFAULT_WORD = "spirit";

    /**
     * Create a new instance of OuijiBoard from OuijiBoard1L.
     *
     * @return the newly created instance
     */
    @Override
    OuijiBoard newInstance();

    /**
     * Clear all data from this.
     */
    @Override
    void clear();

    /**
     * Transfer data from source to this.
     *
     * @param source
     *            the source to transfer from
     */
    @Override
    void transferFrom(OuijiBoard source);

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

    /**
     * get the index value from the board, for access in secondary methods.
     *
     * @return this.index
     */
    int getIndex();

    /**
     * sets the index value and returns the old value.
     *
     * @param newIndex
     *            the value to replace index with
     * @return the old value of index
     */
    int setIndex(int newIndex);

}
