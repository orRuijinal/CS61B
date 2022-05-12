package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max(Comparator<T> c) {
        if(isEmpty()) { return null; }
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if(c.compare(get(i), get(maxIndex)) > 0) {
                // x > y return 1
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }

    public T max() {
        if(isEmpty()) {
            return null;
        }
        return max(comparator);
    }

    @Override
    public boolean equals (Object o) {
        return true;
    }

}
