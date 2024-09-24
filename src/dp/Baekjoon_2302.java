package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2302 {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0]=dp[1]=1;

        for (int i = 2; i <= 40; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int cnt = 1;
        int start = 0;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            cnt*=dp[vip-start-1];
            start=vip;
        }

        cnt*=dp[N-start];
        System.out.println(cnt);
    }
}
