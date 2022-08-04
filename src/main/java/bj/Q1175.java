package bj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1175 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    // 하 상 우 좌
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};
    private static int N; // 세로
    private static int M; // 가로
    /*
     * S : 민식이가 있는 곳. 지나갈 수 있는 곳
     * C : 목적지, 2곳. 지나갈 수 있는 곳
     * . : 지나갈 수 있는 곳
     * # : 지나갈 수 없는 곳
     * */
    private static Location S;
    private static Location C1;
    private static Location C2;
    private static char[][] map; // map[M][N]
    /*
     * 0 : ↓
     * 1 : ↑
     * 2 : →
     * 3 : ←
     * */
    private static boolean[][][] visited; // 어디방향에서 왔는지 정보 + 위치(x, y)정보

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        Queue<Location> queue = new LinkedList<>();
        queue.add(S);
        Location location;
        int nx, ny;
        int answer = -1;
        while (!queue.isEmpty()) {
            location = queue.remove();
            if (map[location.y][location.x] == 'C') {
                if (C1.x == location.x && C1.y == location.y && !location.c1) {
                    location.c1 = true;
                    int tmp = solve2(location);
                    if (tmp != -1) {
                        if (answer == -1) answer = tmp;
                        else answer = Math.min(tmp, answer);
                    }
                }
                if (C2.x == location.x && C2.y == location.y && !location.c2) {
                    location.c2 = true;
                    int tmp = solve2(location);
                    if (tmp != -1) {
                        if (answer == -1) answer = tmp;
                        else answer = Math.min(tmp, answer);
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                nx = location.x + dx[i];
                ny = location.y + dy[i];
                if (isOutOfMap(nx, ny)) continue;
                if (map[ny][nx] == '#') continue;
                if (visited[i][ny][nx]) continue;
                if (location.direction == i) continue;
                queue.add(new Location(nx, ny, i, location.move + 1, location.c1, location.c2));
                visited[i][ny][nx] = true;
            }
        }
        return answer;
    }

    private static int solve2(Location l) {
        boolean[][][] newVisited = new boolean[4][N + 1][M + 1];
        Queue<Location> queue = new LinkedList<>();
        queue.add(l);
        Location location;
        int nx, ny;
        while (!queue.isEmpty()) {
            location = queue.remove();
            if (map[location.y][location.x] == 'C') {
                if (C1.x == location.x && C1.y == location.y && !location.c1) {
                    return location.move;
                }
                if (C2.x == location.x && C2.y == location.y && !location.c2) {
                    return location.move;
                }
            }
            for (int i = 0; i < 4; i++) {
                nx = location.x + dx[i];
                ny = location.y + dy[i];
                if (isOutOfMap(nx, ny)) continue;
                if (map[ny][nx] == '#') continue;
                if (newVisited[i][ny][nx]) continue;
                if (location.direction == i) continue;
                queue.add(new Location(nx, ny, i, location.move + 1, location.c1, location.c2));
                newVisited[i][ny][nx] = true;
            }
        }
        return -1;
    }

    private static boolean isOutOfMap(int x, int y) {
        return x <= 0 || y <= 0 || x > M || y > N;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N + 1][M + 1];
        visited = new boolean[4][N + 1][M + 1];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = input.charAt(j);
                if (map[i + 1][j + 1] == 'S') {
                    S = new Location(j + 1, i + 1, -1, 0, false, false);
                }
                if (map[i + 1][j + 1] == 'C') {
                    if (C1 == null) {
                        C1 = new Location(j + 1, i + 1);
                    } else C2 = new Location(j + 1, i + 1);
                }
            }
        }
    }

    static class Location {
        int x;
        int y;
        int direction;
        int move;
        boolean c1, c2;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(int x, int y, int direction, int move, boolean c1, boolean c2) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.move = move;
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
