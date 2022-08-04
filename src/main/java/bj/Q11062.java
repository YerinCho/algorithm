package bj;

import java.io.*;

public class Q11062 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int T;
    private static int N;
    private static int dp[][];
    private static int card[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            initialize();
            bw.write(game(0, N - 1, true) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int game(int i, int j, boolean turn) {
        // 최선의 전략으로 임했다 => 현재 상황만 보고 최적의 답을 구하는 것 같아 보이나
        // 현재만 보고 카드를 골랏을 때 그게 최고의 점수가 될 수 없음.
        // 지금 양 끝 값 중 작은 값을 골라도 그 다음에 훨씬 큰 값을 가져올 수 잇음.. 테케에서 보면
        // 1 2 5 2 일 때 근우는 첨에 2를 고를 것 같지만.. 사실 2를 고르면 그담에 명우가 5를 골라버려서 결국 최선의 답이 안나옴
        // 1을 골라서 명우가 5를 못고르게 해야 함 그래야 근우가 5를 고를 수 있음... 그래서 그리디가 아님
        // 사실 저 테케 예시로 안줬으면 그리디로 풀엇다가 틀렸을 거 같음 테케가 친절함
        // 결국 모든 경우를 따져봐야 하는 거임

        if (i > j) {
            return 0;
        }
        // 근우 차례 아니면 지금까지의 최소를 구해야 근우가 최대를 갖는다...
        if (!turn) {
            dp[i][j] = Math.min(game(i, j - 1, true), game(i + 1, j, true));
            return dp[i][j];
        }

        // 갱신되어 잇는 경우
        if (dp[i][j] != -1) return dp[i][j];

        // 안 갱신되엇을 때.. 계산하깅
        dp[i][j] = Math.max(card[i] + game(i + 1, j, false), card[j] + game(i, j - 1, false));
        return dp[i][j];
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] input;
        dp = new int[N][N];
        card = new int[N];
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(input[i]);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
                if (i == j) {
                    dp[i][j] = card[i];
                }
            }
        }
    }
}
