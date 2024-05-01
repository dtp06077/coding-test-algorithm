package implementation;

import java.io.*;
import java.util.*;

public class SWEA_1206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answer = new int[10];

        for(int i=0;i<10;i++) {
            int N = Integer.parseInt(br.readLine());
            int[] buildings = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {
                buildings[j] = Integer.parseInt(st.nextToken());
            }
            if(N==4) answer[i]=0;
            else {
                for(int j=2;j<N-2;j++) {
                    int max=buildings[j-2];
                    //왼쪽과 오른쪽 각각 2개의 빌딩의 높이를 확인
                    //최댓값을 구하여 j번째 빌딩과 높이 비교 후 조망권이 확보되는지 확인
                    if(buildings[j-1]>max) max=buildings[j-1];
                    if(buildings[j+1]>max) max=buildings[j+1];
                    if(buildings[j+2]>max) max=buildings[j+2];
                    if(buildings[j]>max) answer[i]+=buildings[j]-max;
                }
            }
        }
        for(int i=1;i<=10;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
