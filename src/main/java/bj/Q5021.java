package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q5021 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int M;
    private static HashMap<String, Double> people;
    private static ArrayList<Family> families;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();
        getFamily();
        bw.write(getNextKing() + "\n");
        bw.flush();
        bw.close();
    }

    private static String getNextKing() throws IOException {
        String king = null, input;
        for (int i = 0; i < M; i++) {
            input = br.readLine();
            if (!people.containsKey(input)) people.put(input, 0.0);
            if (king == null) {
                king = input;
                continue;
            }
            king = people.get(king) > people.get(input) ? king : input;
        }
        return king;
    }

    private static void getFamily() {
        // 처음 입력을 받을땐 왕족이 아닌 상태인데
        // 나중에 그사람이 왕족의 피를 받는 입력이 들어올 수 있기 때문에 모든 가족관계에 대해 갱신이 필요함
        // 근데 N * N 을 해도 50 * 50 이라 2500 (* 연산횟수)
        // 라서 1초 내에 충분히 들어옴
        String child, dad, mom;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                child = families.get(j).child;
                mom = families.get(j).mom;
                dad = families.get(j).dad;
                people.put(child, (people.get(mom) / 2) + (people.get(dad) / 2));
            }
        }
    }

    private static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        people = new HashMap<>();
        people.put(br.readLine(), 1.0);
        String child, dad, mom;
        families = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            child = st.nextToken();
            dad = st.nextToken();
            mom = st.nextToken();
            if (!people.containsKey(child)) people.put(child, 0.0);
            if (!people.containsKey(dad)) people.put(dad, 0.0);
            if (!people.containsKey(mom)) people.put(mom, 0.0);
            families.add(new Family(mom, dad, child));
        }
    }

    static class Family {
        String mom;
        String dad;
        String child;

        public Family(String mom, String dad, String child) {
            this.mom = mom;
            this.dad = dad;
            this.child = child;
        }
    }
}
