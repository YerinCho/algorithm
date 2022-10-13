package programmers;

public class Q42860 {
    char[] chars;
    public int solution(String name) {
        chars = name.toCharArray();
        int answer = 0;
        int tmp;
        int aCount;
        for (int i = 0; i < name.length(); i++) {
            tmp = Math.abs(chars[i] - 'A');
            answer += Math.min(26 - tmp, tmp);
        }
        tmp = 0;
        int cntA = 0;
        int cntB = 0;
        for (int i = 1; i < name.length(); i++) {
            tmp++;
            if (chars[i] != 'A') cntA = tmp;
        }

        tmp = 0;
        for (int i = name.length() - 1; i > 0; i--) {
            tmp++;
            if (chars[i] != 'A') cntB = tmp;
        }
        int move = Math.min(cntA, cntB);


        answer += move;
        return answer;
    }
}
