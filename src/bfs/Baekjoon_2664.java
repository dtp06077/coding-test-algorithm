package bfs;

import java.util.*;
import java.io.*;

public class Baekjoon_2664{
    static int N;
    static boolean[][] graph;
    static boolean[] visited;

    static int cnt=0;

    public static void bfs(int start, int target, int index){
        if(start==target){
            cnt=index;
            return;
        }
        for(int i=0;i<N;i++){
            if(graph[start-1][i]&&!visited[i]){
                visited[i]=true;
                bfs(i+1,target,index+1);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        graph=new boolean[N][N];
        visited=new boolean[N];

        StringTokenizer st1=new StringTokenizer(br.readLine());
        int startNum=Integer.parseInt(st1.nextToken());
        int targetNum=Integer.parseInt(st1.nextToken());
        int M=Integer.parseInt(br.readLine());

        for(int i=0;i<M;i++){
            StringTokenizer st2=new StringTokenizer(br.readLine());
            int n1=Integer.parseInt(st2.nextToken());
            int n2=Integer.parseInt(st2.nextToken());
            graph[n1-1][n2-1]=true;
            graph[n2-1][n1-1]=true;
        }
        visited[startNum-1]=true;
        bfs(startNum,targetNum,0);

        if(cnt==0) System.out.println(-1);
        else System.out.println(cnt);
    }
}
