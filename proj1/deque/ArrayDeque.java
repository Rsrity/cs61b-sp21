package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    private class ArrayDequeIterator implements Iterator<Item> {
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
        public Item next() {
            Item element = items[currentIndex];
            currentIndex = (currentIndex + 1) % items.length;
            count++;
            return element;
        }
    }

    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        head = (head - 1 + items.length) / items.length;
        items[head] = item;
        size++;
    }

    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public Item removeFirst() {
        if(isEmpty()) {
            return null;
        }
        Item first = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size--;
        if(size > 16 && size < items.length / 4){
            resize(items.length / 2);
        }
        return first;
    }

    public Item removeLast() {
        if(isEmpty()){
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        Item last = items[tail];
        items[tail] = null;
        size--;
        if(size > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return last;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % items.length;
            System.out.print(items[actualIndex] + " ");
        }
    }

    public Item get(int index) {
        if(isEmpty() || index >= size || index < 0) {
            return null;
        }
        int actualIndex = (head + index) % items.length;
        return items[actualIndex];
    }

    public Iterator<Item> iterator() {
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
        ArrayDeque<Item> a = (ArrayDeque<Item>) o;
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
