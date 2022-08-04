package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 사이클 생겨서 틀리는 입력 예시
//9
//3 4 2 1 5 12 3 4
//7 5 4 2 9 6 7
//6 5 9 16 7 8
//3 7 14 1 6
//6 13 4 3
//11 8 9
//15 16
//7
public class Q6091 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static boolean[][] connected;
    private static List<Edge> sorted;
    private static int[] root;

    // 플로이드 와샬은 그래프에서 적용 가능한 알고리즘이지만,
    // 이 문제는 트리를 출력하는 거임!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // 사이클 생기면 안돼!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // 그래서 출력초과 존나 떴구나!!!!!!!!!!!!!!!

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        search();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (connected[i][j]) {
                    count++;
                    sb.append(j);
                    sb.append(" ");
                }
            }
            bw.write(count + " " + sb);
            if (i != N) bw.write("\n");
            sb = new StringBuilder();
            count = 0;
        }

        bw.flush();
        bw.close();
    }

    private static void search() {
        Edge edge;
        while (!sorted.isEmpty()) {
            edge = sorted.remove(sorted.size() - 1);
            if (find(edge.v1) != find(edge.v2)) {
                // (v1, v2) 의 경로가 존재하지 않는 경우 갱신
                update(edge);
            }
        }
    }

    private static void update(Edge edge) {
        connected[edge.v1][edge.v2] = true;
        connected[edge.v2][edge.v1] = true;

        union(edge.v1, edge.v2);
    }

    private static int find(int x) {
        if (root[x] == x) {
            return x;
        } else {
            return find(root[x]);
        }
    }

    private static void union(int x, int y){
        x = find(x);
        y = find(y);

        root[y] = x;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        sorted = new ArrayList<>();
        connected = new boolean[N + 1][N + 1];
        root = new int[N + 1];
        for (int i = 0; i < N; i++) {
            root[i + 1] = i + 1;
        }
        String[] inputs;
        int k;
        for (int i = 1; i < N; i++) {
            inputs = br.readLine().split(" ");
            k = i + 1;
            for (String input : inputs) {
                sorted.add(new Edge(i, k, Integer.parseInt(input)));
                k++;
            }
        }
        sorted = sorted.stream()
                .sorted(Edge::sort)
                .collect(Collectors.toList());
    }

    static class Edge {
        public int v1;
        public int v2;
        public int minDistance;

        public Edge(int v1, int v2, int minDistance) {
            this.v1 = v1;
            this.v2 = v2;
            this.minDistance = minDistance;
        }

        public int sort(Edge e) {
            return Integer.compare(e.minDistance, this.minDistance);
        }
    }
}
