package edu.csupomona.cs.cs241.prog3;


public class StringIntHashTable<K extends String,V extends Integer> implements HashTable<K, V>{

    private Node array[];
    private int size;
    private HashFunction function;
    
    @SuppressWarnings("unchecked")
    public StringIntHashTable(int size, HashFunction hf) {
        this.size = size;
        array = new Node[size];
        function = hf;
    }
    
    
    /**
     * Adds a value with the specified key to the table. 
     * If the mapping already exists, the table remains unchanged.
     * This particular add uses chaining to avoid collisions
     * 
     * @pre true
     * @post the table has a mapping for the key to the value 
     * @param key the key to the value 
     * @param value the value to be added
     */
    @Override
    public void add(K key, V value) {
        // wrap the value in a node
        Node n = new Node(value, null, key);
        
        // find the hash value of that key
        int hashValue = function.hash(key, size);
        //System.err.println(hashValue);
        // check for collision, chain if there is, otherwise add.
        if (array[hashValue] != null){
            
            Node temp = array[hashValue];
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            temp.setNext(n);
        }
        else{
            array[hashValue] = n;
        }
    }

    /**
     * This method removes the mapping from the key to the value and returns the value.
     * 
     * @pre There is a mapping indexed by the key
     * @post The value indexed by key and the mapping no longer exist
     * @param key the key to the value to be removed
     * @return the value indexed by key
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        
        // finds out where value should be
        int hashValue = function.hash(key, size);
        // if the value is chained

        if (array[hashValue] == null){
            System.err.println("not a valid mapping");
            return null;
        }
        
        if (array[hashValue].getNext() != null){
            
            // run through the chain and compare the keys and keep track of previous node
            Node temp = array[hashValue];
            Node tempPrev = null;
            while (temp.getNext() != null){
                if (temp.getKey().compareTo(key) == 0){
                    break;
                }
                tempPrev = temp;
                temp = temp.getNext();
            }
            
            
            if (tempPrev == null){
                array[hashValue] = temp.getNext();
                temp.setNext(null);
                return (V) new Integer(temp.getValue());
            }
            // if at the end of the chain then just cut prev pointer 
            else if (temp.getNext() == null){
                tempPrev.setNext(null);
                return (V) new Integer(temp.getValue());
            }else{
                
                // if in the middle of chain cut prev and set around the node to nodes next
                tempPrev.setNext(temp.getNext());
                return (V) new Integer(temp.getValue());
            }
            
        }
        // if there is no chaining then the key at the hashvalue should always match the looked up key
        else{
            
            assert (array[hashValue].getKey().compareTo(key) == 0);
            Node temp = array[hashValue];
            array[hashValue] = null;
            return (V) new Integer(temp.getValue());
        }
    }

    
    /**
     * This method returns the value indexed by the key
     * @pre  There is a mapping indexed by key
     * @post the value returned is the value indexed by key
     * @param key the key to to the value being looked up
     * @return The value indexed by key
     */
    @SuppressWarnings("unchecked")
    @Override
    public V lookup(K key) {
     // finds out where value should be
        int hashValue = function.hash(key, size);
        
        if (array[hashValue] == null){
            System.err.println("not a valid mapping");
            return null;
        }
        
        // if the value is chained
        if (array[hashValue].getNext() != null){
            
            // run through the chain and compare the keys
            Node temp = array[hashValue];

            while (temp.getNext() != null){
                if ((temp.getKey().compareTo(key) == 0)){
                    break;
                }

                temp = temp.getNext();
            }
            // return the value of temp
            return (V) new Integer(temp.getValue());
        
        // if there is no chaining then the key at the hashvalue should always match the looked up key
        }else{
            
            assert (array[hashValue].getKey().compareTo(key) == 0);
            Node temp = array[hashValue];
            array[hashValue] = null;
            return (V) new Integer(temp.getValue());
        }
        
    }

    @Override
    public void printReport() {
        int reportArray[] = new int[size];
        for (int i = 0; i < size; i++){
            
            if (array[i]==null){
                reportArray[i] = 0;
            }
            else{
               
                Node temp = array[i];
                reportArray[i]++;
                while(temp.getNext() != null){
                    temp = temp.getNext();
                    reportArray[i]++;
                }
            }
        }
        
        for (int i = 0; i < reportArray.length; i++) {
            System.err.println(reportArray[i]);
        }
        
        System.out.println("Longest Chain: " + getLongestChain(reportArray));
        System.out.println("AverageChain: " + getAverageChain(reportArray));
        System.out.println("Load Factor: " + getLoadFactor(reportArray));
        System.out.println("Density Factor: " + getDensityFactor(reportArray));
        
        
    }
    
    public int getLongestChain(final int[] reportArray){
        int longestChain;
        
        longestChain = reportArray[0];
        for (int i = 0; i < size; i++){
            if (reportArray[i] > longestChain) {
                longestChain = reportArray[i];
            }
        }
        return longestChain;
    }

    public double getAverageChain(final int[] reportArray){
        double averageChain = 0;
        int numOfChains = 0;
        
        for (int i = 0; i < size; i++){
            
            if(reportArray[i] > 0){
                averageChain += reportArray[i];
                numOfChains++;
            }   
        }
        return averageChain/numOfChains;
        
    }

    public double getLoadFactor(final int[] reportArray){
        double loadFactor = 0;
        
        for (int i = 0; i < size; i++){
            
            if (reportArray[i] > 0) {
                loadFactor++;
            } 
        }
        return loadFactor/size;
    }

    public double getDensityFactor(final int[] reportArray){
        double densityFactor = 0;
        
        for (int i = 0; i < size; i++){
            if (reportArray[i] > 0) {
                densityFactor += reportArray[i];
            } 
        }
        return densityFactor/size;
    }
}
