package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2307 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static ArrayList<Edge>[] graph;
    private static int distance[];
    private static int parents[];
    private static boolean visited[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int a = dijkstra(null);
        int i = N;
        int tmp = -1;
        while (true) {
            if (i == 1) break;
            tmp = Math.max(tmp, dijkstra(new Load(i, parents[i])));
            i = parents[i];
        }
        if (tmp == 1000000000) bw.write(-1 + "\n");
        else bw.write(tmp - a + "\n");
        bw.flush();
        bw.close();
    }

    private static int dijkstra(Load except) {
        Arrays.fill(distance, 1000000000);
        Arrays.fill(visited, false);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Edge::sort);
        Edge cur;
        pq.add(new Edge(1, 0));
        distance[1] = 0;

        while (!pq.isEmpty()) {
            cur = pq.remove();
            if (visited[cur.x]) continue;
            visited[cur.x] = true;
            for (int i = 0; i < graph[cur.x].size(); i++) {
                int nextNode = graph[cur.x].get(i).x;
                int nextWeight = graph[cur.x].get(i).weight;
                if (except != null && ((except.x == cur.x && except.y == nextNode) || (except.y == cur.x && except.x == nextNode))) {
                    continue;
                }

                if (!visited[nextNode] && distance[nextNode] > cur.weight + nextWeight) {
                    distance[nextNode] = cur.weight + nextWeight;
                    if (except == null) parents[nextNode] = cur.x;
                    pq.add(new Edge(nextNode, distance[nextNode]));
                }
            }
        }
        return distance[N];
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int a, b, c;
        graph = new ArrayList[N + 1];
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        parents = new int[N + 1];
        for (int i = 0; i < N; i++) {
            graph[i + 1] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
    }

    static class Edge {
        int x;
        int weight;

        public Edge(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        public int sort(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    static class Load {
        int x;
        int y;

        public Load(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
