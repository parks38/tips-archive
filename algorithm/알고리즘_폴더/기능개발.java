public class 기능개발 {
    /**
        Queue 이용한 알고리즘 
     */
     public static int[] solution (int[] processes, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < processes.length; i++) {
            int during = ((100 - processes[i]) % speeds[i] > 0 ? ((100 - processes[i]) / speeds[i]) + 1 : (100 - processes[i]) / speeds[i]);
            System.out.println(during);
            que.add(during);
        }

        int count = 1;
        int currDate = que.poll();
        while(!que.isEmpty()) {
            if (currDate >= que.peek()) {
                count++;
                que.poll();
            } else {
                answer.add(count);
                count = 1;
                currDate = que.poll();
            }
        }
        answer.add(count);
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
        for loop 으로 자료구조 없이 탐색 경우 
     */
    public static int[] solution2 (int[] workload, int[] speed) {
        List<Integer> answer = new ArrayList<>();

        int currDays = ((100 - workload[0]) % speed[0] > 0 ? ((100 - workload[0]) / speed[0]) + 1 : (100 - workload[0]) / speed[0]);
        int count = 0;

        for (int i = 0; i < workload.length; i++) {
            int left = 100 - workload[i];
            int workDays = (left % speed[i] > 0 ? (left / speed[i]) + 1 : left / speed[i]);
            if (currDays < workDays) {
                answer.add(count);
                currDays = workDays;
                count = 0;
            }
            count++;
        }

        if (count != 0 ) {
            answer.add(count);
        }
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}


