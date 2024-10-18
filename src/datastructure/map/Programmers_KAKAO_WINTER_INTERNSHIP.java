package datastructure.map;

import java.util.*;

/**
 * 도넛과 막대 그래프
 */
public class Programmers_KAKAO_WINTER_INTERNSHIP {

    static public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, Integer> input = new HashMap<>();
        Map<Integer, Integer> output = new HashMap<>();

        for(int i = 0; i < edges.length;i++) {
            input.put(edges[i][1], input.getOrDefault(edges[i][1], 0) +1 );
            input.put(edges[i][0], input.getOrDefault(edges[i][1], 0));
            output.put(edges[i][1], output.getOrDefault(edges[i][0], 0));
            output.put(edges[i][0], output.getOrDefault(edges[i][0], 0) +1 );
        }

        for(int i : output.keySet()) {
            if(output.get(i)>=2&&input.get(i)==0) answer[0]=i;
            else if(output.get(i)==2&&input.get(i)>=2) answer[3]+=1;
            else if(output.get(i)==0&&input.get(i)>=1) answer[2]+=1;
        }
        answer[1]=output.get(answer[0])-answer[3]-answer[2];
        return answer;
    }

    static public void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        System.out.println(solution(edges));
    }
}

