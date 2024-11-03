package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> buggyDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();

        String Operation = "";

        for (int i = 0; i < 100; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne > 0.5) {
                buggyDeque.addLast(i);
                correctDeque.addLast(i);
                Operation = Operation + "addLast(" + i + ")\n";
            } else {
                if (!correctDeque.isEmpty()) {
                    Integer buggyLast = buggyDeque.removeLast();
                    Integer correctLast = correctDeque.removeLast();
//                    assertEquals(lastOperation + "\n" + thisOperation + "\n"
//                            + "removeLast()\n", correctLast, buggyLast);
//                    assertEquals("removeLast()\n", correctLast, buggyLast);
                    Operation = Operation + "removeLast()\n";
                    assertEquals(Operation, correctLast, buggyLast);

                }
            }
        }
    }
}
