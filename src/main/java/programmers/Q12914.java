package programmers;

public class Q12914 {
    public long[] dp;
    public int N;

    public long solution(int n) {
        N = n;
        dp = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = -1;
        }
        dfs(0);
        return dp[0] % 1234567;
    }

    long dfs(int index) {
        if (dp[index] != -1) return dp[index];
        if (index == N) {
            dp[N] = 1;
            return dp[N];
        }
        long i = 0;
        long j = 0;
        if (index + 1 <= N) {
            i = dfs(index + 1);
        }
        if (index + 2 <= N) {
            j = dfs(index + 2);
        }
        dp[index] = (i + j) % 1234567;
        return dp[index];
    }
}
