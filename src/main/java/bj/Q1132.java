package bj;

import java.io.*;
import java.util.*;

public class Q1132 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static final char nullChar = '\u0000';
    private static int N;
    private static long maxSum = 0;
    private static char[][] alphabets;
    private static Map<Character, Long> alWeight = new HashMap<>();
    private static Map<Character, Integer> alNum = new HashMap<>();
    private static List<Character> canNotZero = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        for (int i = 0; i < N; i++) {
            long tmp = 0;
            for (int j = 0; j < 12; j++) {
                if (alphabets[i][j] == nullChar) continue;
                if (tmp == 0) canNotZero.add(alphabets[i][j]);
                tmp = (long) Math.pow(10, 12 - j - 1);
                if (alWeight.containsKey(alphabets[i][j])) {
                    tmp += alWeight.get(alphabets[i][j]);
                }
                alWeight.put(alphabets[i][j], tmp);
            }
        }
        var ref = new Object() {
            int n = 9;
        };

        // 0 자리 만들기
        if (alWeight.size() > 9) {
            Map.Entry<Character, Long> zero = alWeight.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .filter((a) -> !canNotZero.contains(a.getKey()))
                    .findFirst()
                    .get();

            alNum.put(zero.getKey(), 0);
            alWeight.remove(zero.getKey());
        }
        alWeight.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach((a) -> {
                    alNum.put(a.getKey(), ref.n);
                    ref.n--;
                });

        for (Map.Entry<Character, Integer> a : alNum.entrySet()) {
            if (!alWeight.containsKey(a.getKey())) continue;
            maxSum += alWeight.get(a.getKey()) * a.getValue();
        }

        bw.write(maxSum + "\n");
        bw.flush();
        bw.close();
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        alphabets = new char[N][12];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char c;
            for (int j = 0; j < s.length(); j++) {
                c = s.charAt(j);
                alphabets[i][12 - s.length() + j] = c;
            }
        }
    }
}
