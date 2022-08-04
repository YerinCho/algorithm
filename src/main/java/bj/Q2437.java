package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q2437 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        list.sort(Comparator.naturalOrder());
        int min;
        if (list.get(0) != 1) {
            min = 1;
        } else {
            min = 1;
            for (int i = 1; i < N; i++) {
                int n = list.get(i);
                if (min + 1 < n) {
                    break;
                }
                min += n;
            }
            min++;
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        list = new ArrayList<>();
        for (String value : input) {
            list.add(Integer.parseInt(value));
        }
    }
}
