package programmers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Q60063 {
    public static int[] dx = new int[]{1, 0, -1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};
    public static int N;
    public static int answer = 0;
    public static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        Q60063 solution = new Q60063();
        int[][] board = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 0}};
        System.out.println(solution.solution(board));
    }

    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[N][N][2];
        bfs(board);
        return answer;
    }

    public static void bfs(int[][] board) {
        int x1, x2, y1, y2;
        Robot robot;
        Queue<Robot> robots = new LinkedList<>();
        robots.add(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;
        while (!robots.isEmpty()) {
            robot = robots.remove();
            if ((robot.x == N - 1 && robot.y == N - 1) || (robot.getX2() == N - 1 && robot.getY2() == N - 1)) {
                answer = robot.time;
                return;
            }
            for (int i = 0; i < 4; i++) {
                x1 = robot.x + dx[i];
                y1 = robot.y + dy[i];
                x2 = robot.getX2() + dx[i];
                y2 = robot.getY2() + dy[i];
                if (isOutOfMap(x1, y1, x2, y2) || visited[x1][y1][robot.direction] || !canMove(board[y1][x1], board[y2][x2])) {
                    continue;
                }
                visited[x1][y1][robot.direction] = true;
                robots.add(new Robot(x1, y1, robot.direction, robot.time + 1));
            }

            // 가로
            if (robot.direction == 0) {
                if (robot.y + 1 < N && board[robot.y + 1][robot.x] == 0 && board[robot.y + 1][robot.x + 1] == 0) {
                    if (!visited[robot.x + 1][robot.y][1]) {
                        visited[robot.x + 1][robot.y][1] = true;
                        robots.add(new Robot(robot.x + 1, robot.y, 1, robot.time + 1));
                    }
                    if (!visited[robot.x][robot.y][1]) {
                        visited[robot.x][robot.y][1] = true;
                        robots.add(new Robot(robot.x, robot.y, 1, robot.time + 1));
                    }
                }
                if (robot.y - 1 >= 0 && board[robot.y - 1][robot.x] == 0 && board[robot.y - 1][robot.x + 1] == 0) {
                    if (!visited[robot.x][robot.y - 1][1]) {
                        visited[robot.x][robot.y - 1][1] = true;
                        robots.add(new Robot(robot.x, robot.y - 1, 1, robot.time + 1));
                    }
                    if (!visited[robot.x + 1][robot.y - 1][1]) {
                        visited[robot.x + 1][robot.y - 1][1] = true;
                        robots.add(new Robot(robot.x + 1, robot.y - 1, 1, robot.time + 1));
                    }
                }
            }

            if (robot.direction == 1) {
                if (robot.x + 1 < N && board[robot.y][robot.x + 1] == 0 && board[robot.y + 1][robot.x + 1] == 0) {
                    if (!visited[robot.x][robot.y][0]) {
                        visited[robot.x][robot.y][0] = true;
                        robots.add(new Robot(robot.x, robot.y, 0, robot.time + 1));
                    }
                    if (!visited[robot.x][robot.y + 1][0]) {
                        visited[robot.x][robot.y + 1][0] = true;
                        robots.add(new Robot(robot.x, robot.y + 1, 0, robot.time + 1));
                    }
                }
                if (robot.x - 1 >= 0 && board[robot.y][robot.x - 1] == 0 && board[robot.y + 1][robot.x - 1] == 0) {
                    if (!visited[robot.x - 1][robot.y][0]) {
                        visited[robot.x - 1][robot.y][0] = true;
                        robots.add(new Robot(robot.x - 1, robot.y, 0, robot.time + 1));
                    }
                    if (!visited[robot.x - 1][robot.y + 1][0]) {
                        visited[robot.x - 1][robot.y + 1][0] = true;
                        robots.add(new Robot(robot.x - 1, robot.y + 1, 0, robot.time + 1));
                    }
                }
            }
        }
    }

    private static boolean canMove(int r1, int r2) {
        return r1 == 0 && r2 == 0;
    }

    private static boolean isOutOfMap(int x1, int y1, int x2, int y2) {
        return x1 < 0 || x1 >= N || x2 < 0 || x2 >= N || y1 < 0 || y1 >= N || y2 < 0 || y2 >= N;
    }

    // 가로기준 왼쪽노드, 세로기준 위쪽노드
    static class Robot {
        public int x;
        public int y;
        // 가로일 때 0, 세로일 때 1
        public int direction;
        public int time;

        public Robot(int x, int y, int direction, int time) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.time = time;
        }

        public int getX2() {
            return x + dx[direction];
        }

        public int getY2() {
            return y + dy[direction];
        }
    }
}
