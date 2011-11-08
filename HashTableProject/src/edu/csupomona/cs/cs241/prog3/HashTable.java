package edu.csupomona.cs.cs241.prog3;

/**
 * This interface lays out what each hashtable will look like
 * 
 * @author Satshabad
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface HashTable<K, V> {

    /**
     * Adds a value with the specified key to the table. 
     * If the mapping already exists, the table remains unchanged.
     * 
     * @pre true
     * @post the table has a mapping for the key to the value 
     * @param key
     * @param value
     */
    public void add(K key, V value);

    
    /**
     * This method removes the mapping from the key to the value and returns the value.
     * 
     * @pre There is a mapping indexed by the key
     * @post The value indexed by key and the mapping no longer exist
     * @param key the key to the value to be removed
     * @return the value indexed by key
     */
    public V remove(K key);

    /**
     * This method returns the value indexed by the key
     * @pre  There is a mapping indexed by key
     * @post the value returned is the value indexed by key
     * @param key the key to to the value being looked up
     * @return The value indexed by key
     */
    public V lookup(K key);

    public void printReport();

}
