package edu.csupomona.cs.cs241.prog3;

public class TestDriver {

    public static void main(String args[]) {
        
        KeyValueGenerator kv = new KeyValueGenerator();
        kv.initialize();
        AdditiveHashTable<String, Integer> ah = new AdditiveHashTable<String, Integer>(100);
        

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
