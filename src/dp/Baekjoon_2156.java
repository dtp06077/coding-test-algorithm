package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2156 {

    static Integer[] dp;
    static int[] wines;
    static int dp(int n) {
        if(n<=0) return 0;
        if(dp[n]==null) {
            dp[n]=Math.max(Math.max(dp(n-2),dp(n-3)+wines[n-1])+wines[n],dp(n-1));
        }
        return dp[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        wines = new int[n+1];
        dp = new Integer[n+1];

        for (int i = 1; i <= n; i++) {
            wines[i]=Integer.parseInt(br.readLine());
        }

        dp[1]=wines[1];
        System.out.println(dp(n));
    }
}