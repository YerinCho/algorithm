package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q5898 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static int[][] dp;
    private static ArrayList<Edge>[] tree;
    private static int[] grasses;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        for (int i = 0; i < N; i++) {
            bw.write(solve(i + 1, K) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solve(int i, int k) {
        if (dp[i][k] != -1) {
            return dp[i][k];
        }

        if (k == 0) {
            return dp[i][0] = grasses[i];
        }

        int sum = 0;
        for (int j = 0; j < tree[i].size(); j++) {
            sum += solve(tree[i].get(j).edge, k - 1);
            if (k > 1) sum = sum - solve(i, k - 2);
        }

        if (k > 1) sum += solve(i, k - 2);
        else sum += solve(i, 0);
        return dp[i][k] = sum;
    }


    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a, b;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];
        tree = new ArrayList[N + 1];
        grasses = new int[N + 1];
        for (int i = 0; i < N; i++) {
            tree[i + 1] = new ArrayList<>();
            for (int j = 0; j <= K; j++) {
                dp[i + 1][j] = -1;
            }
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            tree[a].add(new Edge(b));
            tree[b].add(new Edge(a));
        }

        for (int i = 0; i < N; i++) {
            grasses[i + 1] = Integer.parseInt(br.readLine());
        }
    }

    static class Edge {
        int edge;

        public Edge(int edge) {
            this.edge = edge;
        }
    }
}
