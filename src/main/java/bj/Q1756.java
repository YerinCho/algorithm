package bj;

import java.io.*;
import java.util.StringTokenizer;

/*
* 이거 문제 이해하는게 제일 어려움 ㅡㅡ
* 백준의 테케 기준으로
* r 는 화덕
* p 는 피자크기
* min[i]는 i번째 위치의 화덕의 윗부분들 중 r[i]보다 작거나 같은 화덕의 크기
* 백준테케 기준
* r : 5 6 4 3 6 2 3
* m : 5 5 4 3 3 2 2
*
* 맨 끝(D) 부터 탐색해나가는데
* min[i] 이 p[i]보다 크면 그 위치에 들어갈수있음
* 그게아니면 탐색계속
*
* 그럼 시간복잡도 N + D 로 풀이 가능
* */

public class Q1756 {
    public static final int MAX = 1000000111;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int D;
    private static int[] r;
    private static int[] min;
    private static int[] p;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve() + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve() {
        for (int i = 1; i <= D; i++) {
            if (i == 1) {
                min[i] = r[i];
                continue;
            }
            min[i] = Math.min(min[i - 1], r[i]);
        }

        int ptr = D;
        boolean canInsert = false;
        for (int i = 1; i <= N; i++) {
            canInsert = false;
            for (int j = ptr; j > 0; j--) {
                if (min[j] < p[i]) continue;
                ptr = j - 1;
                canInsert = true;
                break;
            }
        }
        return canInsert ? ptr + 1 : 0;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        r = new int[D + 1];
        min = new int[D + 1];
        p = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            r[i + 1] = Integer.parseInt(st.nextToken());
            min[i + 1] = MAX;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i + 1] = Integer.parseInt(st.nextToken());
        }
    }
}
