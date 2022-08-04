package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q16768 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int[] dx = new int[]{0, -1, 1, 0};
    public static int[] dy = new int[]{-1, 0, 0, 1};
    public static int N;
    public static int K;
    public static int[][] map;
    public static boolean[][] visited;
    public static boolean deleted;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        while (true) {
            deleted = false;
            visited = new boolean[N][10];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 10; j++) {
                    if (visited[i][j] || map[i][j] == 0) {
                        continue;
                    }
                    bfs(j, i);
                }
            }
            if (deleted) {
                doGravity();
                continue;
            }
            break;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                bw.write(map[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void doGravity() {
        int[][] newMap = new int[N][10];
        int k = N - 1;
        for (int i = 0; i < 10; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    newMap[k][i] = map[j][i];
                    k--;
                }
            }
            k = N - 1;
        }
        map = newMap;
    }

    private static void bfs(int x, int y) {
        Queue<Location> locations = new LinkedList<>();
        locations.add(new Location(x, y));
        Location location;
        List<Location> area = new ArrayList<>();
        while (!locations.isEmpty()) {
            location = locations.remove();
            for (int i = 0; i < 4; i++) {
                int nx = location.x + dx[i];
                int ny = location.y + dy[i];
                // 같은 번호가 아닌경우
                if (isOutOfMap(nx, ny) || visited[ny][nx] || map[location.y][location.x] != map[ny][nx]) {
                    continue;
                }
                // 같은 번호인 경우 탐색
                visited[ny][nx] = true;
                Location l = new Location(nx, ny);
                area.add(l);
                locations.add(l);
            }
        }
        if (area.size() < K) return;

        deleted = true;
        for (Location a : area) {
            map[a.y][a.x] = 0;
        }
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= 10 || y >= N;
    }

    private static void initialize() throws IOException {
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]);
        map = new int[N][10];
        visited = new boolean[N][10];
        String[] inputs = new String[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(inputs[i].substring(j, j + 1));
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
