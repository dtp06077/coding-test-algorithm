package dfs;

import java.io.*;
import java.util.*;

public class Baekjoon_14889 {
    static int N,M;
    static int[][] map;

    static int maxArea=0;
    static Queue<Node> queue=new LinkedList<>();
    static boolean[][] visited;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static void dfs(int wallCount){
        if(wallCount==3){
            int cnt=infection();
            maxArea=(maxArea>cnt)?maxArea:cnt;
            return;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++) {
                if(map[i][j]==0){
                    map[i][j]=1;
                    dfs(wallCount+1);
                    map[i][j]=0;
                }
            }
        }
    }
    static int infection(){
        int count=0;
        int[][] copyMap=new int[N][M];
        visited=new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copyMap[i][j]=map[i][j];
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visited[i][j]&&copyMap[i][j]==2){
                    visited[i][j]=true;
                    queue.add(new Node(j,i));
                    bfs(copyMap);
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copyMap[i][j]==0){
                    count++;
                }
            }
        }
        return count;
    }
    static void bfs(int[][] copyMap){
        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=M||ny>=N) continue;
                if(visited[ny][nx]||copyMap[ny][nx]!=0) continue;
                copyMap[ny][nx]=2;
                visited[ny][nx]=true;
                queue.add(new Node(nx,ny));
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new int[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(maxArea);
    }
}