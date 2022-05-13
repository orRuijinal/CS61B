package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    //private double usage;
    private static int resizeFactor = 2;
    // create an empty array deque
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        //usage = getUsage();
    }

    private double getUsage() {
        if (isEmpty()) {
            return 1.0;
        }
        return (double) size / items.length;
    }

    private boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(items[((i + nextFirst) + 1) % items.length])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (T item : this) {
            if (!other.contains(item)) {
                return false;
            }
        }
        return true;
    }


    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = items[((wizPos + nextFirst) + 1) % items.length];
            wizPos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            if (!(items[i] == null)) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    public T get(int i) {
        if (i < 0 || i > size) {
            return null;
        }
        return items[((i + nextFirst) + 1) % items.length];
    }

    public T removeLast() {
        T removed;
        if (isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            removed = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            removed = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        }
        size -= 1;
        // resize the array if the usage is under 25%, half the size.
        if (getUsage() < 0.25 && items.length > 16) {
            resize(items.length / 2);
        }
        return removed;
    }

    public T removeFirst() {
        T removed;

        if (isEmpty()) {
            return null;
        }
        //point to the first item
        nextFirst = (nextFirst + 1) % items.length;
        removed = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        // resize the array if the usage is under 25%, half the size.
        if (getUsage() < 0.25 && items.length > 16) {
            resize(items.length / 2);
        }
        return removed;
    }

    public void addFirst(T item) {

        if (size == items.length) {
            // make the factor to 2 for now
            resize(items.length * resizeFactor);
        }

        items[nextFirst] = item;
        size += 1;

        // if next of nextFirst is -1, point the to end of the array
        if (nextFirst - 1 == -1) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * resizeFactor);
        }

        items[nextLast] = item;
        size += 1;
        // point to the start of the array when nextLast is > items.length
        if (nextLast + 1 >= items.length) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        //copy to the new array, starting from index 1
        // the range is the right of nextFirst until the left of nextLast
        for (int i = 1; i <= size; i++) {
            a[i] = items[(++nextFirst) % items.length];
        }
        //System.arraycopy(items, (nextFirst+1) % items.length, a, 1, size);

        items = a;
        nextFirst = 0;
        nextLast = size + 1;
    }


    public int size() {
        return size;
    }


}



