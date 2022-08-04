package leetcode;

import java.util.Objects;
import java.util.PriorityQueue;

public class Q1631 {
    private static final int[] dx = new int[]{-1, 0, 1, 0};
    private static final int[] dy = new int[]{0, -1, 0, 1};
    private int N;
    private int M;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Q1631 solution = new Q1631();
        int a = solution.minimumEffortPath(new int[][]
                {{1, 10, 6, 7, 9, 10, 4, 9}});
        System.out.println(a);
    }

    public int minimumEffortPath(int[][] heights) {
        N = heights.length;
        M = heights[0].length;
        visited = new boolean[N][M];
        PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Location(0, 0, 0));

        int answer = Integer.MAX_VALUE;

        while (!priorityQueue.isEmpty()) {
            Location location = priorityQueue.remove();
            if (visited[location.y][location.x]) {
                continue;
            }
            visited[location.y][location.x] = true;
            if (location.y == N - 1 && location.x == M - 1) {
                answer = Math.min(answer, location.effort);
            }

            for (int i = 0; i < 4; i++) {
                int x = location.x + dx[i];
                int y = location.y + dy[i];
                if (isOutOfMap(x, y) || visited[y][x]) {
                    continue;
                }
                int newEffort = Math.max(location.effort, Math.abs(heights[location.y][location.x] - heights[y][x]));
                priorityQueue.add(new Location(x, y, newEffort));
            }
        }
        return answer;
    }

    private boolean isOutOfMap(int nx, int ny) {
        return nx < 0 || nx >= M || ny < 0 || ny >= N;
    }

    static class Location implements Comparable<Location> {
        int x;
        int y;
        int effort;

        public Location(int x, int y, int effort) {
            this.x = x;
            this.y = y;
            this.effort = effort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y && effort == location.effort;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, effort);
        }

        @Override
        public int compareTo(Location location) {
            return Integer.compare(this.effort, location.effort);
        }
    }
}
