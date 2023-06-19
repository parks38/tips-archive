import java.util.*; //Stack 

public class 올바른_괄호 {
    public static void main(String[] args) {
        System.out.println(solution("(())()"));
        System.out.println(solution("(()("));
        System.out.println(solution(")()("));
    }

    public static boolean solution (String brackets) {
        // stack 에서 type 유추하게 하면 효율성 test fail
        Stack<Character> open = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') open.push('(');
            else {
                // 맨 앞의 자리만 확인하면 맨 마지막에서 fail 
                if (open.isEmpty()) return false;
                open.pop();
            }
        }

        return open.isEmpty();
    }
}

// first try
//    public static boolean solution (String brackets) {
//        if (brackets.startsWith(")")) return false;
//
//        String[] bracketArray = brackets.split("");
//        Stack open = new Stack<>();
//        for (int i = 0; i < bracketArray.length; i++) {
//            if (bracketArray[i].equals("(")) open.push("(");
//            else open.pop();
//        }
//
//        return open.isEmpty();
//     }
