package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

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
        ArrayDeque<Double> deque = new ArrayDeque<>();
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

    @Test
    public void testFillUpDelete() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }

        // Remove all but the last element
        while (deque.size() > 1) {
            deque.removeFirst();
        }

        // Measure memory usage
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long used = heapMemoryUsage.getUsed();
        long max = heapMemoryUsage.getMax();

        System.out.println("Heap Memory Usage: " + used + " bytes (used), " + max + " bytes (max)");
    }

    @Test
    public void testMemoryUsage() {
        int[] testSizes = {8, 64}; // 测试用例的大小
        for (int size : testSizes) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            // 插入元素
            for (int i = 0; i < size; i++) {
                deque.addLast(i);
            }
//            int initialCapacity = deque.capacity; // 记录初始容量

            // 删除所有元素，只保留一个
            for (int i = 0; i < size - 1; i++) {
                deque.removeFirst();
            }

//            int finalCapacity = deque.capacity; // 记录最终容量
//            // 检查容量是否合理
//            if (finalCapacity > initialCapacity / 2) {
//                System.out.println("Test Failed for size " + size + ": Initial Capacity = " + initialCapacity + ", Final Capacity = " + finalCapacity);
//            } else {
//                System.out.println("Test Passed for size " + size + ": Initial Capacity = " + initialCapacity + ", Final Capacity = " + finalCapacity);
//            }
        }
    }

}
