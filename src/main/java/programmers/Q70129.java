package programmers;

public class Q70129 {
    public int[] solution(String s) {
        int cnt = 0;
        int zero = 0;
        while (!s.equals("1")) {
            cnt++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') sb.append("1");
            }
            zero += s.length() - sb.length();
            int l = sb.length();
            sb = new StringBuilder();
            while (l != 0) {
                sb.append(l % 2);
                l /= 2;
            }
            s = sb.toString();
        }
        return new int[]{cnt, zero};
    }
}
