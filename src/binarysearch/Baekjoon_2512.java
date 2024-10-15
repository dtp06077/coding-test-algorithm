package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2512 {
    static int N;
    static int[] numList;

    static int binary(int mid) {
        int totNum = 0;
        for (int i = 0; i < N; i++) {
            totNum+=Math.min(numList[i],mid);
        }
        return totNum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numList = new int[N];

        int maxNum = 0;
        int minNum = 100001;
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
            if (numList[i]>maxNum) maxNum=numList[i];
            if(numList[i]<minNum) minNum=numList[i];
            sum+=numList[i];
        }

        int totalNum = Integer.parseInt(br.readLine());

        if(totalNum<sum) {
            if(totalNum/N<=minNum) maxNum=totalNum/N;

            else {
                int min = minNum;
                int max = maxNum;
                int mid;

                while(min<max-1) {
                    mid = (min+max)/2;
                    int result = binary(mid);
                    if (result>totalNum) {
                        max=mid;
                    }
                    else {
                        min=mid;
                    }
                }
                maxNum=min;
            }
        }

        System.out.println(maxNum);
    }
}