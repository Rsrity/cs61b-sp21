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

        while(!deque.isEmpty()){
            assertEquals(deque.removeFirst(), (Integer)7);
            assertEquals(deque.removeFirst(), (Integer)6);
            assertEquals(deque.removeFirst(), (Integer)5);
            assertEquals(deque.removeFirst(), (Integer)4);
            assertEquals(deque.removeFirst(), (Integer)3);
            assertEquals(deque.removeFirst(), (Integer)2);
            assertEquals(deque.removeFirst(), (Integer)1);
            assertEquals(deque.removeFirst(), (Integer)0);
        }
        assertTrue(deque.isEmpty());

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        assertEquals(8, deque.size());
    }
}
