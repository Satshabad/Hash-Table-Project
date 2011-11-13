package edu.csupomona.cs.cs241.prog3;

/**
 * This classes is used for testing the hashtables
 * 
 * @author Satshabad
 * 
 */
public class TestDriver {

    public static void main(String args[]) {
        // enter the size and the has you want. 1 or add, 2 for mult, 3 for
        // rot-add, 4 for rot-mult
        // default is set to rehashing table. go change if in the method if you
        // want non rehashing.
        TestDriver.runHash(100, 1);

    }

    /**
     * This method will run any hash function 50 times on the given size adding
     * .25, .5, .75, 1, 1.25, and 1.5 density factor worth of elements. Un-
     * comment some printlns to see some cool stuff
     * 
     * @param size
     *            the size of the hash table
     * @param hash
     *            the hash function. 1 for add, 2 for mult, 3 for rot add, 4 for
     *            rot mult
     */
    public static void runHash(int size, int hash) {
        String hashNames[] = { "", "Additive Hashing",
                "Multiplicative Hashing", "Rotate Additive Hashing",
                "Rotate Multiplicative Hashing" };

        double percentToFill = .25;
        // for each num of elements to fill
        for (int j = 0; j < 6; j++) {
            double longestChainAve = 0;
            double aveChainAve = 0;
            double loadFactorAve = 0;
            double densityFactorAve = 0;
            for (int l = 0; l < 50; l++) {
                KeyValueGenerator kv = new KeyValueGenerator();
                HashFunction hs = new HashFunction(hash);
                StringIntReHashTable ah = new StringIntReHashTable(size, hs);
                String st;
                int n;
                // for each single element to add
                for (int i = 0; i < percentToFill * size; i++) {
                    st = kv.getNextKey();
                    n = kv.getNextValue();
                    // System.err.println(st + " " + n);
                    // System.err.println(i);
                    ah.add(st, n);
                }

                longestChainAve += ah.getLongestChain();
                aveChainAve += ah.getAverageChain();
                loadFactorAve += ah.getLoadFactor();
                densityFactorAve += ah.getDensityFactor();

            }

            System.out.println("For " + hashNames[hash]
                    + " with rehashing and size " + size + " filled up with "
                    + percentToFill * size + " elements");
            System.out
                    .println("Average longest chain :" + longestChainAve / 50);
            System.out.println("Average average chain :" + aveChainAve / 50);
            System.out.println("Average load factor :" + loadFactorAve / 50);
            System.out.println("Average denisity factor :" + densityFactorAve
                    / 50 + "\n");
            percentToFill += .25;
        }

    }

}
