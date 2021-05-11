import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Q1005 {
    private static int T;
    private static int N;
    private static int K;
    private static int W;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        T = Integer.parseInt(firstLine[0]);
        for (int i = 0; i < T; i++) {
            bw.write(calculate() + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int calculate() throws IOException {
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);

        Map<Integer, Integer> times = new HashMap<>();
        List<List<Integer>> graph = new ArrayList<>();

        List<Integer> totalTimes = new ArrayList<>();

        List<Integer> counts = new ArrayList<>();

        initialize(times, graph, totalTimes, counts);
        List<Integer> heads = getHeads(counts);

        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(heads);

        while (counts.get(W) != 0) {
            int node = queue.remove();
            List<Integer> vertexes = graph.get(node);
            if (!vertexes.isEmpty()) {
                vertexes.forEach(e -> {
                    int t = Math.max(totalTimes.get(e), totalTimes.get(node) + times.get(e));
                    totalTimes.set(e, t);
                    counts.set(e, counts.get(e) - 1);
                });
                List<Integer> v = vertexes.stream()
                    .filter(e -> counts.get(e) == 0)
                    .collect(Collectors.toList());
                queue.addAll(v);
            }
        }

        return totalTimes.get(W);
    }

    private static void initialize(Map<Integer, Integer> times, List<List<Integer>> graph, List<Integer> total,
        List<Integer> counts) throws
        IOException {
        String[] line;
        graph.add(new ArrayList<>());
        total.add(0);
        counts.add(0);
        line = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            int t = Integer.parseInt(line[i - 1]);
            graph.add(new ArrayList<>());
            times.put(i, t);
            total.add(t);
            counts.add(0);
        }
        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph.get(from).add(to);
            counts.set(to, counts.get(to) + 1);
        }
        line = br.readLine().split(" ");
        W = Integer.parseInt(line[0]);
    }

    private static List<Integer> getHeads(List<Integer> counts) {
        List<Integer> heads = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (counts.get(i) == 0) {
                heads.add(i);
            }
        }
        return heads;
    }

}
