public class 주식_가격 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 2, 3})));
    }

    public static int[] solution (int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < prices.length; i++){
            answer[i] = answer.length - 1 - i; // 최대기간으로 세팅
            int arr2 = i;
            // 다음 것의 가격을 peek
            while(!stack.empty() && prices[stack.peek()] > prices[i]){ // 가격이 떨어진 경우
                int curr = stack.pop();
                answer[curr] = i - curr;
            }

            stack.push(arr2);
        }

        return answer;
    }
}