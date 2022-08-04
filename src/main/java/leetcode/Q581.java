package leetcode;

public class Q581 {

    public static void main(String[] args) {
        Q581 solution = new Q581();
        System.out.println(solution.findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{1, 8, 3, 4, 5}));
//        System.out.println(solution.findUnsortedSubarray(new int[]{3, 4, 1, 6, 8}));
    }

    public int findUnsortedSubarray(int[] nums) {
        int startIndex = -1;
        int endIndex = -1;
        int max = 0;
        int min = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[max]) {
                endIndex = i;
            }
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[min]) {
                startIndex = i;
            }
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        int answer = endIndex - startIndex + 1;
        if (endIndex == -1 && startIndex == -1) {
            answer = 0;
        }
        System.out.println(startIndex);
        System.out.println(endIndex);
        return answer;
    }
}
