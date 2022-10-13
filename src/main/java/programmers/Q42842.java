package programmers;

public class Q42842 {
    public int[] solution(int brown, int yellow) {
        int tmp = (brown / 2) - 2;
        int p = (int)Math.sqrt(tmp * tmp - 4 * yellow) + tmp;
        int y = p / 2;
        int x = tmp - y;
        if (x > y) return new int[]{x + 2, y + 2};
        return new int[]{y + 2, x + 2};
    }
}
