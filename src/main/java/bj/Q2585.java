package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q2585 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static List<Point> locations = new ArrayList<>();
    private static int[] count = new int[1002];


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        int answer = binarySearch(1, 1415);
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
    }

    // 1ℓ당 비행거리는 10㎞이고 연료주입은 ℓ단위
    // => 거리 / 10 + 1 이 필요한 연료 량
    // 출발지 S의 좌표는 항상 (0,0)이고 목적지 T의 좌표는 (10000,10000)

    private static int binarySearch(int min, int max) {
        int answer = 1415;
        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (canTravel(mid)) {
                max = mid - 1;
                answer = mid;
            } else {
                min = mid + 1;
            }
        }
        return answer;
    }


    // bfs 로 특정 연료로 k 이하에 갈수잇는지
    private static boolean canTravel(int fuel) {
        // 연료 충전한 횟수임
        count = new int[N + 2];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(locations.get(0), 0));
        Node node;
        while (!queue.isEmpty()) {
            node = queue.remove();
            // 현재 위치 - 최종 위치까지 갈 수 잇어!
            if (getFuel(locations.get(node.idx), locations.get(N + 1)) <= fuel) {
                return true;
            }
            // 현재 연료통 크기로는 K 번 이하로 ㄴㄴ이니까 패ㅡ스
            if (count[node.idx] > K) continue;
            for (int i = 1; i <= N; i++) {
                // count 값이 0 이면 방문한적이 없으니까 연료 충전하러 가도 댐
                if (count[i] == 0 && getFuel(locations.get(node.idx), locations.get(i)) <= fuel) {
                    queue.add(new Node(locations.get(i), i));
                    count[i] = count[node.idx] + 1;
                }
            }
        }
        // K 번 이하로 오는 경우가 불가능함... ㅠㅠ
        return false;
    }

    private static int getFuel(Point a, Point b) {
        return (int) getDistance(a, b) / 10 + 1;
    }

    private static double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        locations.add(new Point(0, 0));

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            locations.add(new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        locations.add(new Point(10000, 10000));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node extends Point {
        int idx;

        public Node(Point p, int idx) {
            super(p.x, p.y);
            this.idx = idx;
        }
    }
}
