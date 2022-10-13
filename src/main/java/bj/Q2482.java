package bj;

import java.io.*;

public class Q2482 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static long[][] dp;
    private static final int M = 1000000003;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve(N, K) + "\n");
        bw.flush();
        bw.close();
    }

    private static long solve(int n, int k) {
        if (dp[n][k] != -1) return dp[n][k];
        if (k == 1) return dp[n][k] = n;
        if (n == 0 || n < 2 * k) return dp[n][k] = 0;

        return dp[n][k] = (solve(n - 2, k - 1) + solve(n - 1, k)) % M;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        dp = new long[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                dp[i + 1][j + 1] = -1;
            }
        }
    }
}
