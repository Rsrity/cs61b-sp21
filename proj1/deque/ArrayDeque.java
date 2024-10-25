package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        head = 0;
        tail = size - 1;
    }

    public ArrayDeque(int capacity) {
        this.items = (T[]) new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = size - 1;
        this.capacity = capacity;
    }

    public int Capacity() {
        return capacity;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int count;
        private int currentIndex;

        public ArrayDequeIterator() {
            count = 0;
            currentIndex = head;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T element = items[currentIndex];
            currentIndex = (currentIndex + 1) % items.length;
            count++;
            return element;
        }
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        tail = (tail + 1) % items.length;
        items[tail] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    public void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];

        for (int i = 0; i < size(); i++) {
            int oldIndex = (head + i) % items.length;
            a[i] = items[oldIndex];
        }
        items = a;
        head = 0;
        tail = size - 1;
        capacity = newCapacity;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        T first = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size--;
        if(size > 16 && size < items.length / 4){
            resize(items.length / 2);
        }
        return first;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        T last = items[tail];
        items[tail] = null;
        tail = (tail - 1 + items.length) % items.length;
        size--;
        if(size > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return last;
    }

    @Override
    public void printDeque(){
        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % items.length;
            System.out.print(items[actualIndex] + " ");
        }
    }

    @Override
    public T get(int index) {
        if(isEmpty() || index >= size || index < 0) {
            return null;
        }
        int actualIndex = (head + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof ArrayDeque)){
            return false;
        }
        ArrayDeque<T> a = (ArrayDeque<T>) o;
        if(size() != a.size()){
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if(!get(i).equals(a.get(i))){
                return false;
            }
        }
        return true;
    }
}
