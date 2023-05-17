import java.util.*;

/**
 * 몇개의 세트가 있는 지 확인 필요
 */
public class 불량_사용자 {
    public static Set<Set<String>> nameList;

    public static void main(String[] args) {
        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = new String[]{"fr*d*", "abc1**"};

        nameList = new HashSet<>();

        System.out.println(solution(user_id, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {

        dfs(new HashSet<>(), 0, user_id, banned_id);

        return nameList.size();
    }

    public static void dfs(Set<String> set, int index, String[] user_id, String[] banned_id) {
        System.out.println("&&&&" + set.toString());
        if (index == banned_id.length) {
            nameList.add(set);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            System.out.println("###" + user_id[i]);
            if (!set.contains(user_id[i]) && checkMatch(user_id[i], banned_id[index])) {
                set.add(user_id[i]);
                System.out.println(i + " add: "+ user_id[i]);
                dfs(new HashSet<>(set), index+1, user_id, banned_id);
                set.remove(user_id[i]);
            }
        }
    }

    public static boolean checkMatch (String userId, String bannedId) {
        if (userId.length() != bannedId.length()) return false;

        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) return false;
        }
        return true;
    }

}

/**
 * &&&&[]
###frodo
0 add: frodo
&&&&[frodo]
###frodo
###fradi
###crodo
###abc123
3 add: abc123
&&&&[abc123, frodo]
###frodoc
###fradi
1 add: fradi
&&&&[fradi]
###frodo
###fradi
###crodo
###abc123
3 add: abc123
&&&&[fradi, abc123]
###frodoc
###crodo
###abc123
###frodoc
2

Process finished with exit code 0

 */
