package bj;

import java.io.*;
import java.util.*;

public class Q1306 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N; // 코스의 길이
    private static int M; // 시야의 범위 == 윈도우 크기
    private static int[] light;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        solve();
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            while (!deque.isEmpty() && light[deque.getLast()] < light[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        bw.write(light[deque.getFirst()] + " ");

        for (int i = M; i < N; i++) {
            if (deque.getFirst() <= i - M) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && light[deque.getLast()] < light[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            bw.write(light[deque.getFirst()] + " ");
        }
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M = 2 * M - 1; // 윈도우크기로 변경
        st = new StringTokenizer(br.readLine());
        light = new int[N];
        for (int i = 0; i < N; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }
    }
}
