package programmers;

public class Q12911 {
    public int solution(int n) {
        int one = 0;
        int tmp = n;;
        while(n > 0) {
            if ((n % 2) == 1) one++;
            n /= 2;
        }

        n = tmp;
        while(true) {
            int count = 0;
            n++;
            tmp = n;
            while(tmp > 0) {
                if ((tmp % 2) == 1) count++;
                tmp /= 2;
                if (count > one) break;
            }
            if (one == count) return n;
        }
    }
}
