package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Q3190 {
    private static int N; // 보드의 크기 N * N
    private static int K; // 사과의 개수
    private static int L; // 뱀이 방향을 바꾸는 횟수
    private static List<List<Integer>> locationApple = new ArrayList<>();
    private static Map<Integer, String> movement = new HashMap<>();
    private static Queue<Location> snake = new LinkedList<>();
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        initialize();
        Location next = new Location(2, 1);
        int time = 0;
        String direction = "R"; // R, L, U, D
        snake.add(new Location(1, 1));
        boolean finished = false;
        while (!finished) {
            time++;
            if (!isNextApple(next.x, next.y)) {
                snake.remove();
            }
            snake.add(new Location(next.x, next.y));
            direction = setDirection(time, direction);
            getNext(next, direction);
            finished = finish(next);
        }
        time++;
        bw.write(time + " ");

        bw.flush();
        bw.close();
    }

    private static String setDirection(int time, String direction) {
        String newDirection = direction;
        if (movement.containsKey(time)) {
            String s = movement.get(time);
            if (s.equals("L")) {
                if (direction.equals("R")) {
                    newDirection = "U";
                }
                if (direction.equals("L")) {
                    newDirection = "D";
                }
                if (direction.equals("U")) {
                    newDirection = "L";
                }
                if (direction.equals("D")) {
                    newDirection = "R";
                }
            } else {
                if (direction.equals("R")) {
                    newDirection = "D";
                }
                if (direction.equals("L")) {
                    newDirection = "U";
                }
                if (direction.equals("U")) {
                    newDirection = "R";
                }
                if (direction.equals("D")) {
                    newDirection = "L";
                }
            }
        }
        return newDirection;
    }

    private static void getNext(Location next, String direction) {
        if (direction.equals("R")) {
            next.x++;
        }
        if (direction.equals("L")) {
            next.x--;
        }
        if (direction.equals("U")) {
            next.y--;
        }
        if (direction.equals("D")) {
            next.y++;
        }
    }

    private static boolean isNextApple(int nextX, int nextY) {
        boolean ret = locationApple.get(nextX).get(nextY) == 1;
        if (ret)
            locationApple.get(nextX).set(nextY, 0);
        return ret;
    }

    private static boolean finish(Location next) {
        if (next.x > N || next.y > N || next.x < 1 || next.y < 1)
            return true;
        return snake.stream().anyMatch(l -> l.x == next.x && l.y == next.y);
    }

    private static void initialize() throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < N + 1; i++) {
            locationApple.add(new ArrayList<>());
        }
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                locationApple.get(i).add(0);
            }
        }
        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            locationApple.get(Integer.parseInt(input[1])).set(Integer.parseInt(input[0]), 1);
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            String[] input = br.readLine().split(" ");
            movement.put(Integer.parseInt(input[0]), input[1]);
        }
    }

    static class Location {
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
