package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Q18870 {
    private static int N;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> inputs = new ArrayList<>();
        initialize(inputs);
        List<Integer> deduplicated = new ArrayList<>(new HashSet<>(inputs));

        deduplicated.sort(Comparator.naturalOrder());

        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < deduplicated.size(); i++) {
            result.put(deduplicated.get(i), i);
        }

        for (Integer input : inputs) {
            bw.write(result.get(input) + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void initialize(List<Integer> inputs) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);

        String[] secondLine = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            inputs.add(Integer.parseInt(secondLine[i]));
        }
    }
}
