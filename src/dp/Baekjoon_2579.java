package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon_2579 {
    static int[] numList;
    static Integer[] dpList;

    static int dp(int N) {
        if(dpList[N]==null) {
            dpList[N]=numList[N]+Math.max(dp(N-2), dp(N-3)+numList[N-1]);
        }
        return dpList[N];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numList = new int[N+1];
        dpList = new Integer[N+1];

        for(int i = 1 ; i <= N; i++) {
            numList[i]=Integer.parseInt(br.readLine());
        }
        dpList[0]=0;
        dpList[1]=numList[1];
        if(N>=2) {
            dpList[2]=numList[1]+numList[2];
        }
        System.out.println(dp(N));
    }
}