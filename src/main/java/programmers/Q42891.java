package programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Q42891 {

    public static void main(String[] args) {
        Q42891 s = new Q42891();
//        System.out.println(s.solution(new int[]{3, 1, 2}, 5));
        System.out.println(s.solution(new int[]{3, 1, 3, 2, 1, 5, 4}, 18));
    }

    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> priorityQueue = new PriorityQueue<>(Food::sort);

        for (int i = 0; i < food_times.length; i++) {
            priorityQueue.add(new Food(i, food_times[i]));
        }
        // 일단 k가 food_time보다 작으면 답은 food_times[k]
        if (k < food_times.length) return (int) k + 1;

        // k가 food_time보다 크면 일단 음식 리스트는 무조건 돈다는 뜻..
        // 그때 몇번 돌았는지 체크하는 변수 필요

        // 일단 한 번 돌았다고 가정하고 시작.
        int count = priorityQueue.peek().num;
        long left = k - (long) food_times.length * count;
        while (left >= 0) {
            if (priorityQueue.isEmpty()) {
                return -1;
            }
            // 돌았으니까 다 먹은 거 없애기.
            while (!priorityQueue.isEmpty() && priorityQueue.peek().num == count) {
                priorityQueue.remove();
            }
            // 다시 한 바퀴 돌기.
            count++;
            left = left - priorityQueue.size();
        }
        if (priorityQueue.isEmpty()) {
            return -1;
        }
        int i = (int) Math.abs(left);
        ArrayList<Food> newFoods = new ArrayList<>();
        newFoods.addAll(priorityQueue);
        newFoods.sort(Food::sortByIndex);
        if (newFoods.size() - i < 0) {
            return 1;
        }
        return newFoods.get(newFoods.size() - i).index + 1;
    }

    static class Food {
        int index;
        int num;

        public Food(int index, int num) {
            this.index = index;
            this.num = num;
        }

        public static int sort(Food a, Food b) {
            return Integer.compare(a.num, b.num);
        }

        public static int sortByIndex(Food a, Food b) {
            return Integer.compare(a.index, b.index);
        }
    }
}
