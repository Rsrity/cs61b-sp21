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
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        /* 初始化Ns，存放测试的操作数 */
        for (int i = 0; i < 8; i++) {
            Ns.addLast(1000 * (1 << i));
        }

        for(int i = 0;i < Ns.size();i++){
            /* 用来测试的SLList */
            SLList<Integer> list = new SLList<>();
            /* 向SLList中添加N个元素，以便后续测试getLast()方法 */
            for(int j = 0;j < Ns.get(i);j++){
                list.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            /* 执行N次getLast()方法 */
            for (int j = 0; j < Ns.get(i); j++) {
                list.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            opCounts.addLast(Ns.get(i));
        }

        printTimingTable(Ns, times, opCounts);
    }

}
