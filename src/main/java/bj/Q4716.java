package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Q4716 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int B;
    private static int A;
    private static List<Team> teams = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Team> sorted;
        while (true) {
            boolean done = initialize();
            if (done) break;
            sorted = teams.stream().sorted(Team::sort).collect(Collectors.toList());
            int d = 0;
            for (Team t : sorted) {
                // A 풍선 먼저 주기..
                if (t.isNearA()) {
                    if (t.k <= A) {
                        d += t.k * t.dA;
                        A -= t.k;
                    } else {
                        d += A * t.dA;
                        t.k -= A;
                        A = 0;
                        d += t.k * t.dB;
                        B -= t.k;
                    }

                } else {
                    if (t.k <= B) {
                        d += t.k * t.dB;
                        B -= t.k;
                    } else {
                        d += B * t.dB;
                        t.k -= B;
                        B = 0;
                        d += t.k * t.dA;
                        A -= t.k;
                    }
                }
                t.k = 0;
            }

            for (Team t : sorted) {
                System.out.println(t.toString());
            }

            bw.write(d + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static boolean initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        A = Integer.parseInt(input[1]);
        B = Integer.parseInt(input[2]);
        if (N == 0 && A == 0 && B == 0) return true;
        teams = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            teams.add(new Team(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
        }
        return false;
    }

    static class Team {
        int k;
        int dA;
        int dB;

        public Team(int k, int dA, int dB) {
            this.k = k;
            this.dA = dA;
            this.dB = dB;
        }

        public int sort(Team t) {
            return Integer.compare(Math.abs(t.dA - t.dB), Math.abs(dA - dB));
        }

        public boolean isNearA() {
            return dA < dB;
        }

        @Override
        public String toString() {
            return "Team{" +
                    "k=" + k +
                    ", dA=" + dA +
                    ", dB=" + dB +
                    '}';
        }
    }
}
