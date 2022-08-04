package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Q1162 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int K;
    private static ArrayList<Node>[] time;
    private static long distances[][];
    private static boolean visited[][];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        if (K >= M) bw.write(0 + "\n");
        else {
            long a = search();
            bw.write(a + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static long search() {
        PriorityQueue<Distance> pq = new PriorityQueue<>(Distance::sort);
        pq.add(new Distance(1, 0, K));
        Distance distance;
        while (!pq.isEmpty()) {
            distance = pq.remove();
            visited[distance.n][distance.k] = true;
            if (distance.dis > distances[distance.n][distance.k]) continue;
            // 도로 ㄴㄴ 포장
            for (Node node : time[distance.n]) {
                if (visited[node.to][distance.k]) continue;
                if (distances[node.to][distance.k] > distance.dis + node.dis) {
                    distances[node.to][distance.k] = distance.dis + node.dis;
                    pq.add(new Distance(node.to, distance.dis + node.dis, distance.k));
                }

                if (distance.k > 0) {
                    if (visited[node.to][distance.k - 1]) continue;
                    if (distances[node.to][distance.k - 1] > distance.dis) {
                        distances[node.to][distance.k - 1] = distance.dis;
                        pq.add(new Distance(node.to, distance.dis, distance.k - 1));
                    }
                }
            }
//            // 도로 ㅇㅇ 포장
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(distances[N][i], answer);
        }
        return answer;
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        distances = new long[N + 1][K + 1];
        visited = new boolean[N + 1][K + 1];
        time = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            time[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            time[Integer.parseInt(input[0])].add(new Node(Integer.parseInt(input[1]), Long.parseLong(input[2])));
            time[Integer.parseInt(input[1])].add(new Node(Integer.parseInt(input[0]), Long.parseLong(input[2])));
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                distances[i][j] = Long.MAX_VALUE;
            }
        }
    }

    static class Node {
        int to;
        long dis;

        public Node(int to, long dis) {
            this.to = to;
            this.dis = dis;
        }
    }

    static class Distance {
        int n;
        long dis;
        int k;

        public Distance(int n, long dis, int k) {
            this.n = n;
            this.dis = dis;
            this.k = k;
        }

        public int sort(Distance d) {
            return Long.compare(this.dis, d.dis);
        }
    }
}
