import java.util.HashSet;
import java.util.Set;

/**
 * 문제 : 소수찾기 (Level 2) 
 * 유형 : DFS (recursion 재귀), Set
 */
public class 소수_찾기_2 {

    public static Set<Integer> numSet;
    public static boolean[] isVisited;

    public static void main(String[] args) {
        System.out.println(solution("17"));
    }

    public static int solution(String numbers)  {
        numSet = new HashSet<>();
        isVisited = new boolean[numbers.length()];
        recursion("", 0, numbers);
        return numSet.size();
    }

    public static void recursion(String str, int index, String numbers) {
        if (index == numbers.length()) return;
        if (str != "" && isPrime(Integer.parseInt(str))) numSet.add(Integer.parseInt(str));

        for (int i = 0; i < numbers.length(); i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                System.out.println("for문 i:"+i+", str:"+str);
                recursion(str + numbers.charAt(i), index++, numbers);
                System.out.println("for문2 i:"+i+", str:"+str);
                isVisited[i] = false;
            }
        }
    }

    public static boolean isPrime (int num) {
        if (num == 0 || num == 1 ) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false; // 나누어질 경우
        }
        System.out.println("###" + num + " true");
        return true;
    }
}

// 추가 소수 찾는 방법 : https://moneydeveloper.tistory.com/m/53
/**
 * for문 i:0, str:
 * for문 i:1, str:1
 * ###17 true
 * for문2 i:1, str:1
 * for문2 i:0, str:
 * for문 i:1, str:
 * ###7 true
 * for문 i:0, str:7
 * ###71 true
 * for문2 i:0, str:7
 * for문2 i:1, str:
 * 3
 * 
 * 
 * 011
 * for문 i:0, str:
for문 i:1, str:0
for문 i:2, str:01
###11 true
for문2 i:2, str:01
for문2 i:1, str:0
for문 i:2, str:0
for문 i:1, str:01
###11 true
for문2 i:1, str:01
for문2 i:2, str:0
for문2 i:0, str:
for문 i:1, str:
for문 i:0, str:1
for문 i:2, str:10
###101 true
for문2 i:2, str:10
for문2 i:0, str:1
for문 i:2, str:1
###11 true
for문 i:0, str:11
for문2 i:0, str:11
for문2 i:2, str:1
for문2 i:1, str:
for문 i:2, str:
for문 i:0, str:1
for문 i:1, str:10
###101 true
for문2 i:1, str:10
for문2 i:0, str:1
for문 i:1, str:1
for문2 i:1, str:1
for문2 i:2, str:
2
 */