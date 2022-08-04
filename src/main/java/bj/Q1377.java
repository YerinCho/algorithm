package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q1377 {
    private static int N;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> inputs = new ArrayList<>();
        initialize(inputs);
        int result = 0;
        List<Integer> sorted = inputs.stream()
            .sorted()
            .collect(Collectors.toList());
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            counts.put(sorted.get(i), i);
        }
        for (int i = 0; i < N; i++) {
            int n = counts.get(inputs.get(i));
            int difference = i - n;
            result = Math.max(result, difference);
        }

        bw.write(result + 1);

        bw.flush();
        bw.close();
    }

    private static void initialize(List<Integer> inputs) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);

        for (int i = 0; i < N; i++) {
            inputs.add(Integer.parseInt(br.readLine()));
        }
    }
}
