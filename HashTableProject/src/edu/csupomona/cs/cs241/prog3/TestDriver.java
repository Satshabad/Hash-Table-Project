package edu.csupomona.cs.cs241.prog3;

public class TestDriver {

    public static void main(String args[]) {
        
        KeyValueGenerator kv = new KeyValueGenerator();
        kv.initialize();
        System.err.println(kv.getNextValue());
        
        
//        char c = 'z';
//        c = (char) (((c + 1) % 123));
//            if (c == 0){
//                c = 97;
//            }
//        System.err.println(c);
        
    }
    
}
