package bj;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

// 못풂
public class Q1208 {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int N;
    private static int S;
    private static ArrayList<Integer> nums;
    private static int sum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        initialize();

        int result = search();

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int search() {
        // 완전탐색 말고 되는 경우가 있나??
        // 수열도 증가하는 수열이라는 전제조건이 없고, 그냥 합이 S인 모든 경우를 찾는건데...
        // 그리고 중간중간 불연속된 요소를 뽑아서 부분수열을 만들수도 있다.
        // 40^2 개가 아니라... 2^40... 많네...
        // 근데, 불연속된 요소를 뽑을 수 있다매.. 그럼 결국 수열을 정렬한 후에 거기서 요소들을 뽑아도 최종적으로는 부분수열이 되는 게 아닌가???
        // 그냥 부분집합의 합이 S 인 모든 경우의 수를 찾는 거..

        // 그럼 일단 정렬한 후에, 생각을 해 보자
        // 전체를 합해.. 그 합이 S보다 크면 뒤쪽의 포인터를 옮기고... 작으면 앞쪽의 포인터를 옮기는 투포인터...
        // S와 가장 가까운.. 원소를 찾기.. 중간값이지 거의 그냥..
        // 그담에 S보다 크면...
        // 모르겠는데 다른 방법을 생각하야겟음.. 아무리 생각해도 저 위 방법들은 반례가 많음

        int mid = -1;
        for (int i = 0; i < N - 1; i++) {
            if (nums.get(i) >= 0) {
                mid = i;
                break;
            }
        }

        // 수열이 양수만, 혹은 음수만 이루어진경우 체크
        boolean isNegative = mid == -1;
        boolean isPositive = mid == 0;

        if (isNegative && S >= 0) return 0;
        if (isPositive && S < 0) return 0;

        int count = 0;
        int s = sum;

        return count;
    }

    private static void initialize() throws IOException {
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        S = Integer.parseInt(inputs[1]);
        String[] input = br.readLine().split(" ");
        sum = 0;
        nums = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(input[i]));
            sum += nums.get(i);
        }

        nums.sort(Comparator.naturalOrder());
    }
}
