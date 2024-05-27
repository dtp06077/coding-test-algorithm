package datastructure.arraylist;

import java.io.*;
import java.util.*;

/**
 * dfs가 아닌 배열을 활용하여 문제 해결.
 * 배점의 최댓값만큼의 불린형 방문배열과 정수형 리스트 선언 후 N개의 배점을 순서대로 탐색.
 * 1. 우선 정수형 리스트를 순차적으로 돌며 현재 배점과 더하여 방문배열의 인덱스(더한값)을 방문체크 후 count++;
 * 2. 방문배열의 현재 배점 인덱스를 방문하지 않았으면(중복 방지) 방문 후 count++.
 * 3. 정수형 리스트에 해당 배점을 추가.
 * 위 순서로 모든 경우의 수를 count할 수 있음.
 */
public class SWEA_3752 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answer = new int[T];
        int index=0;

        while (index<T) {
            int N = Integer.parseInt(br.readLine());
            int[] score = new int[N];
            int size = 0;
            int count = 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                score[i] = Integer.parseInt(st.nextToken());
                size+=score[i];
            }

            boolean[] intArr = new boolean[size+1];
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<N;i++) {
                int num = score[i];
                int listSize = list.size();

                for(int j=0;j<listSize;j++) {
                    int n = list.get(j);
                    if(!intArr[num+n]) {
                        intArr[num+n]=true;
                        list.add(num+n);
                        count++;
                    }
                }
                if(!intArr[num]) {
                    intArr[num]=true;
                    list.add(num);
                    count++;
                }
            }
            answer[index]=count;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
