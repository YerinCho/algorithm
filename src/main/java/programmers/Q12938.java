package programmers;

public class Q12938 {
    public int[] solution(int n, int s) {
        if (s < n) return new int[]{-1};
        int mid = s / n;
        int sum = mid * n;
        int diff = s - sum;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            if (i >= diff) answer[n - i - 1] = mid;
            else answer[n - i - 1] = mid + 1;
        }
        return answer;
    }
}
