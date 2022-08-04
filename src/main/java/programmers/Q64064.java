package programmers;

import java.util.HashSet;
import java.util.Set;

public class Q64064 {
    private static Set<Integer> bannedUser = new HashSet<>();
    private static boolean[] visited;
    private static String[] userId, bannedId;
    private static int answer;

    public static void main(String[] args) {
        Q64064 solution = new Q64064();
        int a = solution.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"});
        System.out.println(a);
    }

    public int solution(String[] user_id, String[] banned_id) {
        userId = new String[user_id.length];
        bannedId = new String[banned_id.length];
        answer = 0;
        System.arraycopy(user_id, 0, userId, 0, user_id.length);
        System.arraycopy(banned_id, 0, bannedId, 0, banned_id.length);
        visited = new boolean[user_id.length];
        dfs(0, 0);
        return answer;
    }

    private void dfs(int idx, int bannedUsers) {
        if (idx == bannedId.length) {
            if (bitCount(bannedUsers) == bannedId.length) {
                answer++;
//                bannedUser.add(bannedUsers);
            }
            return;
        }
        int tmp = bannedUsers;
        for (int i = 0; i < userId.length; i++) {
            if (visited[i]) continue;
            char[] banChars = bannedId[idx].toCharArray();
            char[] userChars = userId[i].toCharArray();
            boolean match = true;
            if (banChars.length != userChars.length) continue;
            for (int j = 0; j < banChars.length; j++) {
                if (banChars[j] == '*') continue;
                if (banChars[j] != userChars[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                if ((tmp & (1 << (i))) == 1) {
                    continue;
                }
                tmp = tmp | (1 << (i));
            }
            visited[i] = true;
            dfs(idx + 1, tmp);
            tmp = bannedUsers;
            visited[i] = false;
        }
    }

    int bitCount(int bit) {
        if (bit == 0) return 0;
        return bit % 2 + bitCount(bit / 2);
    }
}
