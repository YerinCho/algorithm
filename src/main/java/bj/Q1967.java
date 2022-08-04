package bj;

import java.io.*;
import java.util.ArrayList;

public class Q1967 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static ArrayList<Node>[] t;
    private static boolean[] visited;
    private static int N;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int distance = -1;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            int tmp = dfs(i, 0);
            if (distance == -1 || tmp > distance) {
                distance = tmp;
            }
        }
        bw.write(distance + "\n");
        bw.flush();
        bw.close();
    }

    private static int dfs(int index, int dis) {
        int tmpDis = 0;
        for (Node n : t[index]) {
            if (visited[n.to]) {
                continue;
            }
            visited[n.to] = true;
            int dist = dfs(n.to, n.dis);
            tmpDis = Math.max(tmpDis, dist);
        }
        return dis + tmpDis;
    }


    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] inputs = new String[N - 1];
        t = new ArrayList[N + 1];
        for (int i = 0; i < N - 1; i++) {
            inputs[i] = br.readLine();
            t[i + 1] = new ArrayList<>();
        }
        t[N] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            String[] input = inputs[i].split(" ");
            t[Integer.parseInt(input[0])].add(new Node(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            t[Integer.parseInt(input[1])].add(new Node(Integer.parseInt(input[0]), Integer.parseInt(input[2])));
        }
    }

    static class Node {
        int to;
        int dis;

        public Node(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }
    }
}
