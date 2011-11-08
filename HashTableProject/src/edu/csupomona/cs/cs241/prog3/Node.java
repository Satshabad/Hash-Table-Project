package edu.csupomona.cs.cs241.prog3;

/**
 * This class holds values for the hash table and allows for chaining.
 * 
 * @author Satshabad
 *
 * @param <V> The type of the value to be held
 */
public class Node<V> {
    
    /**
     * Points to the next node in the chain.
     */
    private Node<V> next;
    
    /**
     * Holds the value of the node
     */
    private V value;
    
    /**
     * Creates a node with the specified value and pointer to next node
     * 
     * @param nodeValue the value to be held by node
     * @param nextNode the next node in the chain
     */
    Node(V nodeValue, Node<V> nextNode){
        value = nodeValue;
        next = nextNode;
    }
    
    /**
     * Get the value of the node
     * 
     * @return the value
     */
    public V getValue() {
        return value;
    }
    
    /**
     * Gets the next node in the chain
     * 
     * @return the next
     */
    public Node<V> getNext() {
        return next;
    }
    
}
