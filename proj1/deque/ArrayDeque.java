package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    private class ArrayDequeIterator implements Iterator<Item> {
        private int wizPos;

        public ArrayDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public Item next() {
            return items[wizPos++];
        }
    }

    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        for(int i = size() - 1; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = item;
        size++;
    }

    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size++] = x;
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
        if(isEmpty()){
            return null;
        }
        Item first = items[0];
        for(int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        if(size > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return first;
    }

    public Item removeLast() {
        if(isEmpty()){
            return null;
        }
        Item last = items[size - 1];
        items[size - 1] = null;
        size--;
        if(size > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return last;
    }

    public void printDeque(){
        for(int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    public Item get(int index) {
        if(isEmpty() || index >= size || index < 0) {
            return null;
        }
        return items[index];
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
