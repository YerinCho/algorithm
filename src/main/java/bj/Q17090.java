package bj;

import java.io.*;

public class Q17090 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int M;
    private static int N;
    private static final char U = 'U';
    private static final char R = 'R';
    private static final char D = 'D';
    private static final char L = 'L';
    private static char map[][];
    private static int check[][]; // 0 == 아직 안탐색 , 1 == 탈출가능 , -1 == 탈출불가능
    private static boolean visited[][];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        // 한 칸에서 이동을 시작했을 때
        // 탈출 성공한 경우 : 지나온 모든 칸에서 탈출이 가능한
        // 탈출 실패한 경우 - 지나온 칸을 또 마주했을 때 탈출이 실패한 것, 그 지나온 칸에서 탈출이 불가능함
        int count = 0;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[x][y]) {
                    check[x][y] = search(y, x);
                }
            }
        }

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (check[x][y] == 1) count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static int search(int x, int y) {
        // 길에는 3가지의 경우가 잇다...
        // 아직 탐색을 못한 길, 지나가봤자 탈출이 불가능한 길, 지나가면 무조건 탈출이 가능한 길.
        // 이 세가지경우를 확인해주면 굳이 탐색을 모두 할 필요ㄱ ㅏ 없어지는데 ...
        // 예를ㄷ르어 이번 탐색때 길 뭐 30개를 탐색햇음. 근데 탈출이 가능하게 되엇다고 치자.. 그럼 이번에 돌앗던 길들을 뭔가 저장을 해줘야 탐색가능한 길이라고 바꾸고 그 담 탐색을 진행할거아니냐?
        // 그래서 이번에 탐색을 하는 경로의 저장을 어느식으로 할지 고민이 좀 댐
        // 이거만 해결하면 금방 풀 거 같은디...
        // 재귀로 풀면 재귀를 탈출하면서 값을 변경해주면 되지 안을까...??
        int dx = x;
        int dy = y;
        char m = map[y][x];
        if (m == U) {
            dy--;
        }
        if (m == R) {
            dx++;
        }
        if (m == D) {
            dy++;
        }
        if (m == L) {
            dx--;
        }
        visited[y][x] = true;
        if (isOutOfMap(dx, dy)) {
            check[y][x] = 1;
            return 1;
        }
        if (check[dy][dx] == -1) {
            check[y][x] = -1;
            return -1;
        }
        if (check[dy][dx] == 1) {
            check[y][x] = 1;
            return 1;
        }

        // 방문한적은 있지만 이번 탐색에 포함되어잇기 때문에 결국 뻉뺑이 경로라 탐색 불가능으로 결론남
        if (visited[dy][dx] && check[dy][dx] == 0) {
            check[y][x] = -1;
            return -1;
        }
        check[y][x] = search(dx, dy);
        return check[y][x];
    }

    private static boolean isOutOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= M || y >= N;
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        visited = new boolean[N][M];
        check = new int[N][M];
        char[] s;
        for (int y = 0; y < N; y++) {
            s = br.readLine().toCharArray();
            if (M >= 0) System.arraycopy(s, 0, map[y], 0, M);
        }
    }
}
