package programmers;

import java.io.IOException;
import java.util.Queue;
import java.util.*;

// 1주차 하 문제
public class Q1829 {
    public static int[] dx;
    public static int[] dy;
    public static int M;
    public static int N;
    public static boolean[][] visited;
    public static List<Integer> sizes;

    public static void main(String[] args) throws IOException {
        Q1829 solution = new Q1829();
        int m = 6;
        int n = 4;
        int[][] picture = new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(Arrays.toString(solution.solution(m, n, picture)));
    }

    public int[] solution(int m, int n, int[][] picture) {
        sizes = new ArrayList<>();
        M = m;
        N = n;
        dx = new int[]{-1, 0, 1, 0};
        dy = new int[]{0, -1, 0, 1};
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    bfs(i, j, picture);
                }
            }
        }

        int[] answer = new int[2];
        sizes.sort(Comparator.reverseOrder());
        answer[0] = sizes.size();
        answer[1] = sizes.get(0);
        return answer;
    }

    public static void bfs(int i, int j, int[][] picture) {
        int size = 1;
        Queue<Location> locations = new LinkedList<>();
        locations.add(new Location(i, j));
        Location location;
        visited[i][j] = true;
        while (!locations.isEmpty()) {
            location = locations.remove();
            for (int k = 0; k < 4; k++) {
                int x = location.x + dx[k];
                int y = location.y + dy[k];
                if (x < 0 || x >= M || y < 0 || y >= N || visited[x][y] || picture[x][y] == 0) {
                    continue;
                }
                if (picture[x][y] == picture[location.x][location.y]) {
                    size++;
                    locations.add(new Location(x, y));
                    visited[x][y] = true;
                }
            }
        }
        sizes.add(size);
    }

    static class Location {
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
