package math;

import java.io.*;
import java.util.*;

public  class SWEA_20934 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[] answer = new int[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cups = st.nextToken();
            int K = Integer.parseInt(st.nextToken());
            int bellPos = 0;

            for(int i=0;i<3;i++) {
                if(cups.charAt(i)=='o') bellPos=i;
            }

            if(bellPos==0||bellPos==2) {
                if(K%2!=0) answer[index]=1;
                else {
                    if(K==0) answer[index]=bellPos;
                    else answer[index]=0;
                }
            }
            else {
                if(K%2!=0) answer[index]=0;
                else {
                    answer[index]=1;
                }
            }
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}