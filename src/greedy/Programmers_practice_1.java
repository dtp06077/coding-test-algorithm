package greedy;

import java.util.*;

/**
 * 요격 시스템
 */
public class Programmers_practice_1 {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[1]-o2[1]);

        int start = 0;
        for(int i = 0 ; i<targets.length;i++) {
            if(start<=targets[i][0]) {
                start=targets[i][1];
                answer++;
            }
        }
        return answer;
    }
}
