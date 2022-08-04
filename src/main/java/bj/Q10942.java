package bj;

import java.io.*;

public class Q10942 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int M;
    private static int N;
    private static int dp[][];
    private static int numbers[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        String[] input;
        int i, j;
        for (int k = 0; k < M; k++) {
            input = br.readLine().split(" ");
            i = Integer.parseInt(input[0]);
            j = Integer.parseInt(input[1]);
            int answer = isPalindrome(i, j) ? 1 : 0;
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean isPalindrome(int i, int j) {
        if (i == j) return true;
        if (i < j && Math.abs(i - j) == 1) {
            dp[i][j] = numbers[i] == numbers[j] ? 1 : 0;
            return dp[i][j] == 1;
        }

        if (dp[i][j] != -1) return dp[i][j] == 1;

        dp[i][j] = isPalindrome(i + 1, j - 1) && numbers[i] == numbers[j] ? 1 : 0;

        return dp[i][j] == 1;
    }


    private static void initialize() throws IOException {
        String[] input;
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(input[i - 1]);
        }
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                dp[i][j] = -1;
            }
        }
    }
}
