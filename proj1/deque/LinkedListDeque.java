package deque;

import java.util.Deque;
import java.util.Iterator;

public class LinkedListDeque<Item> implements Iterable<Item> {
    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item item) {
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

    private class LinkedListDequeIterator implements Iterator<Item> {
        public Node current;

        public LinkedListDequeIterator() {
            current = head.next;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public void addFirst(Item item) {
        Node newh = new Node(item);
        newh.next = head.next;
        newh.prev = head;
        if(isEmpty()){
            tail = newh;
        } else {
            head.next.prev = newh;
        }
        head.next = newh;
        size++;
    }

    public void addLast(Item item) {
        Node newt = new Node(item);
        newt.next = null;
        newt.prev = tail;
        tail.next = newt;
        tail = newt;
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        Node p = head.next;
        while(p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public Item removeFirst() {
        if(isEmpty()) {
            return null;
        }
        Node p = head.next;
        head.next = p.next;
        if(head.next != null) {
            p.next.prev = head;
        }else {
            tail = head;
        }
        size--;
        return p.item;
    }

    public Item removeLast() {
        if(isEmpty()) {
            return null;
        }
        Node p = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return p.item;
    }

    public Item get(int index){
        if(isEmpty()) {
            return null;
        }
        int i = 0;
        Node p = head.next;
        while(p != null) {
            if(i == index){
                return p.item;
            }
            p = p.next;
            i++;
        }
        return null;
    }

    public Item getRecursive(int index){
        if(isEmpty() || index < 0 || index >= size()) {
            return null;
        }
        return getNodeRecursive(head.next, index + 1).item;
    }

    public Node getNodeRecursive(Node current, int index){
        if(index == 0) {
            return current;
        } else {
            return getNodeRecursive(current.next, index - 1);
        }
    }

    public Iterator<Item> iterator(){
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(!(o instanceof LinkedListDeque)){
            return false;
        }
        LinkedListDeque<Item> a = (LinkedListDeque<Item>) o;
        if(size() != a.size()){
            return false;
        }
        Node current = head.next;
        Node acurrent = a.head.next;
        while(current != null) {
            if(!current.item.equals(acurrent.item)){
                return false;
            }
            current = current.next;
            acurrent = acurrent.next;
        }
        return true;
    }
}
