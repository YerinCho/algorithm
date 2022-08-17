package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q1103 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    private static int N;   // 세로
    private static int M;  // 가로
    private static int[][] board;   // => board[세로][가로]
    private static boolean[][] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(game(0, 0) + "\n");
        bw.flush();
        bw.close();
    }

    private static int game(int x, int y) {
        if (dp[y][x] != -1) return dp[y][x];
        int nx, ny, tmp, max = 0;
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            nx = board[y][x] * dx[i] + x;
            ny = board[y][x] * dy[i] + y;
            if (isOutOfMap(nx, ny) || board[ny][nx] == -1) continue;
            if (visited[ny][nx]) return -1;
            tmp = game(nx, ny);
            if (tmp == -1) return -1;
            max = Math.max(tmp, max);
        }
        visited[y][x] = false;
        return dp[y][x] = max + 1;
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= M || y >= N;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        String input;
        char b;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                b = input.charAt(j);
                dp[i][j] = -1;
                if (b == 'H') board[i][j] = -1;
                else board[i][j] = Integer.parseInt(String.valueOf(b));
            }
        }
    }
}
