package edu.csupomona.cs.cs241.prog3;

public interface HashTable<K, V> {

    public void add(K key, V value);

    public V remove(K key);

    public V lookup(K key);

    public void printReport();

}
