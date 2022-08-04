package bj;

import java.io.*;
import java.util.*;

public class Q3151 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static List<Integer> coding = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        long answer = 0L;
        int now;
        int left, right;
        int sum;
        for (int i = 0; i < N; i++) {
            now = coding.get(i);
            if (now > 0) break;
            left = i + 1;
            right = N - 1;
            while (left < right) {
                sum = now + coding.get(left) + coding.get(right);
                if (sum < 0) {
                    left++;
                    continue;
                }
                if (sum > 0) {
                    right--;
                    continue;
                }
                // sum == 0
                if (Objects.equals(coding.get(left), coding.get(right))) {
                    int count = right - left + 1;
                    answer += (count * (count - 1)) / 2;
                    break;
                }
                int l = 1;
                while (Objects.equals(coding.get(left), coding.get(left + 1))) {
                    left++;
                    l++;
                }
                int r = 1;
                while (Objects.equals(coding.get(right), coding.get(right - 1))) {
                    right--;
                    r++;
                }
                answer += l * r;
                left++;
                right--;
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        Arrays.stream(br.readLine().split(" "))
                .forEach(i -> coding.add(Integer.parseInt(i)));
        coding.sort(Comparator.naturalOrder());
    }
}

