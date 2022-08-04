package programmers;

import java.util.*;

public class Q67258 {
    private Map<String, Integer> gemMap;

    public static void main(String[] args) {
        Q67258 s = new Q67258();
        s.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
        s.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "RUBY"});
        s.solution(new String[]{"ZZZ", "ZZZ", "YYY", "NNNN", "YYY", "BBB"});
        s.solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
        s.solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
        s.solution(new String[]{"XYZ", "XYZ", "XYZ"});
    }

    public int[] solution(String[] gems) {
        int r = 0;
        int l = 0;
        int tmpR = 0;
        int tmpL = 0;
        Set<String> gemSet = new HashSet<>();
        gemSet.addAll(List.of(gems));
        int n = gemSet.size();
        gemMap = new HashMap<>();
        // 개수가 부족하면 r 늘리고
        // 개수가 안부족하면 l 늘리고, l 늘렸을때 개수가 부족하면 r 다시 늘리고...
        //
        int count;
        while (tmpL <= tmpR && tmpL < gems.length){
            if (gemMap.size() == n || tmpR == gems.length) {
                // n이랑 같을 때, l를 늘린다.
                count = gemMap.get(gems[tmpL]);
                if (count == 1) {
                    gemMap.remove(gems[tmpL]);
                } else {
                    gemMap.put(gems[tmpL], count - 1);
                }
                tmpL++;
            } else {
                // n보다 작을 떄.. r을 늘린다.
                if (gemMap.containsKey(gems[tmpR])) {
                    gemMap.put(gems[tmpR], gemMap.get(gems[tmpR]) + 1);
                } else {
                    gemMap.put(gems[tmpR], 1);
                }
                tmpR++;
            }
            if (gemMap.size() == n) {
                if (r - l > tmpR - tmpL || (r == 0 && l == 0)) {
                    r = tmpR;
                    l = tmpL;
                }
            }
            if (tmpL == gems.length && gemMap.size() < n) break;
        }

        int[] answer = {l + 1, r};
        return answer;
    }
}
