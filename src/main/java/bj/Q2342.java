package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2342 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static ArrayList<Integer> commands;
    private static int[][][] dp; // 왼발 오른발 몇번째 지시인지

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        bw.write(solve(0, 0, 0) + "\n");
        bw.flush();
        bw.close();
    }

    private static int solve(int l, int r, int command) {
        if (command >= commands.size()) return 0;
        if (dp[l][r][command] != 0) return dp[l][r][command];
        int c = commands.get(command);

        // 왼쪽발을 이동함
        int left = solve(c, r, command + 1);
        if (l == 0) left += 2;
        else if (Math.abs(l - c) == 2) left += 4;
        else if (Math.abs(l - c) == 0) left += 1;
        else left += 3;

        // 오른쪽발을 이동함
        int right = solve(l, c, command + 1);
        if (r == 0) right += 2;
        else if (Math.abs(r - c) == 2) right += 4;
        else if (Math.abs(r - c) == 0) right += 1;
        else right += 3;

        return dp[l][r][command] = Math.min(left, right);
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        commands = new ArrayList<>();
        int c;
        while (true) {
            c = Integer.parseInt(st.nextToken());
            if (c == 0) break;
            commands.add(c);
        }
        dp = new int[5][5][commands.size()];
    }
}
