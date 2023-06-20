import java.util.Stack;

/**
 * Good To Go! 
 *
 * @author suna.park
 * @createdDate 2023-06-20.
 */
public class 괄호_회전하기 {
    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("{)(}"));
    }

    public static int solution (String brackets) {
        int answer = 0;
        int size = brackets.length();
        for (int i = 0; i < size; i++) {

            brackets = brackets.substring(1) + brackets.charAt(0);
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < size; j++) {
               if (brackets.charAt(j) == '(' || brackets.charAt(j) == '{'
                       || brackets.charAt(j) == '[') {
                   stack.push(brackets.charAt(j));
               } else {
                   if (stack.isEmpty()) break;
                   if (stack.peek() == '(' && brackets.charAt(j) == ')') stack.pop();
                   else if (stack.peek() == '{' && brackets.charAt(j) == '}') stack.pop();
                   else if (stack.peek() == '[' && brackets.charAt(j) == ']') stack.pop();
               }

               if (j == brackets.length()-1 && stack.isEmpty()) answer++;
            }
        }
        return answer;
    }
}
