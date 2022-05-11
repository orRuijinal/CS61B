package deque;

import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;


public class ArrayDequeTest {
//    @Test
    /** Testing the Iterator*/
//    public static void main(String[] args) {
//        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
//        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
//
//        for (int i = 0; i < 50; i+=1) {
//            ad.addLast(i);
//            ad2.addLast(i);
//        }
//
//        if(ad.equals(ad2)) {System.out.println("Yes");}
//    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();

        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ad.addLast(randVal);
                lld.addLast(randVal);
                assertEquals(ad.get(ad.size() - 1), lld.get(lld.size() - 1));
            }
            else if (operationNumber == 1) {
                // size
                int size = ad.size();
                int size_lld = lld.size();
                assertEquals(size, size_lld);
            }
            else if (operationNumber == 2 && ad.size() > 0) {
                // getFirst
                int last = ad.get(0);
                int last_lld = lld.get(0);
                assertEquals(last, last_lld);
            }
            else if (operationNumber == 3 && ad.size() > 0) {
                // remove last
                int last = ad.removeLast();
                int last_lld = lld.removeLast();
                assertEquals(last, last_lld);
            }
            else if (operationNumber == 4 && ad.size() > 0) {
                // remove first
                int last = ad.removeFirst();
                int last_lld = lld.removeFirst();
                assertEquals(last, last_lld);
            }
        }
    }







    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addFirst("foo");
        assertEquals(2, lld1.size());
        lld1.addLast("middle");
        assertEquals(3, lld1.size());

        lld1.addLast("back");
        assertEquals(4, lld1.size());

        lld1.addLast("middle");
        lld1.addLast("middle");
        lld1.addLast("middle");
        lld1.addLast("middle");
        lld1.addLast("middle");
        lld1.addLast("middle");
        lld1.addLast("middle");

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertEquals(6, lld1.size());

        lld1.addLast("the last");
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        assertEquals(4, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }


    @Test
    public void getTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        lld1.addFirst("foo");
        lld1.addFirst("far");
        lld1.addFirst("fee");
        lld1.addFirst("faau");
//        for (int i = 0; i < 100; i++) {
//            lld1.addLast("wrong");
//        }

        assertEquals("faau", lld1.get(0));
        assertEquals("fee", lld1.get(1));
        assertEquals("foo", lld1.get(3));
//        assertEquals("faau", lld1.get(0));
//        assertEquals("faau", lld1.get(0));
        System.out.println("Printing out get item: " + lld1.get(0));

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }
    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

//        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
//        for (int i = 0; i < 1000000; i++) {
//            lld1.addLast(i);
//        }

        ArrayDeque<Integer> lld2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            lld2.addLast(i);
        }

//        for (double i = 0; i < 500000; i++) {
//            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
//        }
//
//        // check for the length of array
//        System.out.println(lld1.getUsage());
//
//        for (double i = 999999; i > 500000; i--) {
//            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
//        }
//        System.out.println(lld1.getUsage());
//


    }


}



