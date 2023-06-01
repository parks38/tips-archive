/**
 * 징검다리
 * 이분탐색 
 */
public class 징검다리 {

    public static void main(String[] args) {
        System.out.println(solution(25, new int[]{2, 14, 11, 21, 17}, 2));
    }

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 0;
        int right = distance;

        // rock 을 가까운 순서로 배열
        Arrays.sort(rocks);
        while(left <= right) {
            int mid = (left + right) / 2;
            int removeRocks = 0;
            int prev = 0;

            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prev < mid) {
                    // 최단 거리 도달하지 않은 경우 돌맹이 제거
                    removeRocks++;
                } else {
                    // 해당 돌맹이에서 앞으로 mid 만큼의 거리 측정
                    prev = rocks[i];
                }
            }

            // 마지막 rock - distance 사이의 간격도 고려하여 추가
           if(distance - rocks[rocks.length-1] < mid) removeRocks++;
           if(removeRocks <= n) {
               answer = mid;
               left = mid + 1;
           } else right = mid - 1;
        }
        return answer;
    }
}
