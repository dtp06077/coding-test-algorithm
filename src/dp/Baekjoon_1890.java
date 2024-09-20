package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];
        long[][] dp = new long[N+1][N+1];

        dp[1][1]=1;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(dp[i][j]==0) continue;
                if(board[i][j]==0) continue;
                if(i+board[i][j]<=N) dp[i+board[i][j]][j]+=dp[i][j];
                if(j+board[i][j]<=N) dp[i][j+board[i][j]]+=dp[i][j];
            }
        }

        System.out.println(dp[N][N]);
    }
}
