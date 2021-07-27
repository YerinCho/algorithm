import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Q2206 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        List<List<String>> graph = new ArrayList<>();

        initialize(br, graph);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph.get(i).get(j));
            }
            System.out.println();
        }

        bw.flush();
        bw.close();
    }

    private static void initialize(BufferedReader br, List<List<String>> g) throws IOException {
        for (int i = 0; i < N; i++) {
            g.add(new ArrayList());
        }
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                g.get(i).add(input[j]);
            }
        }
    }

}
