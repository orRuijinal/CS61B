package deque;

import javax.swing.*;

public class LinkedListDeque<Item> {
    //** Create a node has data, prev, and next
    private class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item item, Node next, Node prev) {
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

    public void addFirst(Item item) {
        // if empty list: pointing to itself for both next and prev
        // else next point to the new node
        if(isEmpty()) {
            sentinel.prev = sentinel.next = new Node(item, sentinel, sentinel);
        }
        else{
            //find the current first node
            Node first_node = sentinel.next;
            //sentinel.next should point to new Node
            //where NewNode.next point to firstNode, .prev is sentinel
            sentinel.next = new Node(item, first_node, sentinel);
            //FirstNode.prev point to the new firstNode
            first_node.prev = sentinel.next;

        }
        size += 1;
    }

    public void addLast(Item item) {
        // point
        if(isEmpty()) {
            sentinel.next = sentinel.prev = new Node(item, sentinel, sentinel);
        }
        else {
            Node last_node = sentinel.prev; // get the last node
            //newNode.prev point to the new node, and newnode.next point to sentinel
            Node new_node = new Node(item, sentinel, last_node);
            //last_node.next should point to new node
            last_node.next = new_node;
            //sentinel.prev should point to newNode now
            sentinel.prev = new_node;
        }

        //sentinel.prev = new Node(item, sentinel, sentinel.prev);
        size += 1;
    }

    public Item getRecursive(int index) {
        return getRecursiveHelper(index, 0, sentinel.next);
    }
    private Item getRecursiveHelper(int index, int t, Node curr) {
        if (t > index) {
            return null;
        }
        if (t == index) {
            return curr.item;
        }
        else {
            return getRecursiveHelper(index, t+1, curr.next);
        }
    }
    public Item removeLast() {
        Item removed;
        if (isEmpty()) {
            return null;
        }
        // make the second last node as the last Node
        removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed;
    }

    public Item removeFirst() {
        Item removed;
        if (isEmpty()) {
            return null;
        }
        // get the second node and assign it as the first Node
        // size--;
        removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    public Item get(int index) {
        Node iNode = sentinel.next;
        for (int t = 0; t < index; t++) {
            iNode = iNode.next;
        }
        return iNode.item;
    }

    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
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
