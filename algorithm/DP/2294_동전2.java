import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 3 15
 * 1   // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
 * 5   // [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 3]
 * 12  // [0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 2, 3, 1, 2, 3, 3]
 */
public class 2294_동전2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] moneyType = new int[n];
        for (int i = 0; i < n; i++) {
            moneyType[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1]; // 15 (총 합)
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 동전 0 개로 만들 수 있는 금액은 없음.

        for (int i = 0; i < n; i++) {
            for (int j = moneyType[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - moneyType[i]] + 1); // 0 부터 시작이니 +1
            }
            System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
    }
}

// [참고] https://bangsj1224.tistory.com/132
