import java.util.ArrayList;
import java.util.List;

/**
 * 문제 : 하노이의 탑
 * 유형 : 재귀 (recursion)
 *
 * [참고]
 * https://small-stap.tistory.com/73
 * https://devlimk1.tistory.com/153
 */
public class 하노이의_탑 {
    private static List<int[]> answer;
    public static void main(String[] args) {
        // 세개의 막대
        solution(1, 2, 3, 4);
    }

    public static int[][] solution (int from, int middle, int to, int num) {
        answer = new ArrayList<>();

        hanoi(from,middle,to,num);
        return answer.stream().map(int[]::clone).toArray(int[][]::new);
    }

    //하노이의 탑 원리
    // N개의 원판이 존재한다고가정
    // h(1, 2, 3 ,N) = h(1,3,2,N-1) + 1 (가장큰원판 깔기) + h(2,1,3,N-1)
    public static void hanoi(int from, int middle, int to, int num) {
        // 한개 존재하는 경우 바로 1 로 배치
        if (num == 0) return;
        if (num == 1) {
            answer.add(new int[]{from, to});
            System.out.printf("Move disk %d from %d to %d%n", num, from, to);
            return;
        } else {
            // n-1개의 원판을 목적지가 아닌 곳(other)로 옮겨놓음.
            hanoi(from, to, middle, num-1);  // from(1) -> middle(2)
            answer.add(new int[]{from, to});
            System.out.printf("Move disk %d from %d to %d%n", num, from, to);
            // 목적지가 아닌 곳(other)에 옮겨놓았던 원판들을 목적지로
            hanoi(middle, from, to, num-1);  // middle(2) -> to(3)
        }
    }
}
