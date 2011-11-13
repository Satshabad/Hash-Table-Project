package edu.csupomona.cs.cs241.prog3;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class created pairs of integers and strings in a particular way.
 * 
 * @author Satshabad
 * 
 */
public class KeyValueGenerator {

    /**
     * The maximum length of string
     */
    private final int LENGTH_OF_STRING = 4;

    /**
     * The random number generator
     */
    private Random rand;

    /**
     * I used this for convenience to hold the keys already created.
     */
    private ArrayList<String> stringlist = new ArrayList<String>();

    /**
     * This initializes the key generator
     * 
     * @pre true
     * @pre the object is created
     */
    public KeyValueGenerator() {
        rand = new Random();
    }

    /**
     * @deprecated
     */
    public void initialize() {
        for (int i = 0; i < LENGTH_OF_STRING; i++) {
            // key += (char)(rand.nextInt(26) + 97);

        }
    }

    /**
     * This method get the next key in the sequence. Every time this is called
     * the key is increment by 1. the key is in base 26. The max value of key is
     * zzzzzzzz
     * 
     * @pre the key is less than zzzzzzzz
     * @post the new key is one more than the previous one
     * @return the incremented key
     */
    public String getNextKey() {
        String key;

        rand.setSeed(System.currentTimeMillis());
        do {
            key = "";
            for (int i = 0; i < LENGTH_OF_STRING; i++) {
                key += (char) (rand.nextInt(26) + 97);

            }
        } while (stringlist.contains(key));
        stringlist.add(key);
        return key;

    }

    /**
     * This method generates a random int between 1 and 100 inclusive
     * 
     * @pre true
     * @post the random in is returned
     * @return the random int
     */
    public int getNextValue() {
        return (rand.nextInt(100) + 1);

    }

    /**
     * This method handles the business logic of the getNextKey method.
     * 
     * @pre nextKey must be at most length 8 and position must be greater than 0
     * @post a string with a value one more than the passed string will be
     *       returned (base 26)
     * @param nextKey
     *            the string to be incremented
     * @param pos
     *            the position to start the increment at
     * @return the incremented string
     * @deprecated
     */
    @SuppressWarnings("unused")
    private String nextKey(String nextKey, int pos) {

        // the project does not specify what should happen if we get to
        // the value zzzzzzzz and try to increment so I print an error
        if (pos == -1) {
            System.err.println("String Too big!");
            return null;
        }
        // Strings are immutable so we have to use a new string
        String copyKey = "";
        char c = nextKey.charAt(pos);

        // This finds the next value of the char
        c = (char) (((c + 1) % 123));
        if (c == 0) {
            c = 97;
        }

        // this make the new string, but since it's indexed in reverse it is
        // made in reverse.
        for (int i = LENGTH_OF_STRING - 1; i >= 0; i--) {
            if (i == pos) {
                copyKey += c;
            } else {
                copyKey += nextKey.charAt(i);
            }
        }

        // so it is reversed again to make it right
        copyKey = reverseString(copyKey);

        // if the char we increased was z than we must also increment the
        // next digit in the string, so we call it recursively
        if (c == 'a') {
            return nextKey(copyKey, pos - 1);
        }
        return copyKey;
    }

    /**
     * This method reverese any given string
     * 
     * @pre the string must be of length 8
     * @post the a copy of the string is returned in reverse order
     * @param s
     *            the string to be reversed
     * @return the reveresed string
     */
    private String reverseString(String s) {
        String newString = "";

        for (int i = LENGTH_OF_STRING - 1; i >= 0; i--) {
            newString += s.charAt(i);
        }
        return newString;

    }
}
