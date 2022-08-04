package bj;

import java.io.*;
import java.util.Queue;
import java.util.*;

public class Q2667 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    public static int N;
    public static int[][] map;
    public static boolean[][] visited;
    public static List<Integer> sizes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }

        sizes.sort(Comparator.naturalOrder());
        bw.write(sizes.size() + "\n");
        for (Integer size : sizes) {
            bw.write(size + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void bfs(int i, int j) {
        int size = 1;
        Location location;
        Queue<Location> locations = new LinkedList<>();
        locations.add(new Location(i, j));
        while (!locations.isEmpty()) {
            location = locations.remove();
            for (int k = 0; k < 4; k++) {
                int x = location.x + dx[k];
                int y = location.y + dy[k];
                if (x < 0 || y < 0 || x >= N || y >= N || visited[x][y] || map[x][y] == 0) {
                    continue;
                }
                if (map[x][y] != 0) {
                    visited[x][y] = true;
                    locations.add(new Location(x, y));
                    size++;
                }
            }
        }
        if (size > 1) size--;
        sizes.add(size);
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        List<String> inputs = new ArrayList<>();
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(inputs.get(i).substring(j, j + 1));
            }
        }
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
