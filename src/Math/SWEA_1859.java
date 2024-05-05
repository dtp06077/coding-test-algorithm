package Math;

import java.io.*;
import java.util.*;

public class SWEA_1859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        long[] answer = new long[T];

        while(index<T) {
            int N = Integer.parseInt(br.readLine());
            int[] numList = new int[N];
            int maxNum = 0;
            int maxIndex = 0;
            //최대 1000000000이 넘어가므로 long타입으로 선언
            long profit = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                numList[i] = Integer.parseInt(st.nextToken());
                if(numList[i]>maxNum) {
                    maxNum = numList[i];
                    maxIndex = i;
                }
            }
            int startIndex=0;
            while(true) {
                for(int i=startIndex;i<maxIndex;i++) {
                    profit+=(maxNum-numList[i]);
                }
                if(maxIndex==N-1) break;
                maxNum=0;
                startIndex = maxIndex+1;
                for(int i=startIndex;i<N;i++) {
                    if(numList[i]>maxNum) {
                        maxNum = numList[i];
                        maxIndex = i;
                    }
                }
            }
            answer[index]=profit;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
