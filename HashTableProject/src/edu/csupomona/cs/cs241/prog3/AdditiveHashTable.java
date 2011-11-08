package edu.csupomona.cs.cs241.prog3;

public class AdditiveHashTable<K,V> implements HashTable<K, V>{

    private Node<V> array[];
    
    @SuppressWarnings("unchecked")
    public AdditiveHashTable(int size) {
        Object arr = new Object[size];
        array = (Node<V>[]) arr;
    }
    
    @Override
    public void add(K key, V value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V lookup(K key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void printReport() {
        // TODO Auto-generated method stub
        
    }

    private int hash(K key){
        return 0;
        
    }
}
