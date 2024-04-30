package implementation;

import java.io.*;
import java.util.*;

public class SWEA_20728 {
    /**
     * 처음엔 무작정 dfs 경우의 수 탐색 알고리즘을 사용해서 풀었지만 시간초과.
     * 문제가 요구하는 것은 모든 경우의 수를 탐색하는 게 아니라 선택한 K개의 수의
     * 최댓값과 최솟값의 차이의 최솟값을 구하는 것.
     * 결국 입력받은 배열을 오름차순 정렬 후 k개씩, i+k-1번째값과 i번째 값의 차이를 구하면 됨.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        int[] answer=new int[T];

        for(int i=0;i<T;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int N=Integer.parseInt(st.nextToken());
            int K=Integer.parseInt(st.nextToken());
            int[] pocket=new int[N];
            int minVal=Integer.MAX_VALUE;

            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                pocket[j]=Integer.parseInt(st.nextToken());
            }
            Arrays.sort(pocket);
            for(int j=0;j<=N-K;j++) {
                int min=pocket[j+K-1]-pocket[j];
                if(min<minVal) minVal=min;
            }
            answer[i]=minVal;
        }
        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
