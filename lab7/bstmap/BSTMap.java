package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K, V> implements Map61B<K, V> {

    private BSTNode root;                  // BST的根节点

    private class BSTNode {
        private K key;                     // 键
        private V value;                   // 值
        private BSTNode left, right;       // 左右子树
        private int N;                     // 以此节点为根的子树的节点个数

        BSTNode(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    @Override
    public void put(Object key, Object value) {

    }

    public void printInOrder() {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
