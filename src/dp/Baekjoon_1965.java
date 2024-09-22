package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int [n+1];
        int[] box = new int [n+1];
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            box[i]=Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if(box[j]<box[i]) {
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
