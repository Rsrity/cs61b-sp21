package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
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
    private int size;
    private int initialSize = 16;
    private double maxLoad = 0.75;
    private int bucketIndex = 0;

    /** Constructors */
    public MyHashMap() {
        size = 0;
        buckets = createTable(initialSize);
    }

    public MyHashMap(int initialSize) {
        size = 0;
        this.initialSize = initialSize;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= maxLoad
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = 0;
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(initialSize);
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
        /* 这里创建一个Collection<Node>类型的数组，这个数组是buckets数组 */
        Collection<Node>[] table = new Collection[tableSize];
        /* 这个数组要通过createBucket()方法初始化，createBucket()方法在不同的类中会重写，
         * 返回不同的数据结构 */
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        size = 0;
        buckets = createTable(16);
    }

    @Override
    public boolean containsKey(K key) {
        Set<K> set = keySet();
        return set.contains(key);
    }

    @Override
    public V get(K key) {
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        double loadFactor = (double) size / initialSize;
        if (loadFactor > maxLoad) {
            resize(initialSize * 2);
        }
        Node newNode = createNode(key, value);
        int position = Math.floorMod(key.hashCode(), initialSize - 1);
        int i = 0;
        for (Collection<Node> bucket : buckets) {
            if (i == position) {
                if (!bucket.isEmpty()) {
                    for (Node node : bucket) {
                        if (node.key.equals(key)) {
                            node.value = value;
                            return;
                        }
                    }
                }
                bucket.add(newNode);
                size++;
            }
            i++;
        }
    }

    private void resize(int newSize) {
        Collection<Node>[] newBuckets = createTable(newSize);
        int i = 0;
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                newBuckets[i].add(node);
            }
            i++;
        }
        buckets = newBuckets;
        initialSize = newSize;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        /* buckets是一个数组，实现了Iterable接口 */
        for (Collection<Node> bucket : buckets) {
            /* Collection也实现了Iterable接口 */
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
