package bstmap;

import org.junit.Test;

public class myBSTMapTest {
    @Test
    public void IteratorTest() {
        //        4
        //       / \
        //      2   6
        //     / \ / \
        //    1  3 5  7
        BSTMap<Integer, Integer> test = new BSTMap<>();
        test.put(4, 4);
        test.put(2, 2);
        test.put(6, 6);
        test.put(5, 5);
        test.put(1, 1);
        test.put(3, 3);
        test.put(7, 7);

        test.printInOrder();
        System.out.println();
        for(Integer i: test) {
            System.out.println(i);
        }
    }
}