package bj;

import java.io.*;
import java.util.*;
import java.util.Deque;
import java.util.Queue;

//1주차 상 문제
public class Q13460 {
    private final static String WALL = "#";
    private final static String RED = "R";
    private final static String BLUE = "B";
    private final static String HOLE = "O";

    private static BufferedReader br;
    private static BufferedWriter bw;

    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};
    public static int N;
    public static int M;
    public static String[][] map;
    public static boolean[][][][] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<String> a = new LinkedList<>();
        Deque<String> s = new ArrayDeque<>();
        initialize();
        Location red = new Location(1, 1);
        Location blue = new Location(1, 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals(RED)) {
                    red = new Location(j, i);
                }
                if (map[i][j].equals(BLUE)) {
                    blue = new Location(j, i);
                }
            }
        }

        bfs(red, blue);

        if (answer == 0) answer = -1;
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    private static void bfs(Location red, Location blue) {
        Bead bead;
        Queue<Bead> beads = new LinkedList<>();
        beads.add(new Bead(red, blue, 0));
        visited[red.y][red.x][blue.y][blue.x] = true;
        while (!beads.isEmpty()) {
            bead = beads.remove();
            // 이동횟수가 초과된 경우 -1
            if (bead.count > 10) {
                answer = -1;
                break;
            }
            if (map[bead.red.y][bead.red.x].equals(HOLE)) {
                answer = bead.count;
                break;
            }
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int rx = bead.red.x + dx[i];
                int ry = bead.red.y + dy[i];
                int bx = bead.blue.x + dx[i];
                int by = bead.blue.y + dy[i];
                int rCount = 0;
                int bCount = 0;
                while (true) {
                    rCount++;
                    if (map[ry][rx].equals(WALL)) {
                        rx -= dx[i];
                        ry -= dy[i];
                        break;
                    }
                    if (map[ry][rx].equals(HOLE)) {
                        break;
                    }
                    rx += dx[i];
                    ry += dy[i];
                }
                while (true) {
                    bCount++;
                    if (map[by][bx].equals(WALL)) {
                        bx -= dx[i];
                        by -= dy[i];
                        break;
                    }
                    if (map[by][bx].equals(HOLE)) {
                        break;
                    }
                    bx += dx[i];
                    by += dy[i];
                }
                if (map[by][bx].equals(HOLE)) {
                    continue;
                }
                if (rx == bx && ry == by) {
                    if (rCount > bCount) {
                        rx -= dx[i];
                        ry -= dy[i];
                    } else {
                        bx -= dx[i];
                        by -= dy[i];
                    }
                }
                if (visited[ry][rx][by][bx]) continue;
                beads.add(new Bead(new Location(rx, ry), new Location(bx, by), bead.count + 1));
            }
        }
    }

    private static void initialize() throws IOException {
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);
        List<String> inputs = new ArrayList<>();
        map = new String[N][M];
        visited = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = inputs.get(i).substring(j, j + 1);
            }
        }
    }

    static class Bead {
        Location red;
        Location blue;
        int count;

        public Bead(Location red, Location blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
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
