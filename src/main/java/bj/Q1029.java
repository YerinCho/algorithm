package bj;

import java.io.*;

public class Q1029 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int[][] prices;
    private static int[][][] dp; // 상태, 파는사람, 가격 일 때 = 최대 소유자수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        int answer = solve(1, 1, 0) + 1;
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve(int state, int seller, int price) {
        if (dp[state][seller][price] != 0) return dp[state][seller][price];

        int tmp = 0;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) != 0) {
                continue;    // 이미 삿던 내역이 있음
            }
            if (prices[seller][i + 1] < price) continue; // 돈모자름
            tmp = Math.max(tmp, 1 + solve(state | (1 << i), i + 1, prices[seller][i + 1]));
        }

        return dp[state][seller][price] = tmp;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        prices = new int[N + 1][N + 1];
        dp = new int[1 << N][N + 1][10];
        String input;
        for (int i = 1; i < N + 1; i++) {
            input = br.readLine();
            for (int j = 1; j < N + 1; j++) {
                prices[i][j] = Integer.parseInt(String.valueOf(input.charAt(j - 1)));
            }
        }
    }

    static class State {
        int state;
        int pre;

        public State(int state, int pre) {
            this.state = state;
            this.pre = pre;
        }
    }
}

