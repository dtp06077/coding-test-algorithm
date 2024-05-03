package dfs;

import java.io.*;
import java.util.*;

/**
 * 주어진 배점으로 가능한 모든 시험 점수의 경우의 수를 찾는 문제.
 * dfs탐색으로 해결하려 했지만 N이 최대 100이므로 최악의 경우 2^100의 시간복잡도를 가짐.
 * 실패한 코드이다.
 */
public class SWEA_3752 {
    static int N;
    static int[] score;
    static Set<Integer> set;
    static void dfs(int start, int target, int index, int weight) {
        if(start==target) {
            set.add(weight);
            return;
        }

        for(int i=index;i<N;i++) {
            dfs(start+1,target,i+1,weight+score[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answer = new int[T];
        int index=0;

        while (index<T) {
            N = Integer.parseInt(br.readLine());
            score = new int[N];
            set = new HashSet<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                score[i] = Integer.parseInt(st.nextToken());
            }

            int depth = 0;
            while(depth<=N) {
                dfs(0,depth,0,0);
                depth++;
            }

            answer[index]=set.size();
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
