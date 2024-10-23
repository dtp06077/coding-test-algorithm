package dp;

import java.io.*;

public class Baekjoon_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][10];

        for (int i = 1; i < 10; i++) {
            dp[1][i]=1;
        }

        long answer = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if(j==0) {
                    dp[i][j]=dp[i-1][j+1]%1000000000;
                }
                else if(j==9) {
                    dp[i][j]=dp[i-1][j-1]%1000000000;
                }
                else dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%1000000000;

                if(i==N) answer+=dp[i][j];
            }
        }

        System.out.println((N!=1)?answer%1000000000:9);
    }
}
