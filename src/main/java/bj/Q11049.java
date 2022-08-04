package bj;

import java.io.*;

public class Q11049 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static Matrix[] matrices;

    // dp[i][j] 는 i부터 j 번째까지 행렬을 곱햇을 때 최소 곱의 수
    private static int dp[][];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(mulMatrix(0, N - 1) + "\n");
        bw.flush();
        bw.close();
    }

    private static int mulMatrix(int i, int j) {
        // 자기 자신일때는 곱할 게 없으니 횟수가 0임
        if (i == j) return 0;
        // 갱신이 되어잇으면 그대로 리턴
        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];

        // 식 세우는 것 보단... 행렬계산 자체를 별로 해본적이 없어서 이거땜에 점화식 세우는데 좀 헤맷음
        // 고등교육이 이럭게 중요함
        // 중간 끼어잇는 경우의 수들 계산하기
        // 첨엔 중간 경우 생각 못하고 j번째행렬 * dp[i][j-1] 랑 dp[j-1][j] * i번째행렬 만 계산하면 되는건가 싶었는데
        // 그게 아니엇음... 중간 모든 경우를 확인해봐야 함
        // dp[i][i+1] * dp[i+1][j] 부터 dp[i][j-2] * dp[j-1][j] 전부 계산하고 최소값을 반환하면 댐
        int l, r;
        for (int k = i; k < j; k++) {
            l = mulMatrix(i, k);
            r = mulMatrix(k + 1, j);
            dp[i][j] = Math.min(dp[i][j], l + r + matrices[i].x * matrices[k].y * matrices[j].y);
        }
        return dp[i][j];
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] input;
        matrices = new Matrix[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            matrices[i] = new Matrix(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static class Matrix {
        int x;
        int y;

        public Matrix(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int mul() {
            return x * y;
        }
    }
}

// M * N, N * K 일 때...
// M * K 가 결과가 된다.

