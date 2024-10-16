package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1309 {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int div = 9901;
        long[][] dp = new long[N+1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % div;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % div;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % div;
        }

        System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%div);
    }
}
