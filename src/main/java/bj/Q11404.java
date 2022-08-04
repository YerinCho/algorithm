package bj;

import java.io.*;

public class Q11404 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        getMinCosts();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (costs[i][j] == 100000000) costs[i][j] = 0;
                bw.write(costs[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void getMinCosts() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (j == k || j == i || i == k) continue;
                    costs[j][k] = Math.min(costs[j][k], costs[j][i] + costs[i][k]);
                }
            }
        }
    }


    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        String[] input;
        costs = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                costs[i][j] = 100000000;
            }
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            costs[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = Math.min(costs[Integer.parseInt(input[0])][Integer.parseInt(input[1])], Integer.parseInt(input[2]));
//            costs[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = Integer.parseInt(input[2]);
        }
    }
}
