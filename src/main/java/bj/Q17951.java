package bj;

import java.io.*;
import java.util.ArrayList;

public class Q17951 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int K;
    private static int sum;
    private static ArrayList<Integer> scores;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        int result = binarySearch(0, sum);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int binarySearch(int min, int max) {
        int mid;
        int result = 0;
        while (min <= max) {
            mid = (min + max) / 2;
            if (canK(mid)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    // 문제의 합이 mid이상이 될 때 K개가 나오냐..
    private static boolean canK(int mid) {
        int k = K;
        int s = 0;
        for (int i = 0; i < N; i++) {
            s += scores.get(i);
            if (s >= mid) {
                k--;
                s = 0;
            }
            if (k == 0) return true;
        }
        return false;
    }

    private static void initialize() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);
        String[] input = br.readLine().split(" ");
        scores = new ArrayList<>();
        sum = 0;
        for (int i = 0; i < N; i++) {
            scores.add(Integer.parseInt(input[i]));
            sum += scores.get(i);
        }
    }
}
