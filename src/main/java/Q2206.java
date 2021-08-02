import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q2206 {
    private static int N;
    private static int M;
    private static int dis = -1;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[][][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);

        initialize(br);
        Queue<Node> queue = new LinkedList<>();
        int answer = bfs(1, 1, queue);

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    private static void initialize(BufferedReader br) throws IOException {
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList());
        }
        for (int i = 1; i < N + 1; i++) {
            String[] input = br.readLine().split("");
            graph.get(i).add(0);
            for (int j = 1; j < M + 1; j++) {
                graph.get(i).add(Integer.parseInt(input[j - 1]));
            }
        }
        visited = new boolean[N + 1][M + 1][2];
    }

    private static int bfs(int x, int y, Queue<Node> queue) {
        int tmpX, tmpY;
        Node node;
        queue.add(new Node(x, y, 1, 0));
        visited[x][y][0] = false;
        visited[x][y][1] = false;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.x == N && node.y == M) {
                return node.distance;
            }

            for (int i = 0; i < 4; i++) {
                tmpX = node.x + dx[i];
                tmpY = node.y + dy[i];
                if (validate(tmpX, tmpY))
                    continue;

                if (graph.get(tmpX).get(tmpY) == 1) { // 다음칸이 벽
                    if (node.broken == 1 || visited[tmpX][tmpY][1]) // 이미 벽을 한번 부수면서 왔거나, 벽을 부수었을 경우 방문한 경우 방문하지 않기
                        continue;
                    queue.add(new Node(tmpX, tmpY, node.distance + 1, 1));
                    visited[tmpX][tmpY][1] = true;
                } else { // 다음칸이 벽 아님
                    if (visited[tmpX][tmpY][node.broken])
                        continue;
                    queue.add(new Node(tmpX, tmpY, node.distance + 1, node.broken));
                    visited[tmpX][tmpY][node.broken] = true;
                }
            }
        }
        return -1;
    }

    private static boolean validate(int x, int y) {
        return x > N || x < 1 || y > M || y < 1;
    }

    static class Node {
        public int x;
        public int y;
        public int distance;
        public int broken;

        public Node(int x, int y, int distance, int broken) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.broken = broken;
        }
    }
}
