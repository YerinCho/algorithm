import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Q1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);

        HashSet<String> inputs = new HashSet<>();

        for (int i = 0; i < n; i++) {
            inputs.add(br.readLine());
        }

        List<String> words = new ArrayList<>(inputs);

        words.sort((a, b) -> {
            if (a.length() > b.length()) {
                return 1;
            }
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return -1;
        });

        for (String r : words) {
            bw.write(r + "\n");
        }

        bw.flush();
        bw.close();
    }
}
