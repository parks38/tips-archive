package algorithm.binary_search;

import java.util.Arrays;

/**
 *  입국 심사
 *  유형 : Binary Search
 *  시간복잡도 : O(logN)
 */
public class 입국심사 {

    public static void main(String[] args) {
        solution(6, new int[]{7, 10});
        // 7 10 14 20 21 28 30
    }

    public static long solution (int pple, int[] time) {
        long answer = 0;
        Arrays.sort(time);
        long start = 0;
        long end = (long) time[time.length - 1] * pple;

        while (start <= end) {
            long mid = (start + end) / 2;
            // 각 심사대 별로 주어진 시간 mid동안 몇명의 사람을 심사할 수 있는지 합산
            long estmiateCompleteCount = 0;
            for (int i = 0; i < time.length; i++) {
                estmiateCompleteCount += mid / time[i];
            }
            if (estmiateCompleteCount < pple) {
                // 해당 시간 안에 모두 심사 불가능
                start = mid + 1;
            } else { // (pple <= estimateCompleteCount)
                // 최솟값이 더 나올수 있어 설정 범위 변경
                answer = Math.min(mid, answer);
                end = mid - 1;
            }
        }
        return answer;
    }
}

// https://born2bedeveloper.tistory.com/40