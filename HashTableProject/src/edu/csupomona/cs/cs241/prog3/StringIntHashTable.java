package edu.csupomona.cs.cs241.prog3;

/**
 * This class holds a hash table that does not implement rehashing. It maps
 * Strings to ints
 * 
 * @author Satshabad
 * 
 */
public class StringIntHashTable {
    /**
     * The table to hold the values in Nodes
     */
    private Node array[];
    /**
     * The array to help mirror the structure of the hash table
     */
    private int reportArray[];
    /**
     * the size of the node array
     */
    private int size;
    /**
     * the object holding the hash function
     */
    private HashFunction function;

    /**
     * This creates an instance of this class with the size and the particular
     * hash function to be used
     * 
     * @pre true
     * @post an instance is created
     * @param size
     *            the size of the hash table.
     * @param hf
     *            the hash functions used to hash the keys
     */
    public StringIntHashTable(int size, HashFunction hf) {
        this.size = size;
        array = new Node[size];
        function = hf;
    }

    /**
     * Adds a value with the specified key to the table. If the mapping already
     * exists, the table remains unchanged. This particular add uses chaining to
     * avoid collisions
     * 
     * @pre true
     * @post the table has a mapping for the key to the value
     * @param key
     *            the key to the value
     * @param value
     *            the value to be added
     */
    public void add(String key, int value) {
        // wrap the value in a node
        Node n = new Node(value, null, key);

        // find the hash value of that key
        int hashValue = function.hash(key, size);
        // System.err.println(hashValue);
        // check for collision, chain if there is, otherwise add.
        if (array[hashValue] != null) {

            Node temp = array[hashValue];
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(n);
        } else {
            array[hashValue] = n;
        }
    }

    /**
     * This method removes the mapping from the key to the value and returns the
     * value.
     * 
     * @pre There is a mapping indexed by the key
     * @post The value indexed by key and the mapping no longer exist
     * @param key
     *            the key to the value to be removed
     * @return the value indexed by key
     */
    public int remove(String key) {

        // finds out where value should be
        int hashValue = function.hash(key, size);
        // if the value is chained

        if (array[hashValue] == null) {
            System.err.println("not a valid mapping");
            return 0;
        }

        if (array[hashValue].getNext() != null) {

            // run through the chain and compare the keys and keep track of
            // previous node
            Node temp = array[hashValue];
            Node tempPrev = null;
            while (temp.getNext() != null) {
                if (temp.getKey().compareTo(key) == 0) {
                    break;
                }
                tempPrev = temp;
                temp = temp.getNext();
            }

            if (tempPrev == null) {
                array[hashValue] = temp.getNext();
                temp.setNext(null);
                return temp.getValue();
            }
            // if at the end of the chain then just cut prev pointer
            else if (temp.getNext() == null) {
                tempPrev.setNext(null);
                return temp.getValue();
            } else {

                // if in the middle of chain cut prev and set around the node to
                // nodes next
                tempPrev.setNext(temp.getNext());
                return temp.getValue();
            }

        }
        // if there is no chaining then the key at the hashvalue should always
        // match the looked up key
        else {

            assert (array[hashValue].getKey().compareTo(key) == 0);
            Node temp = array[hashValue];
            array[hashValue] = null;
            return temp.getValue();
        }
    }

    /**
     * This method returns the value indexed by the key
     * 
     * @pre There is a mapping indexed by key
     * @post the value returned is the value indexed by key
     * @param key
     *            the key to to the value being looked up
     * @return The value indexed by key
     */
    public int lookup(String key) {
        // finds out where value should be
        int hashValue = function.hash(key, size);

        if (array[hashValue] == null) {
            System.err.println("not a valid mapping");
            return 0;
        }

        // if the value is chained
        if (array[hashValue].getNext() != null) {

            // run through the chain and compare the keys
            Node temp = array[hashValue];

            while (temp.getNext() != null) {
                if ((temp.getKey().compareTo(key) == 0)) {
                    break;
                }

                temp = temp.getNext();
            }
            // return the value of temp
            return temp.getValue();

            // if there is no chaining then the key at the hashvalue should
            // always match the looked up key
        } else {

            assert (array[hashValue].getKey().compareTo(key) == 0);
            Node temp = array[hashValue];
            array[hashValue] = null;
            return temp.getValue();
        }

    }

    /**
     * This method prints out the Longest chain, Average chain, Load Factor, and
     * Density Factor at any given time
     * 
     * @pre true
     * @post the info is printed
     * 
     */
    public void printReport() {

        System.out.println("Longest Chain: " + getLongestChain());
        System.out.println("AverageChain: " + getAverageChain());
        System.out.println("Load Factor: " + getLoadFactor());
        System.out.println("Density Factor: " + getDensityFactor());

    }

    /**
     * Get the length of the longest chain
     * 
     * @pre true
     * @post the length is returned
     * 
     * @return the length of the longest chain
     */
    public int getLongestChain() {
        int longestChain;

        if (reportArray == null) {
            intitReportArray();
        }

        longestChain = reportArray[0];
        for (int i = 0; i < size; i++) {
            if (reportArray[i] > longestChain) {
                longestChain = reportArray[i];
            }
        }
        return longestChain;
    }

    /**
     * Gets the average length of the chains in the table
     * 
     * @pre true
     * @post the value is returned
     * 
     * @return the length of the average chain
     */
    public double getAverageChain() {
        double averageChain = 0;
        int numOfChains = 0;

        if (reportArray == null) {
            intitReportArray();
        }

        for (int i = 0; i < size; i++) {

            if (reportArray[i] > 0) {
                averageChain += reportArray[i];
                numOfChains++;
            }
        }
        return averageChain / numOfChains;

    }

    /**
     * Get the load factor of the hash table, that is, the ratio of used to
     * unused buckets
     * 
     * @pre true
     * @post the value is returned
     * @return the load factor
     */
    public double getLoadFactor() {
        double loadFactor = 0;

        if (reportArray == null) {
            intitReportArray();
        }

        for (int i = 0; i < size; i++) {

            if (reportArray[i] > 0) {
                loadFactor++;
            }
        }
        return loadFactor / size;
    }

    /**
     * Gets the density factor of the table, that is, the ratio of elements to
     * available buckets
     * 
     * @pre true
     * @post the value is returned
     * @return the density factor
     */
    public double getDensityFactor() {
        double densityFactor = 0;

        if (reportArray == null) {
            intitReportArray();
        }

        for (int i = 0; i < size; i++) {
            if (reportArray[i] > 0) {
                densityFactor += reportArray[i];
            }
        }
        return densityFactor / size;
    }

    /**
     * Private method for initializing the report array from which the reporting
     * methods get there info
     * 
     * @pre true
     * @post the reportArray is not null
     */
    private void intitReportArray() {
        this.reportArray = new int[size];
        for (int i = 0; i < size; i++) {

            if (array[i] == null) {
                reportArray[i] = 0;
            } else {

                Node temp = array[i];
                reportArray[i]++;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    reportArray[i]++;
                }
            }
        }

        for (int i = 0; i < reportArray.length; i++) {
            // System.err.println(reportArray[i]);
        }
    }
}
