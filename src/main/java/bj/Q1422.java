package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q1422 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N; //N >= K
    private static int K;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        List<String> numList = new ArrayList<>();
        // K와 N의 길이가 같은경우 - 큰 수를 기준으로 하나씩 뽑아서 쓰면 댐...
        if (K == N) {
            list.forEach(i -> numList.add(String.valueOf(i)));
        } else {
            // 가장 큰 수를 N - K 번 더 사용
            int max = list.get(0);
            list.forEach(i -> numList.add(String.valueOf(i)));
            for (int i = 0; i < N - K; i++) {
                numList.add(String.valueOf(max));
            }
        }

        numList.sort(Q1422::sort);

        for (String s : numList) {
            bw.write(s);
        }

        bw.flush();
        bw.close();
    }

    private static int sort(String a, String b) {
        int tmpA, tmpB;
        if (a.length() == b.length()) {
            return b.compareTo(a);
        }
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            tmpA = Integer.parseInt(String.valueOf(a.charAt(i)));
            tmpB = Integer.parseInt(String.valueOf(b.charAt(i)));
            if (tmpA > tmpB) return -1;
            else if (tmpA < tmpB) return 1;
        }
        String l, s, tmp;
        if (a.length() > b.length()) {
            l = a;
            s = b;
            tmp = l.substring(s.length());
            return sort(tmp, s);
        } else {
            l = b;
            s = a;
            tmp = l.substring(s.length());
            return sort(s, tmp);
        }
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        list.sort(Comparator.reverseOrder());
    }
}
