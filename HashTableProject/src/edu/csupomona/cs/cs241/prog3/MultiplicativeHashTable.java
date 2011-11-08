package edu.csupomona.cs.cs241.prog3;

public class MultiplicativeHashTable<K,V> implements HashTable<K, V> {

    private Node<V> array[];
    
    public MultiplicativeHashTable(int size) {
        array = new (Node<V>[]) Object[size];
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

}
