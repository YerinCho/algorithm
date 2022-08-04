package programmers;

import java.util.*;

public class Q72415 {
    public static int[] dx = new int[]{0, -1, 1, 0};
    public static int[] dy = new int[]{-1, 0, 0, 1};
    // 카드 쌍의 개수
    private static int N;
    List<int[]> permutations = new ArrayList<>();
    private static int map[][];
    private static Map<Integer, List<Location>> cardLocations = new HashMap<>();
    private static boolean visited[][];

    static class Location {
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Q72415 solution = new Q72415();
        int a = solution.solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0);
        System.out.println(a);
    }

    // r -> 최초 커서의 row , c -> 최초 커서의 column
    public int solution(int[][] board, int r, int c) {
        int answer = -1;
        map = board;

        // 카드 쌍의 개수 몇 개인지 계산
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    N++;
                    if (cardLocations.containsKey(board[i][j])) {
                        List<Location> tmp = cardLocations.get(board[i][j]);
                        tmp.add(new Location(j, i));
                    } else {
                        cardLocations.put(board[i][j], Collections.singletonList(new Location(j, i)));
                    }
                }
            }
        }
        N = N / 2;

        // 모든 카드 쌍의 순서 경우의 수 구하기
        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i + 1;
        }
        permute(p, 0, N);

        for (int[] permutation : permutations) {
            // 주어진 카드 순서에 대해서 얼마나 걸리는지 구하기
            int count = calculate(permutation, r, c);
            answer = answer == -1 ? count : Math.min(answer, count);
        }

        return answer;
    }

    private int calculate(int[] permutation, int r, int c) {
        // 어느 위치에 있는 것을 먼저 뒤집을 지... 도 고려해야한다...
        visited = new boolean[4][4];
        int[][] cMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cMap[i][j] = map[i][j];
            }
        }
        List<Location> cardLocation = cardLocations.get(permutation[0]);
        List<int[]> distances = new ArrayList<>();
        int[] distanceList = new int[(int) Math.pow(2, N)];
        for (int i = 0; i < N; i++) {
            distances.add(new int[4]);
        }
        for (int i = 0; i < permutation.length; i++) {
            // d[0] -> 1번카드먼저뒤집었을때
            // d[1] -> 2번카드먼저뒤집었을때
            if (i == 0) {
                int[] d = getDistance(cardLocation, cMap, new Location(c, r));
                distanceList[0] = d[0];
                distanceList[N / 2] = d[1];
                cMap[cardLocation.get(0).y][cardLocation.get(0).x] = 0;
                cMap[cardLocation.get(1).y][cardLocation.get(1).x] = 0;
                continue;
            }
            List<Location> newCardLocation = cardLocations.get(permutation[i]);
            int[] d = new int[4];
            int[] tmp = getDistance(newCardLocation, cMap, cardLocation.get(0));
            d[0] = tmp[0];
            d[1] = tmp[1];
            tmp = getDistance(newCardLocation, cMap, cardLocation.get(1));
            d[2] = tmp[0];
            d[3] = tmp[1];
            distances.add(d);
            cardLocation = newCardLocation;
            cMap[cardLocation.get(0).y][cardLocation.get(0).x] = 0;
            cMap[cardLocation.get(1).y][cardLocation.get(1).x] = 0;

//            distances[]
        }

        return r;
    }

    // distance[0] -> 첫번째카드먼저 뒤집었을때
    // distance[1] -> 두번째카드먼저 뒤집었을때
    private int[] getDistance(List<Location> cardLocation, int[][] cMap, Location cursor) {

        return null;
    }

    private void permute(int[] arr, int depth, int n) {
        if (depth == N) {
            int[] t = new int[N];
            if (N >= 0) System.arraycopy(arr, 0, t, 0, N);
            permutations.add(t);
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(arr, i, depth);
            permute(arr, depth + 1, n);
            swap(arr, i, depth);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

//import java.util.*;
//
//class Solution {
//    ArrayList<int[]> orders;
//    int[] dr = {1, -1, 0, 0};
//    int[] dc = {0, 0, 1, -1};
//
//    public int solution(int[][] board, int r, int c) {
//        int answer = Integer.MAX_VALUE;
//        int n = 0;
//        for (int[] row : board) {//개수세기
//            for (int e : row) {
//                if (e != 0) n++;
//            }
//        }
//        n /= 2;//짝 개수
//        int[] temp = new int[n];//12345...n
//        for (int i = 0; i < n; i++) {
//            temp[i] = i + 1;
//        }
//        orders = new ArrayList<>();//순열로 123 312 213 231..짝 뽑는순서
//        permutation(n, n, new int[n], temp, 0, 0);
//
//        for (int[] order : orders) {
//            int total = 0;
//            int[] point = new int[2];//최초커서위치 (r,c)
//            point[0] = r;
//            point[1] = c;
//            int[][] cBoard = new int[4][4];//grid 만들기
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    cBoard[i][j] = board[i][j];
//                }
//            }
//            for (int card : order) {//int[]인 order 즉, 순열로 나열한 순서 한가지씩. 123, 132, ...
//                int cnt = 0;
//                //목표 카드 찾기
//                cnt += bfs(cBoard, card, point) + 1;
//                //목표 카드 선택
//                cBoard[point[0]][point[1]] = 0;
//                System.out.println(point[0] + "," + point[1]);
//                //짝 찾기
//                cnt += bfs(cBoard, card, point) + 1;
//                //짝 찾기 성공
//                cBoard[point[0]][point[1]] = 0;
//                System.out.println(point[0] + "," + point[1]);
//
//                total += cnt;
//            }
//            System.out.println("total : " +total);
//            System.out.println("---");
//            answer = Math.min(total, answer);
//        }
//
//        return answer;
//    }
//
//    private int bfs(int[][] board, int target, int[] point) {
//        int r = point[0];
//        int c = point[1];
//        Queue<int[]> que = new LinkedList<>();
//        boolean[][] visited = new boolean[4][4];
//
//        que.offer(new int[]{r, c, 0});
//        visited[r][c] = true;
//
//        while (!que.isEmpty()) {
//            int[] p = que.poll();
//            if (board[p[0]][p[1]] == target) {
//                point[0] = p[0];
//                point[1] = p[1];
//                return p[2];
//            }
//            //4방 탐색
//            for (int d = 0; d < 4; d++) {
//                int nr = p[0] + dr[d];//direction 상하좌우 탐색
//                int nc = p[1] + dc[d];//direction 상하좌우 탐색
//                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
//                    visited[nr][nc] = true;
//                    que.offer(new int[]{nr, nc, p[2] + 1});
//                }
//            }
//
//            //ctrl + 4방 탐색
//            for (int d = 0; d < 4; d++) {
//                int[] result = findCard(board, p[0], p[1], d);
//                if ((result[0] != p[0] || result[1] != p[1]) && !visited[result[0]][result[1]]) {
//                    visited[result[0]][result[1]] = true;
//                    que.offer(new int[]{result[0], result[1], p[2] + 1});
//                }
//            }
//        }
//        return 0;
//    }
//
//    private int[] findCard(int[][] board, int r, int c, int d) {
//        r += dr[d];
//        c += dc[d];
//        while (r >= 0 && r < 4 && c >= 0 && c < 4) {
//            if (board[r][c] != 0) {
//                return new int[]{r, c};
//            }
//            r += dr[d];
//            c += dc[d];
//        }
//        return new int[]{r - dr[d], c - dc[d]};
//    }
//
//    private void permutation(int n, int r, int[] perms, int[] input, int depth, int flag) {
//        if (depth == r) {
//            int[] temp = new int[n];
//            System.arraycopy(perms, 0, temp, 0, n);
//            orders.add(temp);
//            return;
//        }
//        for (int i = 0; i < n; i++) {
//            if ((flag & (1 << i)) == 0) {
//                perms[depth] = input[i];
//                permutation(n, r, perms, input, depth + 1, flag | (1 << i));
//            }
//        }
//    }
//}
