package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        Comparator<Integer> comparator = Integer::compare;
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(comparator);

        maxArrayDeque.addLast(3);
        maxArrayDeque.addLast(5);
        maxArrayDeque.addLast(1);
        maxArrayDeque.addLast(4);

        Integer maxElement = maxArrayDeque.max(comparator);
        assertEquals(maxElement, (Integer)5);
    }
}
