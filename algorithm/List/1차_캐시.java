package algorithm.List;

import java.util.*;

class 1차_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<String>();
        
        if(cacheSize == 0) // 캐시크기가 0
            return cities.length * 5;
        
        for (int i = 0; i < cities.length; i++) {
            String currCityLower = cities[i].toLowerCase();
            // List 또한 .contains() 사용 가능하다. 
            if (cache.contains(currCityLower)) {
                // 캐시 반복시에 뒤로 update (fix)
                cache.remove(currCityLower);
                cache.add(currCityLower);
                answer++;
            } else {
                answer += 5;
                if (cache.size() >= cacheSize) {
                    cache.remove(cache.get(0));
                }
                cache.add(currCityLower);
            }
        }
        return answer;
    }
}

/**
 * 시간 복잡도에서 맞지 않는 느낌. 
 * 
 * class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedHashSet<String> cache = new LinkedHashSet<String>();

        for (int i = 0; i < cities.length; i++) {
            String currCityLower = cities[i].toLowerCase();
            if (cache.contains(currCityLower)) {
                answer++;
            } else {
                answer += 5;
                if (cache.size() >= cacheSize && cacheSize != 0) {
                    cache.remove(cache.stream().findFirst().get());
                
                }
                cache.add(currCityLower);
            }
        }
        return answer;
    }
}
 */