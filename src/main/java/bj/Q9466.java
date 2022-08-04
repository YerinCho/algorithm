package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//텀 프로젝트
public class Q9466 {
    private static int T;
    private static int N;
    private static int count;
    private static int[] graph;
    private static boolean[] visited;
    private static boolean[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            initialize(br);
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && !team[j]) {
                    search(j);
                }
            }
            bw.write(N - count + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void initialize(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1];
        visited = new boolean[N + 1];
        team = new boolean[N + 1];
        count = 0;
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            graph[i + 1] = Integer.parseInt(input[i]);
        }
    }

    private static void search(int value) {
        visited[value] = true;
        int next = graph[value];
        if (!visited[next] && !team[next]) {
            search(next);
        }
        if (!team[next]) {
            for (int i = next; i != value; i = graph[i]) { // 사이클에 포함된 node가 몇 개인지 확인하기
                count++;
            }
            count++;
        }
        team[value] = true;
    }
}
