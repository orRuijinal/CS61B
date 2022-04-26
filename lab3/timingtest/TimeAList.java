package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
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
        AList times = new AList();
        AList opCounts = new AList();

        for (int t = 0; t < Ns.size(); t++) {
            AList L = new AList();
            int i = 0;
            Stopwatch sw = new Stopwatch();
            while (i < Ns.get(t)){
                L.addLast(i);
                i += 1;
            }
            double timeInSeconds = sw.elapsedTime();
            /*store the time to times AList*/
            times.addLast(timeInSeconds);
            opCounts.addLast(i);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
