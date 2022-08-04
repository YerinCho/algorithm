package bj;

import java.io.*;

public class Q2662 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N; // 투자금액
    private static int M; // 기업 수
    private static int[][] investment;
    private static int[][] dp;
    private static int[][] companyTrace;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        dp[N][M] = invest(N, M);

        bw.write(dp[N][M] + "\n");

        StringBuilder sb = new StringBuilder();
        for (int i = M; i > 0; i--) {
            sb.insert(0, companyTrace[N][i] + " ");
            N -= companyTrace[N][i];
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int invest(int money, int enterprise) {
        if (dp[money][enterprise] != -1) {
            return dp[money][enterprise];
        }

        if (enterprise == 0) {
            return dp[money][enterprise] = 0;
        }

        if (money == 0) {
            return dp[money][enterprise] = 0;
        }

        int tmp = invest(money, enterprise - 1);
        for (int i = 0; i < money; i++) {
            if (tmp < invest(i, enterprise - 1) + investment[money - i][enterprise]) {
                tmp = invest(i, enterprise - 1) + investment[money - i][enterprise];
                companyTrace[money][enterprise] = money - i;
            }
        }

        return dp[money][enterprise] = tmp;
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        investment = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        companyTrace = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                investment[Integer.parseInt(input[0])][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = -1;
            }
        }
    }
}
