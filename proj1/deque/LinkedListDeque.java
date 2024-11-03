package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque() {
        head = new Node(null);
        tail = head;
        size = 0;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node current;

        LinkedListDequeIterator() {
            current = head.next;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public void addFirst(T item) {
        Node newh = new Node(item);
        newh.next = head.next;
        newh.prev = head;
        if (isEmpty()) {
            tail = newh;
        } else {
            head.next.prev = newh;
        }
        head.next = newh;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newt = new Node(item);
        newt.next = null;
        newt.prev = tail;
        tail.next = newt;
        tail = newt;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = head.next;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node p = head.next;
        head.next = p.next;
        if (head.next != null) {
            p.next.prev = head;
        } else {
            tail = head;
        }
        size--;
        return p.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node p = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return p.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        int i = 0;
        Node p = head.next;
        while (p != null) {
            if (i == index) {
                return p.item;
            }
            p = p.next;
            i++;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            return null;
        }
        return getNodeRecursive(head.next, index).item;
    }

    private Node getNodeRecursive(Node current, int index) {
        if (index == 0) {
            return current;
        } else {
            return getNodeRecursive(current.next, index - 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> a = (Deque<T>) o;
        if (size() != a.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!(get(i).equals(a.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
