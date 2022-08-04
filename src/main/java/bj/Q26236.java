package bj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

//1주차 중 문제2
public class Q26236 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int[] dx = new int[]{0, -1, 1, 0};
    public static int[] dy = new int[]{-1, 0, 0, 1};
    public static int N;
    public static int[][] map;
    public static boolean[][] visited;
    public static Location shock;
    public static int size;
    public static int time;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int leftFish = size;
        map[shock.y][shock.x] = 0;
        while (true) {
            int t = bfs();
            if (t == -1) {
                break;
            }
            time += t;
            leftFish--;
            if (leftFish == 0) {
                size++;
                leftFish = size;
            }
        }

        bw.write(time + "\n");
        bw.flush();
        bw.close();
    }

    private static int bfs() {
        visited = new boolean[N][N];
        Queue<Location> locations = new LinkedList<>();
        Location location;
        locations.add(shock);
        visited[shock.y][shock.x] = true;
        int minDistance = -1;
        Location minLocation = null;
        while (!locations.isEmpty()) {
            location = locations.remove();
            for (int i = 0; i < 4; i++) {
                int x = location.x + dx[i];
                int y = location.y + dy[i];
                if (isOutOfMap(x, y) || visited[y][x]) {
                    continue;
                }
                visited[y][x] = true;
                // 물고기가 없을 때, 지나만 갈 수 있을 때
                if (map[y][x] == 0 || map[y][x] == size) {
                    locations.add(new Location(x, y, location.distance + 1));
                    continue;
                }
                // 지나갈 수 없을 때
                if (map[y][x] > size) {
                    continue;
                }
                // 먹을 수 있을때
                if (minDistance == -1) {
                    minDistance = location.distance + 1;
                    minLocation = new Location(x, y, minDistance);
                    continue;
                }
                if (minDistance == location.distance + 1) {
                    if (minLocation.y > y || (minLocation.y == y && minLocation.x > x)) {
                        minLocation = new Location(x, y, minDistance);
                    }
                    continue;
                }
                shock = minLocation;
                shock.distance = 0;
                map[minLocation.y][minLocation.x] = 0;
                return minDistance;
            }
        }
        if (minLocation == null) {
            return -1;
        }
        shock = minLocation;
        shock.distance = 0;
        map[minLocation.y][minLocation.x] = 0;
        return minDistance;
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        size = 2;
        time = 0;
        String[] inputs = new String[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine();
        }
        for (int i = 0; i < N; i++) {
            String[] input = inputs[i].split(" ");
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(input[j]);
                if (n == 9) {
                    shock = new Location(j, i, 0);
                }
                map[i][j] = n;
            }
        }
    }

    static class Location {
        public int x;
        public int y;
        public int distance;

        public Location(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
