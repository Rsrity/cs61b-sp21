package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testFillUpEmptyFillUpAgain(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        assertEquals(8, deque.size());

        for(int i: deque){
            System.out.println(i);
        }


        assertEquals(deque.removeFirst(), (Integer)7);
        assertEquals(deque.removeFirst(), (Integer)6);
        assertEquals(deque.removeFirst(), (Integer)5);
        assertEquals(deque.removeFirst(), (Integer)4);
        assertEquals(deque.removeFirst(), (Integer)3);
        assertEquals(deque.removeFirst(), (Integer)2);
        assertEquals(deque.removeFirst(), (Integer)1);
        assertEquals(deque.removeFirst(), (Integer)0);

        assertTrue(deque.isEmpty());

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        assertEquals(8, deque.size());
    }

    @Test
    public void testGuitarString(){
        int SR = 44100;
        double CONCERT_A = 440.0;
        int capacity = (int) Math.round(SR / CONCERT_A);
        ArrayDeque<Double> deque = new ArrayDeque<>(capacity);
        /* 用0.0初始化buffer */
        for (int i = 0; i < capacity; i++) {
            deque.addLast(0.0);
        }
        assertEquals(capacity, deque.size());

        capacity = deque.size();
        while(!deque.isEmpty()) {
            deque.removeFirst();
        }

        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            deque.addLast(r);
        }
//        System.out.println(capacity);
//        System.out.println(deque.size());
    }
}
