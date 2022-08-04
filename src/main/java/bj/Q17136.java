package bj;

import java.io.*;

public class Q17136 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[][] graph;
    private static boolean[][] visited = new boolean[10][10];
    private static int count;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        // 남은 색종이 개수 배열로 넣어놓고... 5가지 색종이 구분하는 저 부분 반복문으로 바꿔서 코드 길이 줄일 수 잇을듯...
        cover(0, 5, 5, 5, 5, 5, 0, count);

        if (min == Integer.MAX_VALUE) min = -1;
        if (count == 0) min = 0;
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    private static int cover(int x, int one, int two, int three, int four, int five, int papers, int left) {
        if (left == 0) return papers;
        if (x >= 100) return -1;
        int canCover;

        if (visited[x % 10][x / 10] || graph[x % 10][x / 10] == 0) {
            canCover = cover(x + 1, one, two, three, four, five, papers, left);
            if (canCover == -1) return -1;
            min = Math.min(canCover, min);
            return canCover;
        }

        if (five > 0 && left >= 25) {
            if (canCover(x / 10, x % 10, 5)) {
                coverPaper(x / 10, x % 10, 5, true);
                canCover = cover(x + 1, one, two, three, four, five - 1, papers + 1, left - 25);
                min = canCover != -1 ? Math.min(canCover, min) : min;
                coverPaper(x / 10, x % 10, 5, false);
            }
        }

        if (four > 0 && left >= 16) {
            if (canCover(x / 10, x % 10, 4)) {
                coverPaper(x / 10, x % 10, 4, true);
                canCover = cover(x + 1, one, two, three, four - 1, five, papers + 1, left - 16);
                min = canCover != -1 ? Math.min(canCover, min) : min;
                coverPaper(x / 10, x % 10, 4, false);
            }
        }

        if (three > 0 && left >= 9) {
            if (canCover(x / 10, x % 10, 3)) {
                coverPaper(x / 10, x % 10, 3, true);
                canCover = cover(x + 1, one, two, three - 1, four, five, papers + 1, left - 9);
                min = canCover != -1 ? Math.min(canCover, min) : min;
                coverPaper(x / 10, x % 10, 3, false);
            }
        }

        if (two > 0 && left >= 4) {
            if (canCover(x / 10, x % 10, 2)) {
                coverPaper(x / 10, x % 10, 2, true);
                canCover = cover(x + 1, one, two - 1, three, four, five, papers + 1, left - 4);
                min = canCover != -1 ? Math.min(canCover, min) : min;
                coverPaper(x / 10, x % 10, 2, false);
            }
        }

        if (one > 0 && left >= 1) {
            if (canCover(x / 10, x % 10, 1)) {
                coverPaper(x / 10, x % 10, 1, true);
                canCover = cover(x + 1, one - 1, two, three, four, five, papers + 1, left - 1);
                min = canCover != -1 ? Math.min(canCover, min) : min;
                coverPaper(x / 10, x % 10, 1, false);
            }
        }

        return -1;
    }

    private static void coverPaper(int x, int y, int c, boolean b) {
        int nx, ny;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                nx = x + i;
                ny = y + j;
                visited[ny][nx] = b;
            }
        }
    }

    private static boolean canCover(int x, int y, int c) {
        int nx, ny;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                nx = x + i;
                ny = y + j;
                if (isOutOfMap(nx, ny) || graph[ny][nx] == 0 || visited[ny][nx]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x > 9 || y > 9;
    }

    private static void initialize() throws IOException {
        graph = new int[10][10];
        int n;
        for (int i = 0; i < 10; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                n = Integer.parseInt(input[j]);
                if (n == 1) count++;
                graph[j][i] = n;
            }
        }
    }
}
