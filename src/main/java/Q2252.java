import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Q2252 {

    private static int studentCount;
    private static int compareCount;
    private static final ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        studentCount = Integer.parseInt(firstLine[0]);
        compareCount = Integer.parseInt(firstLine[1]);

        ArrayList<Integer> counts = new ArrayList<>();

        initialize(br, counts);

        List<Integer> heads = getHeads(counts);
        List<Integer> result = sort(counts, heads);

        for (Integer i : result) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void initialize(BufferedReader br, ArrayList<Integer> counts) throws IOException {
        for (int i = 0; i < studentCount + 1; i++) {
            graph.add(new ArrayList<>());
            counts.add(0);
        }

        int a, b;
        String[] inputs;

        for (int i = 0; i < compareCount; i++) {
            inputs = br.readLine().split(" ");
            a = Integer.parseInt(inputs[0]);
            b = Integer.parseInt(inputs[1]);
            graph.get(a).add(b);
            counts.set(b, counts.get(b) + 1);
        }
    }

    private static List<Integer> getHeads(ArrayList<Integer> counts) {
        List<Integer> heads = new ArrayList<>();

        for (int i = 1; i < studentCount + 1; i++) {
            if (counts.get(i) == 0) {
                heads.add(i);
            }
        }
        return heads;
    }

    private static List<Integer> sort(ArrayList<Integer> counts, List<Integer> heads) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.addAll(heads);

        while (result.size() < studentCount) {
            int node = queue.remove();
            result.add(node);
            List<Integer> vertexes = graph.get(node);
            if (!vertexes.isEmpty()) {
                vertexes.forEach(e -> counts.set(e, counts.get(e) - 1));
                List<Integer> v = vertexes.stream()
                    .filter(e -> counts.get(e) == 0)
                    .collect(Collectors.toList());
                queue.addAll(v);
            }
        }
        return result;
    }
}
