package edu.csupomona.cs.cs241.prog3;

/**
 * @author Satshabad
 *
 */
public class HashFunction {

    private int hashState;
    
    public HashFunction(int hashFunction) {
        hashState = hashFunction;
    }
    
    
    /**
     * This method takes a key and returns a hash value based on additive hashing.
     * @pre the String is non null
     * @post the hash value will be valid for the key and will be between 0 and size
     * @param key the key to be hashed
     * @return the hash value of that key
     */
    public int hash(String s, int size){
         
        switch (hashState)
        {
            case 1:
                return additiveHash(s, size);
                
            default:
                return 0;
            
        }
            
        
        

        
    }

    private int additiveHash(String key, int size) {
        double value = 0;
        
        for(int i = 0; i < key.length(); i++){
            int temp = (int) ((key.charAt(key.length()- 1 - i) - 96) * Math.pow(26, i));
            value += temp;
        }
        return (int) (value % size);
    }
}
