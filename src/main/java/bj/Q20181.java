package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q20181 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static int[] feeds;
    private static long[] dp;
    private static Sum[] sum;

    // data type: int -> long

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static long solve() {
        calculateSum();
        // dp[i] = i번째 위치가 시작지점일 때, 최대로 얻을 수 있는 탈피에너지의 값
        // 따라서, 시작지점이 1일때가 정답
        for (int i = N; i > 0; i--) {
            if (i == N) {
                dp[i] = sum[i].sum - K;
                if (dp[i] < 0) dp[i] = 0;
                continue;
            }
            // 지금 지점에서 먹이를 먹는 경우와 먹지 않는 경우 중 더 큰 값으로 갱신
            int ptr = sum[i].ptr;
            // 지금 지점에서 먹이를 먹는 경우 : 그 먹이를 다 먹고, 그 다음 지점을 시작지점으로 한 값을 더함
            long next = ptr >= N ? 0 : dp[ptr + 1];
            dp[i] = Math.max(dp[i + 1], sum[i].sum - K + next);
        }
        return dp[1];
    }

    private static void calculateSum() {
        // sum[i] => i번째 먹이를 처음으로 먹기 시작했을 때 먹는 애벌레 양 & 먹는게 끝나는 위치 (i 부터 ptr 까지의 애벌레 합임)
        // O(2N) 시간복잡도
        int ptr = N;
        long tmp;
        for (int i = N; i > 0; i--) {
            if (i == N) {
                sum[i] = new Sum(feeds[i], ptr);
                continue;
            }
            tmp = sum[i + 1].sum + feeds[i];
            if (tmp <= K) {
                sum[i] = new Sum(tmp, ptr);
                continue;
            }
            while (tmp > K) {
                if (tmp - feeds[ptr] < K) break;
                tmp -= feeds[ptr];
                ptr--;
            }
            sum[i] = new Sum(tmp, ptr);
        }
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        feeds = new int[N + 1];
        dp = new long[N + 1];
        sum = new Sum[N + 1];
        for (int i = 0; i < N; i++) {
            feeds[i + 1] = Integer.parseInt(st.nextToken());
        }
    }

    static class Sum {
        long sum;
        int ptr;

        public Sum(long sum, int ptr) {
            this.sum = sum;
            this.ptr = ptr;
        }
    }
}
