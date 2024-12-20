package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;
    
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxElement = null;
        for (T item: this) {
            if (maxElement == null || c.compare(maxElement, item) < 0) {
                maxElement = item;
            }
        }
        return maxElement;
    }
}
