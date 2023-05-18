import java.util.Arrays;

/**
 * 유형: 완전탐색
 * 풀이 방법 :
 *  - 타일의 총 합수
 *  - 가로폭 > 세로폭
 *  - (가로 - 2) * (세로 - 2) = yellow의 개수
 *     - 양네면 4개식 남고 나머지는 yellow 면을 감싸는 모양
 *   [Y, B]
 *   [1, 8] = (3,3)
 *   [2, 10] = (4,3)
 *   [3, 12] = (5,3) -- B +2씩 증가 패턴
 */
public class 카페트 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, 2)));
        System.out.println(Arrays.toString(solution(8, 1)));
        System.out.println(Arrays.toString(solution(24, 24)));
    }

    public static int[] solution (int brown, int yellow) {
        for (int i = 1; i <= yellow+brown; i++) {
            int row = i; // 가로
            int col = (yellow + brown) / i; // 세로
            if (row > col) break;
            if ((row - 2) * (col - 2) == yellow) return new int[]{col, row};
        }
        return new int[]{0, 0};
    }
}
