package bj;

import java.io.*;
import java.util.ArrayList;

public class Q2533 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static ArrayList<Node>[] tree;
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int a = dfs(1, 0);
        int b = dfs(1, 1);
        int min = Math.min(a, b);
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    private static int dfs(int index, int count) {
        ArrayList<Node> list = tree[index];
        visited[index] = true;
        if (dp[index][count] != -1) return dp[index][count];
        int contain = 1;
        int notContain = 0;
        for (Node node : list) {
            if (visited[node.outDegree]) continue;
            notContain += dfs(node.outDegree, 1);
            contain += Math.min(dfs(node.outDegree, 1), dfs(node.outDegree, 0));
        }

        dp[index][1] = contain;
        dp[index][0] = notContain;

        return dp[index][count];
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] inputs;
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            inputs = br.readLine().split(" ");
            tree[Integer.parseInt(inputs[0])].add(new Node(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])));
            tree[Integer.parseInt(inputs[1])].add(new Node(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[0])));
        }
        dp = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
    }

    static class Node {
        int inDegree;
        int outDegree;

        public Node(int inDegree, int outDegree) {
            this.inDegree = inDegree;
            this.outDegree = outDegree;
        }
    }
}
