package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q1655 {
    private static int N;
    private static List<Integer> inputs = new ArrayList<>();
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        List<Integer> middle = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue(N, Comparator.naturalOrder());
        PriorityQueue<Integer> maxHeap = new PriorityQueue(N, Comparator.reverseOrder());
        int input, mid = 0;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            inputs.add(input);
            if (inputs.size() == 1) {
                mid = input;
            } else {
                if (input <= mid) {
                    maxHeap.add(input);
                    if (inputs.size() % 2 == 0) {
                        minHeap.add(mid);
                        mid = maxHeap.remove();
                    }
                } else {
                    minHeap.add(input);
                    if (inputs.size() % 2 != 0) {
                        maxHeap.add(mid);
                        mid = minHeap.remove();
                    }
                }
            }
            middle.add(mid);
        }

        for (Integer integer : middle) {
            bw.write(integer + "\n");
        }

        bw.flush();
        bw.close();
    }
}
