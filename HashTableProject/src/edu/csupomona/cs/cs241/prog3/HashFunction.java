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
                
            case 2:
                return multiplicativeHash(s, size);
                
            case 3:
                return rotateAdditiveHash(s, size);
                
            case 4:
                return rotateMultiplicativeHash(s, size);
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
    
    private int multiplicativeHash(String key, int size) {
        double value = 1;
        
        for(int i = 0; i < key.length(); i++){
            int temp = (int) ((key.charAt(key.length()- 1 - i) - 96) * Math.pow(26, i));
            value *= temp;
        }
        return (int) (value % size);
    }
    
    private int rotateAdditiveHash(String key, int size){
        double value = 0;
        
        for(int i = 0; i < key.length(); i++){
            int temp = (int) ((key.charAt(key.length()- 1 - i) - 96) * Math.pow(26, i));
            
            value += rotate(temp);
            value = rotate(value);
        }
        return (int) (value % size);
    }
    
    private int rotateMultiplicativeHash(String key, int size){
        double value = 1;
        
        for(int i = 0; i < key.length(); i++){
            int temp = (int) ((key.charAt(key.length()- 1 - i) - 96) * Math.pow(26, i));
            
            value *= rotate(temp);
            value = rotate(value);
        }
        return (int) (value % size);
    }
    
    private double rotate(double r){

        for (int j = 0; j < 4; j++) {
            
            double numOfDigits = Math.floor(Math.log10(r));
            numOfDigits = Math.pow(10, numOfDigits);
            double z = Math.floor(r / numOfDigits);
            double lastDigits = (r - z * numOfDigits);
            r = lastDigits * 10 + z;
        }
        return r;
        
        
        
        
        
        
        
        
        
//        String s =""+ r;
//        
//        char arr[] = s.toCharArray();
//        char newArr[] = new char[arr.length];
//        char temp[];
//        
//        for (int k = 0; k < 4; k++) {
//            for (int j = 0; j < arr.length; j++) {
//
//                int z = ((j - 1) % arr.length);
//
//                if (z < 0) {
//                    z += arr.length;
//                }
//                newArr[z] = arr[j];
//            }
//            temp = newArr;
//            newArr = arr;
//            arr = temp;
//        }
//        s = "";
//        for (int j = 0; j < arr.length; j++) {
//            s += arr[j];
//        }
//        return Double.parseDouble(s.toString());
    }
}
