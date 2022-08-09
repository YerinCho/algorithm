package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q13325 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int K;
    private static int N;
    private static int[] treeNodes;
    private static int[] treeEdges;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        int sum = 0;
        for (int i = N - 1; i > 1; i = i - 2) {
            if (treeNodes[i] + treeEdges[i] > treeNodes[i - 1] + treeEdges[i - 1]) {
                treeEdges[i - 1] = treeNodes[i] + treeEdges[i] - treeNodes[i - 1];
            } else {
                treeEdges[i] = treeNodes[i - 1] + treeEdges[i - 1] - treeNodes[i];
            }
            treeNodes[i / 2] = treeNodes[i] + treeEdges[i];
            sum += treeEdges[i] + treeEdges[i - 1];
        }
        return sum;
    }

    private static void initialize() throws IOException {
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = 1 << (K + 1);
        treeEdges = new int[N];
        treeNodes = new int[N];
        for (int i = 2; i < N; i++) {
            treeEdges[i] = Integer.parseInt(st.nextToken());
        }
    }
}
