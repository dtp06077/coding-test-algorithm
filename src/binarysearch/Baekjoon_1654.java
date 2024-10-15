package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1654 {
    static int[] lanList;
    static int K;

    static long count(long len) {
        long totCnt = 0;

        for (int i = 0; i < K; i++) {
            totCnt+=(lanList[i]/len);
        }
        return totCnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        lanList = new int[K];
        long maxLen = 0;

        for (int i = 0; i < K; i++) {
            lanList[i]=Integer.parseInt(br.readLine());
            if(maxLen<lanList[i]) maxLen=lanList[i];
        }
        long row = 0;
        long high = maxLen+1;
        long mid;

        while(row<high) {
            mid=(row+high)/2;
            long cnt = count(mid);
            if(cnt>=N) row=mid+1;
            else high=mid;
        }

        System.out.println(row-1);
    }
}
