package datastructure.graph.dfs;

import java.io.*;

public class Baekjoon_9663 {
    static int N;
    static int[] visited;
    static int cnt=0;

    public static void dfs(int K){
        if(K==N){
            cnt++;
            return;
        }
        for(int i=0;i<N;i++){
            visited[K]=i;
            if(check(K)){
                dfs(K+1);
            }
        }
    }
    public static boolean check(int K){
        for(int i=0;i<K;i++){
            if(visited[i]==visited[K]){
                return false;
            }
            else if(Math.abs(K-i)==Math.abs(visited[K]-visited[i])){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        visited=new int[N];

        dfs(0);

        System.out.print(cnt);
    }
}
