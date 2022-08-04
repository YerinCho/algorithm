package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q15684 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int H;
    private static boolean[][][] ladder;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        if (canGo(new boolean[N + 1][N + 1][H + 1])) bw.write(0 + "\n");
        else bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        // 1개의 가로선을 추가하는 경우
        boolean[][][] newLadder = new boolean[N + 1][N + 1][H + 1];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= H; j++) {
                if (ladder[i][i + 1][j]) continue;
                if (i > 1 && ladder[i - 1][i][j]) continue;
                if (i < N - 1 && ladder[i + 1][i + 2][j]) continue;
                newLadder[i][i + 1][j] = true;
                newLadder[i + 1][i][j] = true;
                if (canGo(newLadder)) return 1;
                newLadder[i][i + 1][j] = false;
                newLadder[i + 1][i][j] = false;
            }
        }

        // 2개의 가로선을 추가하는 경우
        int N1, H1, N2, H2;
        for (int i = 1; i < (N - 1) * H; i++) {
            H1 = i / (N - 1) + 1;
            N1 = i % (N - 1);
            if (N1 == 0) {
                H1--;
                N1 = N - 1;
            }
            if (ladder[N1][N1 + 1][H1]) continue;
            if (N1 > 1 && ladder[N1 - 1][N1][H1]) continue;
            if (N1 < N - 1 && ladder[N1 + 1][N1 + 2][H1]) continue;
            newLadder[N1][N1 + 1][H1] = true;
            newLadder[N1 + 1][N1][H1] = true;

            for (int j = i + 1; j <= (N - 1) * H; j++) {
                H2 = j / (N - 1) + 1;
                N2 = j % (N - 1);
                if (N2 == 0) {
                    H2--;
                    N2 = N - 1;
                }
                if (ladder[N2][N2 + 1][H2]) continue;
                if (N2 > 1 && (ladder[N2 - 1][N2][H2] || newLadder[N2 - 1][N2][H2])) continue;
                if (N2 < N - 1 && (ladder[N2 + 1][N2 + 2][H2] || newLadder[N2 + 1][N2 + 2][H2])) continue;
                newLadder[N2][N2 + 1][H2] = true;
                newLadder[N2 + 1][N2][H2] = true;
                if (canGo(newLadder)) return 2;
                newLadder[N2][N2 + 1][H2] = false;
                newLadder[N2 + 1][N2][H2] = false;
            }
            newLadder[N1][N1 + 1][H1] = false;
            newLadder[N1 + 1][N1][H1] = false;
        }


        // 3개의 가로선을 추가하는 경우
        int N3, H3;
        for (int i = 1; i < (N - 1) * H - 1; i++) {
            H1 = i / (N - 1) + 1;
            N1 = i % (N - 1);
            if (N1 == 0) {
                H1--;
                N1 = N - 1;
            }
            if (ladder[N1][N1 + 1][H1]) continue;
            if (N1 > 1 && ladder[N1 - 1][N1][H1]) continue;
            if (N1 < N - 1 && ladder[N1 + 1][N1 + 2][H1]) continue;
            newLadder[N1][N1 + 1][H1] = true;
            newLadder[N1 + 1][N1][H1] = true;

            for (int j = i + 1; j < (N - 1) * H; j++) {
                H2 = j / (N - 1) + 1;
                N2 = j % (N - 1);
                if (N2 == 0) {
                    H2--;
                    N2 = N - 1;
                }
                if (ladder[N2][N2 + 1][H2]) continue;
                if (N2 > 1 && (ladder[N2 - 1][N2][H2] || newLadder[N2 - 1][N2][H2])) continue;
                if (N2 < N - 1 && (ladder[N2 + 1][N2 + 2][H2] || newLadder[N2 + 1][N2 + 2][H2])) continue;
                newLadder[N2][N2 + 1][H2] = true;
                newLadder[N2 + 1][N2][H2] = true;

                for (int k = j + 1; k <= (N - 1) * H; k++) {
                    H3 = k / (N - 1) + 1;
                    N3 = k % (N - 1);
                    if (N3 == 0) {
                        H3--;
                        N3 = N - 1;
                    }
                    if (ladder[N3][N3 + 1][H3]) continue;
                    if (N3 > 1 && (ladder[N3 - 1][N3][H3] || newLadder[N3 - 1][N3][H3])) continue;
                    if (N3 < N - 1 && (ladder[N3 + 1][N3 + 2][H3] || newLadder[N3 + 1][N3 + 2][H3])) continue;
                    newLadder[N3][N3 + 1][H3] = true;
                    newLadder[N3 + 1][N3][H3] = true;
                    if (canGo(newLadder)) return 3;
                    newLadder[N3][N3 + 1][H3] = false;
                    newLadder[N3 + 1][N3][H3] = false;
                }
                newLadder[N2][N2 + 1][H2] = false;
                newLadder[N2 + 1][N2][H2] = false;
            }
            newLadder[N1][N1 + 1][H1] = false;
            newLadder[N1 + 1][N1][H1] = false;
        }

        return -1;
    }

    private static boolean canGo(boolean[][][] newLadder) {
        for (int i = 1; i <= N; i++) {
            int curN = i;
            int curH = 1;
            while (curH <= H) {
                if (curN < N && (ladder[curN][curN + 1][curH] || newLadder[curN][curN + 1][curH])) {
                    curN++;
                } else if (curN > 1 && (ladder[curN - 1][curN][curH] || newLadder[curN - 1][curN][curH])) {
                    curN--;
                }
                curH++;

            }
            if (curN != i) return false;
        }
        return true;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[N + 1][N + 1][H + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ladder[b][b + 1][a] = true;
            ladder[b + 1][b][a] = true;
        }
    }
}
