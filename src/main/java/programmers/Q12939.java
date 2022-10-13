package programmers;
import java.util.*;
public class Q12939 {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int min = Integer.parseInt(st.nextToken());
        int max = min;
        int token;
        while (st.hasMoreTokens()) {
            token = Integer.parseInt(st.nextToken());
            min = Math.min(min, token);
            max = Math.max(max, token);
        }
        return min + " " + max;
    }
}
