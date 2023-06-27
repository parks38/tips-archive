import java.util.*;

/**
 * 2	10	[7,4,5,6]	8
 * 100	100	[10]	101
 * 100	100	[10,10,10,10,10,10,10,10,10,10]	110
 *
 * @author suna.park
 * @createdDate 2023-06-26.
 */
public class 다리_건너는_트럭 {
    public static void main(String[] args) {
        System.out.println(solution2(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(solution2(100, 100, new int[]{10}));
        System.out.println(solution2(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }

    /**
     * my version
     */
    public static int solution2(int length, int weight, int[] cars) {
        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        int mass = 0;
        for (int i = 0; i < cars.length; i++) {
            q.add(cars[i]);
        }

        Queue<Integer> list = new ArrayDeque<>();
        while (!q.isEmpty()) {
            System.out.println(Arrays.toString(list.toArray()));
            if (list.size() >= length) {
                int curr = list.poll();
                mass -= curr;
            }
            if (mass + q.peek() > weight) {
                list.add(0);
            } else {
                int curr = q.poll();
                list.add(curr);
                mass += curr;
            }
            time++;
        }

        return time + length;
    }

    public static int solution(int length, int weight, int[] cars) {
        Queue<Integer> q = new LinkedList<>();
        int sum = 0;
        int time = 0;
        for (int car : cars) {
            while (true) {
                if (q.isEmpty()) {
                    q.add(car);
                    sum += car;
                    time++;
                    break;
                } else if (q.size() == length) {
                    sum -= q.poll();
                } else {
                    if (sum + car <= weight) {
                        q.add(car);
                        sum += car;
                        time++;
                        break;
                    } else {
                        q.add(0);
                        time++;
                    }
                }
            }
        }

        return time + length;
        // 트럭에서 반복문이 끝나는데 마지막 다리 길이만큼 지나가야해서 + 다리길이
    }