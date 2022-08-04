package bj;

import java.io.*;
import java.util.Arrays;

public class Q1102 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int P;
    private static int costs[][];
    // Integer.Max 로 하면 오버플로 일어나는듯 바꿔주니까 맞음.. 이거 최대값이 600을 안넘는걸로 기억...
    private static int MAX = 10000;
    private static int dp[];
    private static int state = 0;
    private static int Y = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        int cost = 0;
        // P개의 발전소가 돌아가려면 P-1개의 발전소가 돌아가는 상태에서 최소로 들어가는 비용을 선택해야함...
        // P-1개의 발전소가 돌아가려면 P-2개의 발전소가 돌아가는 상태에서 최소로 돌아가는 비용을 선택해야함
        // 근데 단순히 위처럼 생각해버리면... 이게 P-2개의 발전소가 돌아가는 상태에서 좀 비싼 애를 선택햇는데 그담에 선택할 애들이 더 싸서
        // 최종 합이 더 싼 경우가 나올 수 잇을거같음 그럼 결국 모든 경우를 따져봐야한다는건데...


        // 그리고 P-2개의 발전소가 켜져잇고 그담꺼를 하나 킬 때 어느 발전소를 키냐에 따라 또 달라짐... 이걸 어케표현함?
        // 즉, 1. 현재 돌아가고잇는 발전소의 상태(켜져잇는 개수랑은 상관없음.)
        // 그리고 2. 그 때 얼마나 비용을 사용햇는지 이 두 정보가 필요
        // 맵을 쓰기에는 최대 2^16 개의 키값이 들어가서... 65536 개? 될지도 모르겟다는 생각...
        // 그리고 키값이 스트링일거니까 스트링 계산도 좀 구현이 귀찮을거같음
        // 하지만... 많은 건 맞는거같고.. 결국 켜짐/안켜짐 두 상태에 대한 최대 16개 원소 집합이니까 16개 비트의 비트마스킹으로 가능함.
        // 상태가 켜짐/안켜짐 두개라서 비트로 표현이 가능한것. 절반만켜짐 뭐 그런거도 있었으면 다른 방안을 써야 햇겟지...
        // 즉, dp[발전소의 상태를 비트로 표현] = 이 상태일 때, P까지 키기 위한 최소 비용
        // 의 방식으로 풀면 풀이가 가능할 것 가틈.

        // 초기화는 못만드는 경우 -1 출력하라햇으니 -1..
        Arrays.fill(dp, -1);

        cost = calculate(state, Y);

        if (cost == MAX) cost = -1;
        bw.write(cost + "\n");
        bw.flush();
        bw.close();
    }

    private static int calculate(int s, int count) {
        if (count >= P) {
            return 0;
        }
        if (dp[s] != -1) {
            return dp[s];
        }
        int r = MAX;
        for (int i = 0; i < N; i++) {
            // i 번째의 발전소가 ㄲㅓ져잇으면 걔를 키는 작업을 하자
            if ((s & (1 << i)) == 0) {
                // i번째 발전소를 킨 상태가 다음 상태가 된다
                // 앗.. s 말고 전역변수 state 쓰다가 틀린거 발견못하고 맞왜틀 햇네
                int next = s | (1 << i);
                int min = MAX;
                for (int j = 0; j < N; j++) {
                    // j번째 발전소가 켜져잇어야 i번째 발전소를 킬 수 있으니 켜져잇는걸 찾는다
                    if ((s & (1 << j)) != 0) {
                        // 그리고 모든 경우를 돌아서 최소값을 찾기
                        min = Math.min(costs[j][i], min);
                    }
                }
                // 몇번째 발전소를 키든간에 일단 전부다 돌아봐야 어느게 최소인지 알게댐.. 그래서 다돈담에 최소값을 선택하는것
                r = Math.min(r, calculate(next, count + 1) + min);
            }
        }
        dp[s] = r;
        return dp[s];
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        dp = new int[1 << N];
        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(input[j]);
            }
        }
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'Y') {
                state = state | (1 << i);
                Y++;
            }
        }
        P = Integer.parseInt(br.readLine());
    }
}
