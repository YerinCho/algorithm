import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Q1422 {
    private static int N;
    private static int K;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<String> inputs = new ArrayList<>(N);

        initialize(inputs);

        int max = inputs.stream()
            .mapToInt(Integer::parseInt)
            .max()
            .getAsInt();

        for (int i = 0; i < N - K; i++) {
            inputs.add(String.valueOf(max));
        }

        inputs.sort(Q1422::sort);

        StringBuilder result = new StringBuilder();
        for (String input : inputs) {
            result.append(input);
        }

        bw.write(result.toString());

        bw.flush();
        bw.close();
    }

    private static int sort(String a, String b) {
        StringBuilder sb = new StringBuilder();
        a.chars().forEach(sb::append);
        String p = sb.toString();
        sb = new StringBuilder();
        b.chars().forEach(sb::append);
        String q = sb.toString();

        for (int i = 0; i < Math.min(p.length(), q.length()); i = i + 2) {
            int first = Integer.parseInt(String.valueOf(p.charAt(i)) + p.charAt(i + 1));
            int second = Integer.parseInt(String.valueOf(q.charAt(i)) + q.charAt(i + 1));
            if (first == second)
                continue;
            if (first > second)
                return -1;
            return 1;
        }
        String longer;
        String shorter;
        if (p.length() > q.length()) {
            longer = p;
            shorter = q;
        } else {
            longer = q;
            shorter = p;
        }
        int shorterLength = shorter.length();
        String l = longer.substring(shorterLength);
        if (p.length() > q.length()) {
            return sort(l, shorter);
        }
        if (p.length() < q.length()) {
            return sort(shorter, l);
        }
        return p.compareTo(q);
    }

    private static void initialize(List<String> inputs) throws IOException {
        String[] firstLine = br.readLine().split(" ");
        K = Integer.parseInt(firstLine[0]);
        N = Integer.parseInt(firstLine[1]);
        for (int i = 0; i < K; i++) {
            inputs.add(br.readLine());
        }
    }
}
