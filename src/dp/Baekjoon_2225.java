package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2225 {

    static long[][] dp;
    static long div = 1000000000;

    static long dp (int N, int K) {
        if(dp[N][K]==0) {
            dp[N][K]=dp(N-1,K)+dp(N,K-1);
        }
        return dp[N][K]%div;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new long[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            dp[i][1]=1;
        }
        for (int i = 1; i <= K; i++) {
            dp[1][i]=i;
        }

        System.out.println(dp(N,K));
    }
}


