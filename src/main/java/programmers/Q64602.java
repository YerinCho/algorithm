package programmers;

public class Q64602 {

    private static int[] stoneList;
    private static int K;

    public static void main(String[] args) {
        Q64602 solution = new Q64602();
//        System.out.println(solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 8, 1}, 3));
//        System.out.println(solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 3, 1}, 3));
        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 2, 4, 5, 3, 2, 1, 4, 2, 5, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1}, 7));
        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 2, 4, 5, 3, 2, 1, 4, 2, 5, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1}, 8));
        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 2, 4, 5, 3, 2, 1, 4, 2, 5, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1}, 12));
        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 2, 4, 5, 3, 2, 1, 4, 2, 5, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1}, 13));
    }

    public int solution(int[] stones, int k) {
        // K칸을 기준으로 해서 그 중의 최대값이 최소값이 되는 경우를 찾기.
        // 걍 단순한 완탐으로 찾으면 O(M * K) 최대 20만 * 20만... 말이 안되게 숫자가 크다

        stoneList = new int[stones.length];
        K = k;
        int max = stones[0];
        for (int i = 0; i < stones.length; i++) {
            stoneList[i] = stones[i];
            if (max < stones[i]) max = stones[i];
        }

        return binarySearch(1, max);
    }

    //m 값이 답이 된다고 할 때 그 경우 K칸씩 건너뛰기가 되는지
    private boolean canK(int m) {
        int i = 0;
        int k = 0;
        while (i < stoneList.length) {
            k++;
            if (stoneList[i] >= m) {
                if (k > K) return false;
                k = 0;
            }
            i++;
        }
        if (k >= K) return false;
        return true;

//        int max = -1;
//        int i = 0;
//        while (i < stoneList.length) {
//            if (i >= stoneList.length - K) {
//                max = stoneList[stoneList.length - 1];
//                for (int j = stoneList.length - 1; j >= stoneList.length - K; j--) {
//                    max = Math.max(max, stoneList[j]);
//                }
//                return max >= m;
//            } else {
//                if (stoneList[i] >= m) {
//                    i++;
//                    continue;
//                }
//                max = stoneList[i];
//                for (int j = i + 1; j < i + K; j++) {
//                    if (j >= stoneList.length) break;
//                    max = Math.max(max, stoneList[j]);
//                }
//                if (max < m) {
//                    return false;
//                }
//                i = i + K;
//            }
//        }
//        return true;
    }

    private int binarySearch(int min, int max) {
        int mid;
        int result = 1;
        while (min <= max) {
            mid = (min + max) / 2;
            if (canK(mid)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }
}
