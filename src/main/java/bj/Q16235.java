package bj;

import java.io.*;
import java.util.*;

public class Q16235 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    public static int[] dx = new int[]{-1, 0, 1, 0, 1, -1, -1, 1};
    public static int[] dy = new int[]{0, -1, 0, 1, 1, -1, 1, -1};
    private static int N;
    private static int M;
    private static int K;
    private static int[][] A;
    private static int[][] land;
    private static Deque<Tree>[][] trees;

    // 우선순위 큐로 풀었는데 이거 언어빨 타서 통과한거같고 시간초과 나는게 맞음
    // 근데 생각해보면 이거 정렬이 필요 없는 문제임
    // 위치마다 최초 나무는 무조건 1개이고, 그 다음 생성되는 나무는 무조건 1, 그리고 나무는 뒤지거나 나이먹거나로 한정되어 있기 때문에
    // 그냥 쭉 넣으면 됨. 근데 이제 그냥 ArrayList를 쓰게 되면 뒤진나무 처리나, 응애나무 넣을때 N 만큼 걸리는데
    // 그냥 덱를 쓰면 응애나무 넣는데 O(1), 뒤진나무 처리에도 O(1) 의 시간으로 해결이 가능
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        for (int i = 0; i < K; i++) {
            springAndSummer();
            autumnAndWinter();
        }
        bw.write(getTreeCount() + "\n");
        bw.flush();
        bw.close();
    }

    private static int getTreeCount() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count += trees[j][i].size();
            }
        }
        return count;
    }

    private static void springAndSummer() {
        int newLand;
        Tree tree;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                newLand = 0;
                Deque<Tree> newTrees = new LinkedList<>();
                while (!trees[i][j].isEmpty()) {
                    tree = trees[i][j].remove();
                    if (tree.age <= land[i][j]) {
                        land[i][j] -= tree.age;
                        tree.age++;
                        newTrees.addLast(tree);
                        continue;
                    }
                    newLand += tree.age / 2;
                }
                trees[i][j] = newTrees;
                land[i][j] += newLand;
            }
        }
    }

    private static void autumnAndWinter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Tree tree : trees[i][j]) {
                    if (tree.age % 5 != 0) continue;
                    for (int k = 0; k < 8; k++) {
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;
                        if (isOutOfMap(nx, ny)) continue;
                        trees[nx][ny].addFirst(new Tree(1));
                    }
                }
                land[i][j] += A[i][j];
            }
        }
    }

    private static boolean isOutOfMap(int x, int y) {
        return x <= 0 || y <= 0 || x > N || y > N;
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1][N + 1];
        land = new int[N + 1][N + 1];
        trees = new Deque[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                land[i][j] = 5;
                trees[i][j] = new LinkedList<>();
            }
        }
        int x, y, z;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(z));
        }
    }

    static class Tree {
        int age;

        public Tree(int age) {
            this.age = age;
        }

        public int compareByAge(Tree t1) {
            return Integer.compare(this.age, t1.age);
        }
    }
}
