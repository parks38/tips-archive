import java.util.Arrays;

/**
 * 순위.java
 *
 * @author suna.park
 * @createdDate 2023-06-28.
 */
public class 순위 {
    public static void main(String[] args) {
        int[][] results = {	{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        /**
         *     1  2  3  4  5
         *  1  0  1
         *  2  -1 0  -1 -1  1
         *  3     1  -  -1
         *  4     1  1
         *  5    -1
         *
         *  Floyd-Warshall(플로이드-워샬 알고리즘)
         *  dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j])
         *  본 컨셉은 정점 i, j사이 모든 경유지를 탐색해서 그 중 최단 경로를 찾아내는 것입니다.
         *  그래프의 정점이 1~V까지이면, 경유지 K는 이 모든 정점들을 경유지로 하는 경로를 탐색
         *
         *     1  2   3  4  5
         *  1  0  1         1
         *  2  -1 0  -1 -1  1
         *  3     1  -  -1  1
         *  4     1  1      1
         *  5 -1 -1 -1  -1  0
         *
         */
        System.out.println(solution(5, results));
        // solution(5, results);
    }

    public static int solution (int n, int[][] results) {
        int answer = 0;
        int maxValue = 987654321;

        // 순위 확정 조건
        // n명의 선수가 있을 때, 각 선수는 n-1번의 승패를 알 수 있어야 순위를 확정 지을 수 있다.
        // [1,3,4] > [2] > [5]

        int[][] floyd = new int[n+1][n+1];
        for(int i = 0; i <= n ; i++) {
            Arrays.fill(floyd[i], maxValue);
            floyd[i][i] = 0; // 본인!!!
        }

        for (int i = 0; i < results.length; i++) {
            int winner = results[i][0];
            int loser = results[i][1];
            floyd[winner][loser] = 1;
            floyd[loser][winner] = -1;

            System.out.println(Arrays.toString(floyd[i]));
        }

        // 중간 지점 확인하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    // i > k > j
                    if (floyd[i][k] ==1 && floyd[k][j] == 1) {
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    // j > k > i
                    if (floyd[i][k] == -1 && floyd[k][j] == -1) {
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }

        // 결과를 알 수 있는 선수에 대한 탐색
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (floyd[i][j] != 0) cnt ++;
            }
            //순회 하면서 각 행에서 0이 아닌 값이 n-1개일 때 answer를 증가
            // n명의 선수가 있을 때, 각 선수는 n-1번의 승패를 알 수 있어야 순위를 확정 지을 수 있다
            if (cnt == n-1) answer++;
        }

        return answer;
    }
}


/**
 * graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
 */