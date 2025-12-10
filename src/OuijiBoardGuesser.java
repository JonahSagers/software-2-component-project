import java.util.Random;

import components.ouijiboard.OuijiBoard;
import components.ouijiboard.OuijiBoard1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Sample use case of the OuijiBoard class.
 */
public class OuijiBoardGuesser {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        OuijiBoard board = new OuijiBoard1L();
        SimpleWriter out = new SimpleWriter1L();
        Random random = new Random();

        String spirit = "";
        while (true) {

            char guess = '\u0000';
            while (board.guess(guess) != 0) {
                // we don't binary search in this house
                guess++;
            }
            spirit += guess;
            out.print(guess);
            if (spirit.contains("bye")) {
                out.println("");
                spirit = "";
                board.exorcise(out, random);
            }
        }
    }
}
