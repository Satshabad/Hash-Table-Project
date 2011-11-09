package edu.csupomona.cs.cs241.prog3;

/**
 * This class holds values for the hash table and allows for chaining.
 * 
 * @author Satshabad
 *
 */
public class Node {
    
    /**
     * Points to the next node in the chain.
     */
    private Node next;
    
    /**
     * Holds the value of the node
     */
    private int value;

    /**
     * Holds the key to the value
     */
    private String key;
    
    /**
     * Creates a node with the specified value and pointer to next node
     * 
     * @param nodeValue the value to be held by node
     * @param nextNode the next node in the chain
     */
    Node(int nodeValue, Node nextNode, String theKey){
        value = nodeValue;
        next = nextNode;
        key = theKey;
    }
    
    /**
     * Get the value of the node
     * 
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Gets the next node in the chain
     * 
     * @return the next
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the pointer to the next node to the passed node
     * 
     * @param n the node that becomes the next one in the chain
     */
    public void setNext(Node n) {
        next = n;
    }

    /**
     * gets the key associated with the value
     * 
     * @return the String associated with the value
     */
    public String getKey() {
        return key;
    }
    
}
