package programmers;

import java.util.Arrays;

public class Q12941 {
    public int solution(int[] A, int[] B) {
        int[] AList = Arrays.stream(A).sorted().toArray();
        int[] BList = Arrays.stream(B).sorted().toArray();
        int answer = 0;

        for (int i = 0; i < A.length; i++) {
            answer+= AList[i] * BList[B.length - i - 1];
        }

        return answer;
    }
}
