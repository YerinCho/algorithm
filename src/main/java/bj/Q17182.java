package bj;

import java.io.*;

public class Q17182 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static int[][] costs;
    private static int visited = 0;
    private static int[][] min;
    private static int max;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        // 각 구간 - 구간 사이 최소 경로를 파악한다. => 플로이드 와샬, 10^3 시간복잡도라 충분히 가능
        // 구간사이 최소값 구하기
        getMinCosts();

        visited = visited | 1 << K;
        int answer = dfs(K, visited);
//
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static int dfs(int num, int visited) {
        int minValue = Integer.MAX_VALUE;
        if (visited == max) return 0;
        if (min[num][visited] != Integer.MAX_VALUE) return min[num][visited];
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0 || i == num) continue;
            int newVisited = visited | 1 << i;
            minValue = Math.min(dfs(i, newVisited) + costs[num][i], minValue);
        }
        min[num][visited] = minValue;
        return min[num][visited];
    }

    private static void getMinCosts() {
        // 플로이드 와샬 알고리즘
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (costs[j][k] > costs[j][i] + costs[i][k]) {
                        costs[j][k] = costs[j][i] + costs[i][k];
                    }
                }
            }
        }
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        costs = new int[N][N];
        max = (1 << N) - 1;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(input[j]);
            }
        }
        min = new int[N][max];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < max; j++) {
                min[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}
