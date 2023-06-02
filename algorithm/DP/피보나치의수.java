/**
 * 피보나치 수
 * F(2) = F(0) + F(1) = 0 + 1 = 1
 * F(3) = F(1) + F(2) = 1 + 1 = 2
 * F(4) = F(2) + F(3) = 1 + 2 = 3
 * F(5) = F(3) + F(4) = 2 + 3 = 5
 *
 */
public class 피보나치의수 {

    public static void main(String[] args) {
        System.out.println(solution(5));
        System.out.println(solution(3));
    }

    public static int solution (int n) {
        return fibb(n) % 1234567;
    }

    // DP 적용한 풀이 방법 
    public static int fibb(int n) {
        if (n == 1 || n == 0) return n;
        return (fibb(n-1) + fibb(n-2)) % 1234567;
    }
}


//    public static int solution (int n) {
//        int answer = 0;
//
//        if (n == 1 || n == 0) return n;
//        int prev = 0;
//        int curr = 1;
//
//        for (int i = 2; i <= n; i++) {
//            answer = (prev + curr) % 1234567;
//            prev = curr;
//            curr = answer;
//        }
//        return answer;
//    }