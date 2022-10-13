package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q10282 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static int[] root;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m;
        n = Integer.parseInt(st.nextToken());
        boolean yes = true;
        for (int i = 1; i < M; i++) {
            m = Integer.parseInt(st.nextToken());
            if (find(n) != find(m)) {
                yes = false;
                break;
            }
            n = m;
        }
        if (yes) {
            bw.write("YES\n");
        } else bw.write("NO\n");
        bw.flush();
        bw.close();
    }

    private static int find(int x) {
        if (root[x] == x) {
            return x;
        } else {
            return find(root[x]);
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        root[y] = x;
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        root = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            root[i] = i;
        }
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) union(i, j);
            }
        }
    }
}
