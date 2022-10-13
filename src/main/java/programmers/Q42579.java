package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Q42579 {
    // 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
    // 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
    // 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
    public static void main(String[] args) {
        Q42579 a = new Q42579();
        int[] b = a.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 150, 2500});
    }

    ArrayList<Integer> answer = new ArrayList<>();

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genre = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (!genre.containsKey(genres[i])) genre.put(genres[i], plays[i]);
            else {
                genre.put(genres[i], genre.get(genres[i]) + plays[i]);
            }
        }
        List<Map.Entry<String, Integer>> sorted = genre.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .collect(Collectors.toList());
        for (Map.Entry<String, Integer> key : sorted) {
            search(key.getKey(), plays, genres);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private void search(String g, int[] plays, String[] genres) {
        Music first = null;
        Music second = null;
        for (int i = 0; i < plays.length; i++) {
            if (!genres[i].equals(g)) continue;
            if (first == null) {
                first = new Music(plays[i], i);
                continue;
            }
            if (second == null) {
                if (plays[i] > first.times) {
                    second = new Music(first.times, first.num);
                    first.num = i;
                    first.times = plays[i];
                } else {
                    second = new Music(plays[i], i);
                }
                continue;
            }

            // 첫번째로 가는 경우
            if (plays[i] > first.times) {
                second.num = first.num;
                second.times = first.times;
                first.num = i;
                first.times = plays[i];
                continue;
            }

            // 두번째로 가는 경우
            if (plays[i] > second.times) {
                second.num = i;
                second.times = plays[i];
            }
        }
        if (first != null) {
            answer.add(first.num);
        }
        if (second != null) {
            answer.add(second.num);
        }
    }

    class Music {
        int times;
        int num;

        public Music(int times, int num) {
            this.times = times;
            this.num = num;
        }
    }
}
