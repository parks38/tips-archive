import java.util.*;

/**
 * 유형 : DFS, Sort, Binary Search
 * 풀이 방법 : 
 * - info가 포함될 수 있는 모든 경우의 수를 map에 key로 넣고 점수를 value로 넣어준다.
 * - query를 key로하는 value들을 가져와서 이분탐색
 */
public class 순위_검색 {
    public static Map<String, List<Integer>> situationMap;
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210",
                "python frontend senior chicken 150","cpp backend senior pizza 260",
                "java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"};

        // answer : [1,1,1,1,2,4]

        System.out.println(Arrays.toString(solutions(info, query)));
    }

    public static int[] solutions (String[] info, String[] query) {

        int[] answer = new int[query.length];

        situationMap = new HashMap<>();

        // dfs 이용 info의 combination 경우의 수 탐색
        for (int i = 0; i < info.length; i++) {
            String[] infoSplit = info[i].split(" ");
            createAllSituations(infoSplit, "", 0);
        }

        // key 안의 value 값을 정렬
        for (String key : situationMap.keySet()) Collections.sort(situationMap.get(key));

        // binarySearch 이용해서 score 찾기
        for (int i = 0; i < query.length; i++) {
            String queryReplace = query[i].replaceAll(" and ", " ");
            String[] querySplit = queryReplace.split(" "); // 마지막 score 전에는 and 가 없기 떄문에 적용
            String combinationAll = querySplit[0]+querySplit[1]+querySplit[2]+querySplit[3];
            answer[i] = situationMap.containsKey(combinationAll) ?
                    binarySearch(combinationAll, Integer.parseInt(querySplit[4])) : 0;
        }

        return answer;
    }

    public static void createAllSituations(String[] info, String combination, int level) {
        if (level == 4) {
            situationMap.computeIfAbsent(combination, x -> new ArrayList<>()).add(Integer.parseInt(info[4]));
            return;
        }

        createAllSituations(info, combination + "-", level+1);
        createAllSituations(info, combination + info[level], level+1);
    }

    public static int binarySearch (String query, int score) {
        List<Integer> keyList = situationMap.get(query);
        int start = 0;
        int end = keyList.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            // 값이 더 크면 뒷 부분 탐색
            if (keyList.get(mid) < score) {
                start = mid + 1;
            } else {
                // 값이 적다면 앞 부분 탐색 필요
                end = mid -1;
            }
        }

        // score점 이상 받은 지원자는 몇 명인지 return 
        return keyList.size() - start;
    }
}
