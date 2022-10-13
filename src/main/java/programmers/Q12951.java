package programmers;

public class Q12951 {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                first = true;
                sb.append(s.charAt(i));
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                first = false;
                sb.append(s.charAt(i));
                continue;
            }
            if (first) {
                first = false;
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }

        return sb.toString();
    }
}
