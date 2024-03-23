package dfs;

import java.util.*;
import java.io.*;

public class Baekjoon_14889 {
    static int N;
    static int[][] team;
    static boolean[] visited;
    static int MIN=Integer.MAX_VALUE;

    public static void dfs(int index, int depth){
        if(depth==N/2){
            output();
            return;
        }
        for(int i=index;i<N;i++){
            visited[i]=true;
            dfs(i+1, depth+1);
            visited[i]=false;
        }
    }

    public static void output(){
        int start=0;
        int link=0;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(visited[i]==true&&visited[j]==true){
                    start+=team[i][j];
                    start+=team[j][i];
                }
                else if(visited[i]==false&&visited[j]==false){
                    link+=team[i][j];
                    link+=team[j][i];
                }
            }
        }
        int num=Math.abs(start-link);

        if(num==0){
            System.out.print(num);
            System.exit(0);
        }

        MIN=Math.min(MIN,num);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        team=new int[N][N];
        visited=new boolean[N];

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                team[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        System.out.print(MIN);
    }
}