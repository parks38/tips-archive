package dfs;

public class 정수_삼각형 {

    public static void main(String[] args) {
        int[][] triangle = new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };

        System.out.println(solution(triangle));
    }

    /**
     * 해당 방법으로 풀면 돌아는 가지면 효율성 테스트에서 망!!!!!!
     * 
     * @param triangle
     * @return
     */
    public static int solutions(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];
        int answer = 0;

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            int largest = 0;
            for (int j = 0; j < triangle[i].length; j++) {
                if (j - 1 >= 0 && j + 1 < triangle[i].length) {
                    // within 범위
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                } else {
                    if (j == 0)
                        dp[i][j] = dp[i - 1][0] + triangle[i][j];
                    else if (j == triangle[i].length - 1)
                        dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                }
                System.out.print(dp[i][j] + " ");
                largest = Math.max(largest, dp[i][j]);
            }
            System.out.println();

            answer = Math.max(answer, largest);
        }

        return answer;
    }

    /**
     * 하기 방법이 효율성 검사도 통과
     * 
     * @param triangle
     * @return
     */
    public static int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i]; // triangle 에서는 colum row 와 안의 length 가 동일하다.

            for (int j = 1; j < triangle[i].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer;
    }
}
