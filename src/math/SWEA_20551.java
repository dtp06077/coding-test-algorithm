package math;

import java.io.*;
import java.util.*;

public class SWEA_20551 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[] answer = new int[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(C<3||B<2) {
                answer[index]=-1;
            }

            else {
                int count = 0;
                if(B>=C) {
                    count+=B-(C-1);
                    B=C-1;
                }
                if(A>=B) {
                    count+=A-(B-1);
                }
                answer[index]=count;
            }
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}