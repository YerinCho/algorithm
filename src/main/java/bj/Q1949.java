package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q1949 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int[] people;
    private static ArrayList<Node>[] tree;
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        dp[1][0] = dfs(1, 0);
        dp[1][1] = dfs(1, 1);
        bw.write(Math.max(dp[1][0], dp[1][1]) + "\n");
        bw.flush();
        bw.close();
    }

    private static int dfs(int index, int in) {
        ArrayList<Node> list = tree[index];
        visited[index] = true;

        if (dp[index][in] != -1) return dp[index][in];
        // 작명센스 개구림..;
        int notSum = 0;
        int sum = people[index];
        for (Node node : list) {
            if (visited[node.outDegree]) continue;
            sum += dfs(node.outDegree, 0);
            notSum += Math.max(dfs(node.outDegree, 1), dfs(node.outDegree, 0));
        }

        dp[index][1] = sum;
        dp[index][0] = notSum;

        return dp[index][in];
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        people = new int[N + 1];
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            people[i + 1] = Integer.parseInt(inputs[i]);
        }
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
            dp[i][1] = -1;
            dp[i][0] = -1;
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
