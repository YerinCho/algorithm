package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q6068 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static ArrayList<Work> works;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        works.sort(Work::compareByDue);
        int ptr = works.get(0).due;
        Work work;
        for (int i = 0; i < works.size(); i++) {
            work = works.get(i);
            if (work.due >= ptr) {
                ptr -= work.time;
            } else {
                ptr = work.due - work.time;
            }
        }
        return ptr < 0 ? -1 : ptr;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        works = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            works.add(new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static class Work {
        int time;
        int due;

        public Work(int time, int due) {
            this.time = time;
            this.due = due;
        }

        public static int compareByDue(Work h1, Work h2) {
            return -Integer.compare(h1.due, h2.due);
        }

    }
}
