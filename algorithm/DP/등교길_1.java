/**
    동적 프로그래밍 (DP) 
    time complexity :  subset sum problem, and there is an obvious O(N*W) 
 */
public class 등교길_1 {

    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    public static int solution (int m, int n, int[][] splash) {
        int[][] route = new int[n][m];

        // splash 기록
        for (int i = 0; i < splash.length; i++) {
            route[splash[i][0]][splash[i][1]] = -1;
        }

        // 위 + 왼쪽 총합 => 가능 route
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (route[i][j] == -1) route[i][j] = 0;
                else if (i - 1 < 0 || j - 1 < 0) route[i][j] = 1;
                else {
                    route[i][j] = route[i - 1][j] + route[i][j - 1];
                }
            }
        }

        return route[n-1][m-1] % 1000000007;
    }
}
