package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;                    // BST的根节点

    private class BSTNode {
        private K key;                       // 键
        private V value;                     // 值
        private BSTNode left, right, parent; // 左右子树 父节点
        private int N;                       // 以此节点为根的子树的节点个数

        BSTNode(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        BSTNode current = root;
        while (current != null) {
            if (key.equals(current.key)) {
                return true;
            } else if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        BSTNode current = root;
        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            } else if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /* 以x为根插入节点 */
    private BSTNode put(BSTNode x, K key, V value){
        if (x == null) {
            return new BSTNode(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        /* 将要插入的节点的key与x的key作对比，如果小于x的key，插入到左子树中
         *  如果大于x的key，则插入到右子树中，如果相等，则更新x的value */
        if (cmp < 0) {
            x.left = put(x.left, key, value);
            x.left.parent = x;
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
            x.right.parent = x;
        } else {
            x.value = value;
        }
        /* 以x为根节点的节点个数为左子树的节点个数加上右子树节点个数再加上1 */
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key + " ");
        printInOrder(node.right);
    }

    /* 寻找以node为根节点的最小节点 */
    private BSTNode findMin(BSTNode node) {
        BSTNode min = node;
        while (min.left != null) {
             min = min.left;
        }
        return min;
    }

    private BSTNode findNext(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode nextNode;
        /* 如果节点有右子树，那么右子树的最左侧节点就是后继 */
        if (node.right != null) {
            nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        } else {
            /* 如果节点没有右子树，则需要向上查找，找到一个节点，这个节点是其父节点的左子节点。
             * 这个节点的父节点就是后继。 */
            nextNode = node;
            while (nextNode.parent != null) {
                if (nextNode.parent.left == nextNode) {
                    return nextNode.parent;
                }
                nextNode = nextNode.parent;
            }
        }
        return null;
    }

    private class BSTMapIter implements Iterator<K> {
        private BSTNode cur = findMin(root);

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public K next() {
            K ret = cur.key;
            cur = findNext(cur);
            return ret;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        for (K i: this) {
            ret.add(i);
        }
        return ret;
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
