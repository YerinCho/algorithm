package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q1460 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int[][] farm;
    private static int[][] dp;
    private static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        solve();

        bw.write(ans * ans + "\n");
        bw.flush();
        bw.close();
    }

    private static void solve() {
        // 과일은 1개 혹은 2개
        for (int fruit1 = 1; fruit1 <= 7; fruit1++) {
            for (int fruit2 = fruit1; fruit2 <= 7; fruit2++) {
                dp = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (fruit1 == farm[i][j] || fruit2 == farm[i][j]) {
                            dp[i][j] = 1;
                        }
                    }
                }
                for (int i = 1; i < N; i++) {
                    for (int j = 1; j < N; j++) {
                        if (dp[i][j] == 0) continue;
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
        }
    }


    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x, y, l, f;
        farm = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());
            for (int j = x; j < l + x; j++) {
                for (int k = y; k < l + y; k++) {
                    farm[j][k] = f;
                }
            }
        }
    }
}
