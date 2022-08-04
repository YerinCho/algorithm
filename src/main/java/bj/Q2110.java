package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Q2110 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int C;
    private static List<Integer> locations;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        // 즉, 인접한 두 공유기 사이의 거리들의 최소값이 최대가 되는 경우를 찾는 것이 문제
        // 거리가 K(이상)일 때 C개의 공유기를 설치할 수 있을까? 로 바꾸기
        // 이때 K값을 변경해가면서 값을 찾기.
        // 모든 값을 해보기에는... K값의 범위가 1부터 10억까지 너무 크다 => 완전탐색은 불가능.


        // 거리가 K일 때 최대로 설치할 수 있는 공유기의 개수는?
        // 거리가 K 일 때로 탐색하려고 했는데, 그 경우 딱 K 가 되느 ㄴ경우를 찾는게 경우의 수가 꽤 되어서 K이상일 때 true처리해버리고
        // 그 이상도 되는지 확인하는게 맞는듯.

        int result = binarySearch(1, locations.get(N - 1) - locations.get(0));

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int binarySearch(int min, int max) {
        int mid;
        int result = 1;
        while (min <= max) {
            mid = (max + min) / 2;
            if (validate(mid)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    private static boolean validate(int k) {
        int c = C;
        int a, b;
        int i = 0;
        while (i < N - 1) {
            a = locations.get(i);
            for (int j = i + 1; j < N; j++) {
                i = j;
                b = locations.get(j);
                if (b - a < k) {
                    continue;
                }
                if (b - a >= k) {
                    // j 집에 공유기 설치 가능..
                    c--;
                    break;
                }
            }
            if (c == 0) {
                // K 거리 이상으로 공유기 C개를 모두 설치 가능!
                return true;
            }
        }
        return false;
    }

    private static void initialize() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        locations = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            locations.add(input);
        }
        locations.sort(Comparator.naturalOrder());
        C -= 1; // 일단 첫 집에 공유기 설치함
    }
}
