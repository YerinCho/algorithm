package bj;

import java.io.*;
import java.util.PriorityQueue;

public class Q1774 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static Node[] godList;
    private static int[] parent;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N + 1; j++) {
                double distance = Math.sqrt(Math.pow(godList[i].x - godList[j].x, 2) + Math.pow(godList[i].y - godList[j].y, 2));
                pq.add(new Edge(i, j, distance));
            }
        }

        double result = 0;

        // 크루스칼
        while (!pq.isEmpty()) {
            Edge edge = pq.remove();

            if (union(edge.start, edge.end)) {
                result += edge.weight;
            }
        }

        System.out.printf("%.2f", result);

        bw.flush();
        bw.close();
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
            return true;
        }
        return false;
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        godList = new Node[N + 1];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            godList[i + 1] = (new Node(Integer.parseInt(input[0]), Integer.parseInt((input[1]))));
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        // 이미 연결된 애들 union
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            union(Integer.parseInt(input[0]), Integer.parseInt((input[1])));
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double value) {
            this.start = start;
            this.end = end;
            this.weight = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
