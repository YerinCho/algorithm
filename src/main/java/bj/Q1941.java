package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Q1941 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};
    private static char[][] graph;
    private static boolean[][] visited = new boolean[5][5];
    private static boolean[][] contains = new boolean[5][5];
    private static int count = 0;
    private static int c = 0;
    private static List<List<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        // nCr, n = 25, r = 7

        getCombination(0, 7);
        for (List<Integer> n : combinations) {
            c = 0;
            if (is7(n)) count++;
        }
//        System.out.println(combinations.size());

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static void getCombination(int k, int r) {
        if (r == 0) {
            List<Integer> list = new ArrayList<>();
            int x, y;
            for (int i = 0; i < 25; i++) {
                x = i % 5;
                y = i / 5;
                if (visited[y][x]) list.add(i);
            }
            if (list.size() == 7) {
                combinations.add(list);
            }
            return;
        }

        int x, y;
        for (int i = k; i < 25; i++) {
            x = i % 5;
            y = i / 5;
            visited[y][x] = true;
            getCombination(i + 1, r - 1);
            visited[y][x] = false;
        }
    }

    private static boolean is7(List<Integer> nodes) {
        // 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
        // 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
        // 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
        // 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.

        // 5*5 의 서로다른 7개의 (x, y)쌍 경우의 수를 찾는다... 그리고 그 경우에 대해 검증하도록 관점을 변경
        // 즉 일단 저지르고 해결하는거
        // 원래는 완전한 칠공주를 만들려고 햇으나... 못찾거나 / ㅜ ㅗ ㅏ ㅓ 뭐 이런식으로 생긴거를 전부 탐색하는게 불가능함...

        contains = new boolean[5][5];
        visited = new boolean[5][5];
        int countS = 0;
        for (Integer n : nodes) {
            contains[n / 5][n % 5] = true;
            if (graph[n / 5][n % 5] == 'S') {
                countS++;
            }
        }
        // 5 6 7 8 9 11 16
        // 이다솜파가 4명이 안넘으면 ㄴㄴ
        if (countS < 4) return false;

        for (int i = 0; i < 25; i++) {
            if (contains[i / 5][i % 5]) {
                search(i % 5, i / 5);
                break;
            }
        }
//        if (c == 7) {
//            for (int i = 0; i < 25; i++) {
//                System.out.print(i);
//                if (i % 5 == 4) System.out.println();
//            }
//            System.out.println();
//        }
        return c == 7;
    }

    private static void search(int x, int y) {
        visited[y][x] = true;
        c++;
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (isOutOfMap(nx, ny) || visited[ny][nx] || !contains[ny][nx]) continue;
            search(nx, ny);
        }
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x > 4 || y > 4;
    }

    private static void initialize() throws IOException {
        graph = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                graph[i][j] = input.charAt(j);
            }
        }
    }
}

//YYYYY
//SYSYS
//YYYYY
//YSYYY
//YYYYY
