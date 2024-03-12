package bruteforce;

import java.util.*;
import java.io.*;

public class Baekjoon_14501{
    static int N;
    static int[][] table;
    static int max=0;

    static void dfs(int index, int profit){
        max=(max>profit)?max:profit;

        if(index>N){
            return;
        }
        if(index+table[index][0]<=N+1){
            dfs(index+table[index][0],profit+table[index][1]);
        }
        if(index+1<=N){
            dfs(index+1,profit);
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        table=new int[N+1][2];

        for(int i=1;i<=N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            table[i][0]=Integer.parseInt(st.nextToken());
            table[i][1]=Integer.parseInt(st.nextToken());
        }
        dfs(1,0);
        System.out.println(max);
    }
}
