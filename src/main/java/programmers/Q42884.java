package programmers;

import java.util.PriorityQueue;

public class Q42884 {
    public static void main(String[] args) {
        Q42884 solution = new Q42884();
        System.out.println(solution.solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }

    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Route> routeList = new PriorityQueue<>(Route::sortByEnd);
        for (int[] route : routes) {
            routeList.add(new Route(route[0], route[1]));
        }
        if (routeList.size() < 2) {
            return 1;
        }

        Route next;
        Route route = routeList.remove();
        int camera = route.end;
        answer++;
        while (!routeList.isEmpty()) {
            next = routeList.remove();
            if (next.start > camera) {
                answer++;
                camera = next.end;
            }
        }
        return answer;
    }

    public static class Route {
        int start;
        int end;

        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int sortByEnd(Route route) {
            int s = Integer.compare(end, route.end);
            if (s == 0) return Integer.compare(start, route.start);
            return s;
        }

        @Override
        public String toString() {
            return "Route{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
