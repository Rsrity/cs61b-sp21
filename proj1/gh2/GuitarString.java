package gh2;

import deque.ArrayDeque;
import deque.Deque;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */

    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        /* 容量为采样率 / 频率 */
        int capacity = (int) Math.round(SR / frequency);
        /* 创建buffer */
        buffer = new ArrayDeque<>();
        /* 用0.0初始化buffer */
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int capacity = buffer.size();
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }

        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        Double first = buffer.removeFirst();
        Double second = buffer.get(0);
        Double newDouble = (first + second) / 2 * DECAY;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
