package programmers;

public class Q12924 {
    public int solution(int n) {
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i ++) {
            sum[i] = sum[i - 1] + i;
        }

        int answer = 0;
        for(int i = 1; i<= n; i++) {
            if (sum[i] < n) continue;
            if (sum[i] == n) {
                answer++;
                continue;
            }
            for(int j = i - 1; j >= 1; j--) {
                if (sum[i] - sum[j] < n) continue;
                if (sum[i] - sum[j] > n) break;
                if (sum[i] - sum[j] == n) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}
