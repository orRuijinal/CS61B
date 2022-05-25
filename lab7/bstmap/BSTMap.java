package bstmap;
import java.util.Iterator;
import java.util.Set;


//** A map should have a key and the corresponding value of the key for each
// data/insertion
// Amy:18
// Ben:7
// Rick:55*/
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        //private int size;
        // initialize the class
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void printInOder() {
        return;
    }
    
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private boolean containsKeyHelper(K key, Node node) {
        //return false if node is null
        if (node == null) {
            return false;
        }
        //lexicographically less than: go left; greater than 0: go right
        if (key.compareTo(node.key) < 0) {
            containsKeyHelper(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            containsKeyHelper(key, node.right);
        }
        return true;
    }

    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            getHelper(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            getHelper(key, node.right);
        }
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        //1. traverse to where the key is
        root = putHelper(key, value, root);
        size += 1;
    }
    private Node putHelper(K key, V value, Node node) {
        if (node == null) {
            //initialize the root if null
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            putHelper(key, value, node.left);
        } else if (key.compareTo(node.key) > 0) {
            putHelper(key, value, node.right);
        }
        node.value = value;
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Invalid Operation for BSTMap");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Invalid Operation for BSTMap");
    }


    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Invalid Operation for BSTMap");
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Invalid Operation for BSTMap");
    }
}
