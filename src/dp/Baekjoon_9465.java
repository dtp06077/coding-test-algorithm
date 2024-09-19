package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        int[][] dp;

        while(T>0) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[2][n+1];

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            dp[0][1]=Integer.parseInt(st1.nextToken());
            dp[1][1]=Integer.parseInt(st2.nextToken());

            for(int i=2; i<=n; i++) {
                dp[0][i]=Math.max(dp[1][i-1],dp[1][i-2])+Integer.parseInt(st1.nextToken());
                dp[1][i]=Math.max(dp[0][i-1],dp[0][i-2])+Integer.parseInt(st2.nextToken());
            }
            sb.append(Math.max(dp[0][n],dp[1][n])+"\n");
            T--;
        }

        System.out.println(sb);
    }
}
