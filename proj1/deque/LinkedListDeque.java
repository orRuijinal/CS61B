package deque;
import java.util.Iterator;


public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    //** Create a node has data, prev, and next
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private Node sentinel;

    //** Creates an empty LinkedListDeque */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, sentinel, sentinel);
    }


//    private boolean contains(T item) {
//        for (int i = 0; i < size; i++) {
//            if (item.equals(sentinel.next.item)) {
//                return true;
//            }
//            sentinel = sentinel.next;
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) { 
            if(other.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node wizNode;
        LinkedListDequeIterator() {
            wizNode = sentinel.next;
        }

        public boolean hasNext() {
            return wizNode.next != sentinel;
        }

        public T next() {
            T returnItem = wizNode.item;
            sentinel = sentinel.next;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public void addFirst(T item) {
        // if empty list: pointing to itself for both next and prev
        // else next point to the new node
        if (isEmpty()) {
            sentinel.prev = sentinel.next = new Node(item, sentinel, sentinel);
        } else {
            //find the current first node
            Node firstNode = sentinel.next;
            //sentinel.next should point to new Node
            //where NewNode.next point to firstNode, .prev is sentinel
            sentinel.next = new Node(item, firstNode, sentinel);
            //FirstNode.prev point to the new firstNode
            firstNode.prev = sentinel.next;

        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        // point
        if (isEmpty()) {
            sentinel.next = sentinel.prev = new Node(item, sentinel, sentinel);
        } else {
            Node lastNode = sentinel.prev; // get the last node
            //newNode.prev point to the new node, and newnode.next point to sentinel
            Node newNode = new Node(item, sentinel, lastNode);
            //last_node.next should point to new node
            lastNode.next = newNode;
            //sentinel.prev should point to newNode now
            sentinel.prev = newNode;
        }

        //sentinel.prev = new Node(item, sentinel, sentinel.prev);
        size += 1;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, 0, sentinel.next);
    }
    private T getRecursiveHelper(int index, int t, Node curr) {
        if (t > index) {
            return null;
        }
        if (t == index) {
            return curr.item;
        } else {
            return getRecursiveHelper(index, t + 1, curr.next);
        }
    }
    public T removeLast() {
        T removed;
        if (isEmpty()) {
            return null;
        }
        // make the second last node as the last Node
        removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }

    public T removeFirst() {
        T removed;
        if (isEmpty()) {
            return null;
        }
        // get the second node and assign it as the first Node
        // size--;
        removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    public T get(int index) {
        Node iNode = sentinel.next;
        for (int t = 0; t < index; t++) {
            iNode = iNode.next;
        }
        return iNode.item;
    }




    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(getRecursive(i) + " ");
            //System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}
