package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q7983 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static ArrayList<Handout> handouts;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        handouts.sort(Handout::compareByDue);
        return binarySearch();
    }

    private static int binarySearch() {
        int max = handouts.get(handouts.size() - 1).due;
        int min = 0;
        int answer = max;
        int mid;
        while (min <= max) {
            mid = (min + max) / 2;
            if (check(mid)) {
                min = mid + 1;
                answer = mid;
            } else {
                max = mid - 1;
            }
        }
        return answer;
    }

    private static boolean check(int mid) {
        Handout handout;
        int ptr = mid;
        for (int i = 0; i < handouts.size(); i++) {
            handout = handouts.get(i);
            ptr += handout.time;
            if (ptr > handout.due) return false;
        }
        return true;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        handouts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            handouts.add(new Handout(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static class Handout {
        int time;
        int due;

        public Handout(int time, int due) {
            this.time = time;
            this.due = due;
        }

        public static int compareByDue(Handout h1, Handout h2) {
            return Integer.compare(h1.due, h2.due);
        }
    }
}

