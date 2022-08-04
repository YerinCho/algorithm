package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q1466 {
    private static boolean[][] graph;
    private static boolean[] visited;
    ArrayList<Integer>[] g;

    private static int N;

    public static void main(String[] args) {
        Q1466 solution = new Q1466();
        int a = solution.minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}});
        System.out.println(a);
    }

    public int minReorder(int n, int[][] connections) {
        visited = new boolean[n];
        graph = new boolean[n][n];
        N = n;
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < connections.length; i++) {
            graph[connections[i][0]][connections[i][1]] = true;
        }

        return bfs(0);
    }

    public int bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        int c = 0;
        int n;
        while (!queue.isEmpty()) {
            n = queue.remove();
            visited[n] = true;
            for (int j = 0; j < N; j++) {
                if (!(graph[n][j] || graph[j][n]) || visited[j]) continue;
                if (graph[n][j]) c++;
                queue.add(j);
            }
        }
        return c;
    }
}
