package bj;

import java.io.*;

public class Q19951 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;   // 조교 수
    private static int[] heights;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int[] sum = new int[N + 1];
        String[] command;
        for (int i = 0; i < M; i++) {
            command = br.readLine().split(" ");
            sum[Integer.parseInt(command[0])] += Integer.parseInt(command[2]);
            if (Integer.parseInt(command[1]) == N) continue;
            sum[Integer.parseInt(command[1]) + 1] -= Integer.parseInt(command[2]);
        }

        for (int i = 1; i <= N; i++) {
            if (i > 1) sum[i] += sum[i - 1];
            heights[i] += sum[i];
        }

        for (int i = 1; i <= N; i++) {
            bw.write(heights[i] + " ");
        }
        bw.flush();
        bw.close();
    }

    private static void initialize() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        inputs = br.readLine().split(" ");
        heights = new int[N + 1];
        for (int i = 0; i < N; i++) {
            heights[i + 1] = Integer.parseInt(inputs[i]);
        }
    }
}
