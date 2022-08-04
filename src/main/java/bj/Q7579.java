package bj;

import java.io.*;
import java.util.StringTokenizer;

public class Q7579 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N; // 앱 개수
    private static int M; // 추가로 필요한 메모리
    private static int dp[]; // dp[비활성화 할 때 비용] = 확보할 수 있는 최대 메모
    private static App[] apps;
    private static final int MAX_COST = 100 * 100 + 1;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        solve();
        for (int i = 0; i <= MAX_COST; i++) {
            if (dp[i] >= M) {   // 비용 작은 순서대로 탐색후 찾으면 출력
                bw.write(i + "\n");
                break;
            }
        }
        bw.flush();
        bw.close();
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            for (int j = MAX_COST - 1; j >= 0; j--) {
                if (j < apps[i].c) break;   // 현재 비용으로 앱 kill 불가능
                dp[j] = Math.max(dp[j], dp[j - apps[i].c] + apps[i].mem);
            }
        }
    }

    private static void initialize() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[MAX_COST];
        apps = new App[N + 1];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            apps[i + 1] = new App(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
        }
    }

    static class App {
        int mem;
        int c; // 비용

        public App(int mem, int c) {
            this.mem = mem;
            this.c = c;
        }
    }
}
