package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    //private double usage;
    private static int resizeFactor = 2;
    // create an empty array deque
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        //usage = getUsage();
    }

    public double getUsage() {
        if (isEmpty()) {
            return 1.0;
        }
        return (double) size / items.length;
    }

    public void printDeque() {
        for(int i = 0; i < items.length; i++){
            if(!(items[i] == null)){
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
        if (isEmpty()) { return null; }

        // resize the array if the usage is under 25%, half the size.
        if (getUsage() < 0.25 && items.length > 16) {
            resize(items.length / 2);
        }

        if (nextLast == 0) {
            removed = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        }
        else {
            removed = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        }
        size -= 1;
        return removed;
    }

    public T removeFirst() {
        T removed;

        if (isEmpty()) { return null; }

        // resize the array if the usage is under 25%, half the size.
        if (getUsage() < 0.25 && items.length > 16) {
            resize(items.length / 2);
        }

        //the first item would be at 0 if nextFirst is == length -1
        if (nextFirst == items.length - 1) {
            removed = items[0];
            items[0] = null;
            nextFirst = 0; // recycle
        }
        else {
            removed = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = nextFirst + 1; // recycle
        }
        size -= 1;
        return removed;
    }

    public void addFirst(T item) {

        if(size == items.length) {
            // make the factor to 2 for now
            resize(items.length * resizeFactor);
        }

        items[nextFirst] = item;
        size += 1;

        // if next of nextFirst is -1, point the to end of the array
        if (nextFirst - 1 == -1) {
            nextFirst = items.length - 1;
        }
        else {
            nextFirst -= 1;
        }
    }

    public void addLast(T item) {
        if(size == items.length) {
            resize(items.length * resizeFactor);
        }

        items[nextLast] = item;
        size += 1;
        // point to the start of the array when nextLast is > items.length
        if (nextLast + 1 >= items.length) {
            nextLast = 0;
        }
        else {
            nextLast += 1;
        }
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public boolean isEmpty() {
        if(size == 0) { return true; }
        return false;
    }

    public int size() {
        return size;
    }


}



