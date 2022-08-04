package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//1주차 중 문제2
public class Q2573 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    public static int N;
    public static int M;
    public static int[][] map;
    public static int[][] nextMap;
    public static boolean[][] visited;
    public static boolean done = false;
    public static int year = 0;
    public static int numberOfGraph = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        while (!done) {
            visited = new boolean[N][M];
            melt();
            year++;
            numberOfGraph = countGraph();
            if (numberOfGraph > 1) {
                year--;
                break;
            }
            if (done) {
                year = 0;
                break;
            }
            copyMap();
        }
        bw.write(year + "\n");

        bw.flush();
        bw.close();
    }

    public static void melt() {
        Location location;
        Queue<Location> locations = new LinkedList<>();
        locations.add(new Location(0, 0));
        int count = 0;
        while (!locations.isEmpty()) {
            location = locations.remove();
            for (int k = 0; k < 4; k++) {
                int x = location.x + dx[k];
                int y = location.y + dy[k];
                if (isOutOfMap(x, y)) {
                    continue;
                }
                if (!visited[x][y]) {
                    locations.add(new Location(x, y));
                    visited[x][y] = true;
                }
                if (map[location.x][location.y] > 0 && map[x][y] == 0) {
                    nextMap[location.x][location.y]--;
                    count++;
                }
            }
        }
        if (count == 0) {
            done = true;
        }
    }

    public static int countGraph() {
        visited = new boolean[N][M];
        int numberOfGraph = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    numberOfGraph++;
                    Location location;
                    Queue<Location> locations = new LinkedList<>();
                    locations.add(new Location(i, j));
                    while (!locations.isEmpty()) {
                        location = locations.remove();
                        for (int k = 0; k < 4; k++) {
                            int x = location.x + dx[k];
                            int y = location.y + dy[k];
                            if (isOutOfMap(x, y) || visited[x][y] || map[x][y] == 0) { // 이 부분에서, map전체를 돌 수 있지만, 빙하만 들고 다님으로써 탐색의 횟수를 줄일 수 있음.. 그런 방식도 있다.
                                continue;
                            }
                            locations.add(new Location(x, y));
                            visited[x][y] = true;
                        }
                    }
                }
            }
        }
        return numberOfGraph;
    }

    private static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nextMap[i][j] = Math.max(nextMap[i][j], 0);
                map[i][j] = nextMap[i][j];
            }
        }
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static void initialize() throws IOException {
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);
        List<String> inputs = new ArrayList<>();
        map = new int[N][M];
        nextMap = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            String[] input = inputs.get(i).split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                nextMap[i][j] = map[i][j];
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
