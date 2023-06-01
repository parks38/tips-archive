import java.util.Arrays;

/**
 * 문제유형 : 정렬
 * time complexity : O(N)
 *
 *  논문 n편 중, h번 이상 인용된 논문이 h편 이상이고
 *  나머지 논문이 h번 이하 인용되었다면 h의 최댓값
 *
 * [testcase]
 * {2, 2, 2, 2, 2} => 2
 * [0, 1, 1] => 1
 * [3, 1, 0]	1
 * [3, 1, 1, 1, 4]	2
 * [0, 0, 0, 1]	1
 * [9, 9, 9, 12]	4
 * [1, 1, 5, 7, 6]	3
 * [0, 0, 0] (테스트 16번)	0
 */

public class H_Index {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 0, 6, 1, 5}));
        System.out.println(solution(new int[]{1, 1, 5, 7, 6}));
        System.out.println(solution(new int[]{9, 9, 9, 12}));
    }

    /**
     * (ctn-h)번째 논문의 인용횟수가 h번 이상일 때,
     *
     * (cnt-h)-1번째 논문의 인용횟수가 h번 이하면 h값을 return
     * (cnt-h)-1번째 논문의 인용횟수가 h번 이상이면 인용횟수(h)를 증가
     *
     * @param citation
     * @return
     */
    public static int solution (int[] citation) {
        int answer = 0;

        Arrays.sort(citation);

        for (int i = 0; i < citation.length; i++) {
            if (citation[i] >= citation.length - i) {
                return citation.length - i;
            }
        }

        return answer;
    }
}
