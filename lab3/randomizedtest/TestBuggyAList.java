package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeMove(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        /* 向correct和buggy中添加4，5，6 */
        for(int i = 4;i < 7;i++){
            correct.addLast(i);
            buggy.addLast(i);
        }

        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
//                System.out.println("L.addLast(" + randVal + ")");

                B.addLast(randVal);
//                System.out.println("B.addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // getLast
                if(L.size() > 0){
                    int Llast = L.getLast();
//                    System.out.println("getLast(" + Llast + ")");

                    int Blast = B.getLast();
//                    System.out.println("getLast(" + Blast + ")");
                    assertEquals(Llast, Blast);
                }
            } else if (operationNumber == 2) {
                // removeLast
                if(L.size() > 0){
                    int Llast = L.removeLast();
//                    System.out.println("removeLast(" + Llast + ")");

                    int Blast = B.removeLast();
//                    System.out.println("removeLast(" + Blast + ")");
                    assertEquals(Llast, Blast);
                }
            }
        }

    }
}
