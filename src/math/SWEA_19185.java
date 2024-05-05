package math;

import java.io.*;
import java.util.*;

public class SWEA_19185 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        String[] answer = new String[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            String[] nList = new String[N];
            String[] mList = new String[M];

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                nList[i]=st.nextToken();
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) {
                mList[i]=st.nextToken();
            }

            int Q = Integer.parseInt(br.readLine());
            String years = " ";
            while(Q>0) {
                long Y = Long.parseLong(br.readLine())-1;
                String nYear = nList[(int)Y%N];
                String mYear = mList[(int)Y%M];
                years+=(nYear+mYear+" ");
                Q--;
            }

            answer[index]=years;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+answer[i-1]);
        }
    }
}
