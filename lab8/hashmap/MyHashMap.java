package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Ruijin Ye
 */
public class MyHashMap<K, V> implements Map61B<K, V> {



    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private double loadFactor;
    private int size = 0;
    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        //Node
        Node node = getNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node getNode(K key) {
        int bucketIndex = hash(key);
        return getNodeHelper(key, bucketIndex);
    }

    private Node getNodeHelper(K key, int index) {
        // for each node in the bucket
        // perform comparison
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }


    private int hash(K key) {
        return hash(key, buckets);
    }
    private int hash(K key, Collection<Node>[] table) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    @Override
    public void clear() {
        buckets = createTable(DEFAULT_INITIAL_SIZE);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void put(K key, V value) {
        //navigate to the corresponding node
        int bucketIndex = hash(key);
        Node node = getNode(key);
        // if node exists simply replace the value
        if (node != null) {
            node.value = value;
            return;
        }
        Node new_node = createNode(key, value);
        size += 1;
        buckets[bucketIndex].add(new_node);
        //resize if workload over the limit
        if (hasReachedMaxLoad()) {
            resize(buckets.length * 2);
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (K key : this) {
            set.add(key);
        }
        return set;
    }

    private boolean hasReachedMaxLoad() {
        return (double)size / buckets.length > DEFAULT_LOAD_FACTOR;
    }

    private void resize(int bucketSize) {
        Collection<Node>[] newBuckets = createTable(bucketSize);
        Iterator<Node> nodeIterator = new MyHashMapNodeIterator();
        while (nodeIterator.hasNext()) {
            Node node = nodeIterator.next();
            int bucketIndex = hash(node.key, newBuckets);
            newBuckets[bucketIndex].add(node);
        }
        buckets = newBuckets;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapKeyIterator();
    }

    private class MyHashMapKeyIterator implements Iterator<K> {
        private final Iterator<Node> nodeIterator = new MyHashMapNodeIterator();

        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        public K next() {
            return nodeIterator.next().key;
        }
    }
    private class MyHashMapNodeIterator implements Iterator<Node> {
        private final Iterator<Collection<Node>> bucketsIterator = Arrays.stream(buckets).iterator();
        private Iterator<Node> currentBucketIterator;
        private int nodesLeft = size;
        public boolean hasNext() {
            return nodesLeft > 0;
        }

        public Node next() {
            if (currentBucketIterator == null || !currentBucketIterator.hasNext()) {
                Collection<Node> currentBucket = bucketsIterator.next();
                while (currentBucket.size() == 0) {
                    currentBucket = bucketsIterator.next();
                }
                currentBucketIterator = currentBucket.iterator();
            }
            nodesLeft -= 1;
            return currentBucketIterator.next();
        }
    }

    @Override
    public V remove(K value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
