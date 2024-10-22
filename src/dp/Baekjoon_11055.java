package dp;

import java.io.*;
import java.util.*;

public class Baekjoon_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] numList = new int[N+1];
        int[] dpList = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i <= N; i++) {
            numList[i]=Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for(int i = 1 ; i <= N; i++) {
            dpList[i]=numList[i];
            for(int j = 1 ; j < i; j++) {
                if(numList[j]<numList[i]) {
                    dpList[i]=Math.max(dpList[j]+numList[i],dpList[i]);
                }
            }
            max = Math.max(dpList[i], max);
        }
        System.out.println(max);
    }
}