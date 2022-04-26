package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // Create an SLList
        SLList SL = new SLList();
        AList times = new AList();
        AList opCount = new AList();

        AList<Integer> Ns = new AList() {{
            addLast(1000);
            addLast(2000);
            addLast(4000);
            addLast(8000);
            addLast(16000);
            addLast(32000);
            addLast(64000);
            addLast(128000);
        }};
        int M = 10000;
        // Add N item to the SLList
        for (int n = 0; n < Ns.size(); n++) {
            int i = 0;
            int j = 0;
            while (i < Ns.get(n)) {
                SL.addLast(i);
                i += 1;
            }
            // start the timer
            Stopwatch sw = new Stopwatch();
            // Perform M getLast operations on SLList
            while (j < M) {
                SL.getLast();
                j += 1;
            }
            double timeInSeconds = sw.elapsedTime();

            // store the time and # Ops
            times.addLast(timeInSeconds);
            opCount.addLast(M);
        }
        printTimingTable(Ns, times, opCount);
    }
}
