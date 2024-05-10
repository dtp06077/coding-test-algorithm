package math;

import java.io.*;
import java.util.*;

public class SWEA_19113 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        ArrayList<int[]> answer = new ArrayList<>();

        while(T>0) {
            int N = Integer.parseInt(br.readLine());
            int[][] price = new int[N*2][2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<2*N;i++) {
                int num = Integer.parseInt(st.nextToken());
                price[i][0]=num;
            }

            int[] discount = new int[N];
            int index=0;
            for(int i=0;i<2*N;i++) {
                if(price[i][1]==0&&price[i][0]%3==0) {
                    int disNum = price[i][0]/3;
                    for(int j=i+1;j<2*N;j++) {
                        if(price[j][1]==0&&price[j][0]==disNum*4) {
                            price[j][1]=1;
                            break;
                        }
                    }
                    discount[index]=price[i][0];
                    index++;
                    if(index==N) break;
                }
            }
            answer.add(discount);
            T--;
        }

        int TCNum = 1;
        for(int[] ans : answer) {
            System.out.print("#"+TCNum+" ");

            for (int i : ans) {
                System.out.print(i+" ");
            }
            TCNum++;

            System.out.println();
        }
    }
}
