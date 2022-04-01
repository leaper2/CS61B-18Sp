package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root; /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }

        if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        }
        if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        // the remained case is that key==p.key, so return the value directly
        return p.value;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    /*
     * It's tempting to call the get method firstly to prob if the key exist already
     * or not. However, it is in vain due to we still has to traverse the tree again
     * to append the new node or update the exist value.
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        }
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);

        }
        if (key.compareTo(p.key) == 0) {
            p.value = value;
        }
        // the remained unchecked case is key==p.key, so just assign the value and
        // return
        // p.value = value;

        /*
         * Obviously, we have to add and link a new node to the BST in the absence of
         * its key.
         * To avoid the "arms length base cases" and the use of a extra pointer
         * to the previous node, we conceive the following mechnism:
         * Normally:
         * 1.return the passed argument as the returned value;
         * 2.assign the value of the variable to the same variable, weird but feasible
         * and acceptable. Note: don't return earlier in these normal case. If you
         * return the p.left or p.right, then it will change the value of p.left to
         * something like p.left.left.
         * By contrast:
         * 3.If the key is not exist, we create the node and return its address as the
         * return value and return earlier.
         */
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    /*
     * Wrap method to assign the value to root.
     * The variable root is distinguishable and special, it holds the entry address
     * of the entire BST. There is no reason has to avoid messing up its assighment
     * with the above recursion mechnism.
     * 1. The public charateristic of the put method dictate its signature
     * to have the form put(K key, V value).
     * 2. The above recursion mechnism determine the recurrent method to have
     * a form of returing the passed address.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value. Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
