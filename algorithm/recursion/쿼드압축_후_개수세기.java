import java.util.Arrays;

/**
 *  문제: 쿼드 압축 후 개수 세기 
 *  유형: recursion(백트래킹)
 *  시간 복잡도 : O(N^4) ??
 *    - 백트래킹 기본 : O(K ^ N), where ‘K’ is the number of times the function calls itself.
 *
 *   참고: https://brorica.tistory.com/entry/프로그래머스-쿼드압축-후-개수-세기 > O(N^2)
 */
public class 쿼드압축_후_개수세기 {

    static int[] answer;
    public static void main(String[] args) {
        int[][] quad = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        System.out.println(Arrays.toString(solution(quad)));
    }

    /**
     * 백 트래킹
     * @param quad
     * @return
     */
    public static int[] solution (int[][] quad) {
        answer = new int[2]; // 0, 1

        recQuadCount(quad, 0, 0, quad.length);

        return answer;
    }

    public static void recQuadCount(int[][] quad, int x, int y, int size) {
        boolean isCheckAll = false;

        outerloop: //label과 break를 조합해서 이 요구사항을 충족
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (quad[i][j] == quad[x][y]) isCheckAll = true;
                else {
                    isCheckAll = false;
                    break outerloop;
                }
            }
        }

        if (isCheckAll) {
            if (quad[x][y] == 0) answer[0]++;
            else if (quad[x][y] == 1) answer[1]++;
            return;
        }

        size = size / 2;

        // 4등분
        recQuadCount(quad, x, y, size); //좌상단
        recQuadCount(quad, x + size, y, size); // 우상단
        recQuadCount(quad, x, y + size, size); // 좌하단
        recQuadCount(quad, x + size, y + size, size);  //우하단
    }
}
