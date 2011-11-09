package edu.csupomona.cs.cs241.prog3;

public class TestDriver {

    public static void main(String args[]) {
        
        KeyValueGenerator kv = new KeyValueGenerator();
        kv.initialize();
        HashFunction hs = new HashFunction(1);
        StringIntHashTable<String, Integer> ah = new StringIntHashTable<String, Integer>(100, hs);
        

        String st;
        int n;
        for(int i = 0; i < 443; i++){
            st = kv.getNextKey();
            n = kv.getNextValue();
            System.err.println(st + " " + n);
            ah.add(st, n);
        }
        
        ah.printReport();
        
    }
    
}
