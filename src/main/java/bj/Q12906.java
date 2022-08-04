package bj;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q12906 {
    static class Hanoi {
        String A;
        String B;
        String C;

        public Hanoi(String a, String b, String c) {
            A = a;
            B = b;
            C = c;
        }

        public boolean isEnd() {
            return this.state().equals(answer);
        }

        public String state() {
            return A + "," + B + "," + C;
        }

        public boolean movableA() {
            return A.length() > 0;
        }

        public boolean movableB() {
            return B.length() > 0;
        }

        public boolean movableC() {
            return C.length() > 0;
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] count;
    private static String[] states;
    private static Set<String> visited;
    private static String answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        answer = find();
        int a = search();
        bw.write(a + "\n");
        bw.flush();
        bw.close();
    }

    private static String find() {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < states[i].length(); j++) {
                if (states[i].charAt(j) == 'A') a++;
                if (states[i].charAt(j) == 'B') b++;
                if (states[i].charAt(j) == 'C') c++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("A".repeat(Math.max(0, a)));
        sb.append(",");
        sb.append("B".repeat(Math.max(0, b)));
        sb.append(",");
        sb.append("C".repeat(Math.max(0, c)));
        return sb.toString();
    }

    private static int search() {
        Queue<Hanoi> queue = new LinkedList<>();
        Hanoi hanoi, newHanoi;
        String tmp;
        hanoi = new Hanoi(states[0], states[1], states[2]);
        queue.add(hanoi);
        visited.add(hanoi.state());
        char last;
        int count = -1;
        int s;
        while (!queue.isEmpty()) {
            count++;
            s = queue.size();
            for (int i = 0; i < s; i++) {
                hanoi = queue.remove();
                if (hanoi.isEnd()) return count;

                if (hanoi.movableA()) {
                    last = hanoi.A.charAt(hanoi.A.length() - 1);
                    tmp = hanoi.A.substring(0, hanoi.A.length() - 1);
                    newHanoi = new Hanoi(tmp, hanoi.B + last, hanoi.C);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                    newHanoi = new Hanoi(tmp, hanoi.B, hanoi.C + last);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                }

                if (hanoi.movableB()) {
                    last = hanoi.B.charAt(hanoi.B.length() - 1);
                    tmp = hanoi.B.substring(0, hanoi.B.length() - 1);
                    newHanoi = new Hanoi(hanoi.A + last, tmp, hanoi.C);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                    newHanoi = new Hanoi(hanoi.A, tmp, hanoi.C + last);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                }

                if (hanoi.movableC()) {
                    last = hanoi.C.charAt(hanoi.C.length() - 1);
                    tmp = hanoi.C.substring(0, hanoi.C.length() - 1);
                    newHanoi = new Hanoi(hanoi.A, hanoi.B + last, tmp);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                    newHanoi = new Hanoi(hanoi.A + last, hanoi.B, tmp);
                    if (!visited.contains(newHanoi.state())) {
                        visited.add(newHanoi.state());
                        queue.add(newHanoi);
                    }
                }
            }
        }
        return -1;
    }

    private static void initialize() throws IOException {
        String[] inputs;
        count = new int[3];
        states = new String[3];
        visited = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            inputs = br.readLine().split(" ");
            count[i] = Integer.parseInt(inputs[0]);
            if (count[i] == 0) {
                states[i] = "";
                continue;
            }
            states[i] = inputs[1];
        }
    }

}
