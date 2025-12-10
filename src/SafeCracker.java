import java.util.Random;

import components.ouijiboard.OuijiBoard;
import components.ouijiboard.OuijiBoard1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Sample use case of the OuijiBoard class.
 */
public class SafeCracker {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        OuijiBoard vault = new OuijiBoard1L();
        SimpleWriter out = new SimpleWriter1L();
        Random random = new Random();

        int digits = 100;
        // Ok forgive the way I've written this but it should generate a number
        // between 1 * 10^digit and 10 * 10^digit, avoiding overflow
        String targetCode = "";
        for (int i = 0; i < digits; i++) {
            int digit = random.nextInt(10);
            targetCode += digit;
        }
        vault.setSpirit(targetCode);
        String vaultCode = "";

        while (vaultCode.length() <= digits) {

            int guess = 0;
            while (vault.guess(Character.forDigit(guess, 10)) != 0) {
                // we don't binary search in this house
                guess++;
                out.println("Testing ... " + vaultCode + guess);
            }
            vaultCode += Character.forDigit(guess, 10);
        }
        out.println("Final Code: " + vaultCode);
    }
}
