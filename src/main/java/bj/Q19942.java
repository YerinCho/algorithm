package bj;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q19942 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int mp; // 단백징
    private static int mf; // 지방
    private static int ms; // 탄수
    private static int mv; // 비타민
    private static Food[] foods;
    private static boolean[] visited;
    private static State answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        solve();
        if (answer == null) bw.write(-1 + "\n");
        else {
            bw.write(answer.price + "\n");
            for (int i = 0; i < N; i++) {
                if ((answer.state & (1 << i)) != 0) bw.write(i + 1 + " ");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void solve() {
        State state, newState;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0, 0, 0, 0));
        while (!queue.isEmpty()) {
            state = queue.remove();
            for (int i = 0; i <= N; i++) {
                if ((state.state | 1 << i) >= 1 << N) continue;
                if (visited[state.state | 1 << i]) continue;
                newState = new State(
                        state.p + foods[i + 1].p,
                        state.f + foods[i + 1].f,
                        state.s + foods[i + 1].s,
                        state.v + foods[i + 1].v,
                        state.price + foods[i + 1].price,
                        state.state | 1 << i);
                queue.add(newState);
                visited[newState.state] = true;
                if (newState.completed()) {
                    if (answer == null) answer = newState;
                    else {
                        if (answer.price > newState.price) answer = newState;
                        if (answer.price == newState.price) {
                            StringBuilder a = new StringBuilder();
                            StringBuilder b = new StringBuilder();
                            for (int j = 0; j < N; j++) {
                                if ((answer.state & (1 << j)) != 0) a.append(j + 1);
                                if ((newState.state & (1 << j)) != 0) b.append(j + 1);
                            }
                            if (a.compareTo(b) > 0) {
                                answer = newState;
                            }
                        }
                    }
                }
            }
        }
    }


    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());
        foods = new Food[N + 1];
        visited = new boolean[1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            foods[i + 1] = new Food(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
    }

    static class State extends Food {
        int state;

        public State(int p, int f, int s, int v, int price, int state) {
            super(p, f, s, v, price);
            this.state = state;
        }

        public boolean completed() {
            return this.p >= mp && this.f >= mf && this.s >= ms && this.v >= mv;
        }
    }

    static class Food {
        int p;
        int f;
        int s;
        int v;
        int price;

        public Food(int p, int f, int s, int v, int price) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.price = price;
        }
    }
}
