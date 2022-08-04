package bj;

import java.io.*;

public class Q1799 {
    // 못풀었음
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] dx = new int[]{-1, 1, -1, 1};
    private static int[] dy = new int[]{-1, -1, 1, 1};
    private static int N;
    private static int max = -1;
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int a = bishop(0, 0, false);
        max = Math.max(a, max);
        int m = max;
        max = -1;
        int b = bishop(1, 0, true);
        max = Math.max(b, max);
        m += max;
        bw.write(m + "\n");
        bw.flush();
        bw.close();
    }

    // 단순 백트래킹만 사용하면 시간초과가 남
    // 가지치기를 어케 해야하지?
    // 일단 비숍을 놓으면 대각선은 모두 당연하게도 탐색할필요가 없어짐. 어차피 못놓기 때문... 근데
    // 이 경우 서로 잡을 수 없는 비숍 두개로 인해 같은 곳이 탐색할필요가 없어질 수도 잇어서 되돌리는거 구현이 불가능함...
    // 대각선을 뭔가 이용할 수 있을 것 같은데

    private static int bishop(int k, int count, boolean isOdd) {
        // N이홀수면 -> 0, 2, 4 (마지막)
        //         -> 1, 3
        // N이짝수면 -> 0, 2
        //         -> 1, 3 (마지막)
        if (N % 2 == 0) {
            if (isOdd) {
                if (k >= N * N) return count;
            } else {
                if (k >= N * N - 1) return count;
            }
        } else {
            if (isOdd) {
                if (k >= N * N - 1) return count;
            } else {
                if (k >= N * N) return count;
            }
        }
        int x, y, c;
        x = k / N;
        y = k % N;
        c = -1;

        // 비숍을 놓지 못하는 칸이거나, 이미 그 곳에 비숍을 놓은 경우 어차피 놓을 수 업슴..
        if (board[y][x] == 0 || board[y][x] == -1 || visited[y][x]) {
            c = bishop(k + 2, count, isOdd);
            max = Math.max(c, max);
            return c;
        }

        // 비숍을 놓은 경우...
        if (canBishop(x, y)) {
            board[y][x] = -1;
            c = bishop(k + 2, count + 1, isOdd);
            max = Math.max(c, max);
            board[y][x] = 1;
        }

        // 비숍을 놓지 않은 경우
        c = Math.max(c, bishop(k + 2, count, isOdd));
        max = Math.max(c, max);

        return c;
    }

    private static boolean canBishop(int x, int y) {
        // 대각선에 다른 말이 있는 경우 false
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < N; j++) {
                nx = x + dx[i] * j;
                ny = y + dy[i] * j;
                if (isOutOfMap(nx, ny)) break;
                if (board[ny][nx] == -1 || visited[ny][nx]) return false;
            }
        }
        return true;
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x > N - 1 || y > N - 1;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[j][i] = Integer.parseInt(input[j]);
            }
        }
    }
}
