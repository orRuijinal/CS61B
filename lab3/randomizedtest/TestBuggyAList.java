package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import javax.sound.midi.SysexMessage;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing correct = new AListNoResizing();
        BuggyAList wrong = new BuggyAList();

        correct.addLast(4);
        wrong.addLast(4);
        correct.addLast(5);
        wrong.addLast(5);
        correct.addLast(6);
        wrong.addLast(6);
        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());

    }
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> Bug = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                Bug.addLast(randVal);
                assertEquals(L.getLast(), Bug.getLast());
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size_bug = Bug.size();
                assertEquals(size, size_bug);
            }
              else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                int last = L.getLast();
                int last_bug = Bug.getLast();
                assertEquals(last, last_bug);
            } else if (operationNumber == 3 && L.size() > 0) {
                // removeLast
                int removed = L.removeLast();
                int removed_bug = Bug.removeLast();
                assertEquals(removed, removed_bug);
            }
              //assertEquals(L.size(), Bug.size());
              //assertEquals(L.removeLast(), Bug.removeLast());
              //assertEquals(L.getLast(), Bug.getLast());
        }
    }
}


