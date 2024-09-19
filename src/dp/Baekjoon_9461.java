package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_9461 {
    static Long[] dp;

    static Long dp(int N) {
        if(dp[N]==null) {
            dp[N]=dp(N-1)+dp(N-5);
        }
        return dp[N];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        dp = new Long[101];
        dp[0]=0L;
        dp[1]=dp[2]=dp[3]=1L;
        dp[4]=dp[5]=2L;

        while(T>0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp(N)+"\n");
            T--;
        }
        System.out.println(sb);
    }
}
