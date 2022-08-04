package bj;

import java.io.*;
import java.util.Map;

public class Q15961 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N; // 접시 수
    private static int d; // 초밥 종류 개수
    private static int k; // 연속 단위
    private static int c; // 쿠폰 초밥
    private static int[] belt;
    private static int[] sushi;
    private static Map<Integer, Integer> sushiMap;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int answer = eatMax();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static int eatMax() {
        int maxSushiCount = 0;
        boolean coupon = false;
        for (int i = 0; i < k; i++) {
            if (sushi[belt[i]] == 0) maxSushiCount++;
            sushi[belt[i]]++;
        }
        if (sushi[c] == 0) {
            maxSushiCount++;
            coupon = true;
        }

        int end;
        for (int i = 0; i < N; i++) {
            end = (i + k) % N;
            sushi[belt[i]]--;
            if (sushi[belt[i]] == 0) {
                maxSushiCount--;
            }

            if (sushi[belt[end]] == 0) maxSushiCount++;
            sushi[belt[end]]++;
           if (sushi[c] == 0) {
               if (!coupon) {
                   maxSushiCount++;
                   coupon = true;
               }
           }
           if (sushi[c] != 0 && coupon) {
               coupon = false;
               maxSushiCount--;
           }
        }

        return maxSushiCount;
    }

    private static void initialize() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        d = Integer.parseInt(inputs[1]);
        k = Integer.parseInt(inputs[2]);
        c = Integer.parseInt(inputs[3]);
        belt = new int[N];
        sushi = new int[d + 1];
        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
    }
}
