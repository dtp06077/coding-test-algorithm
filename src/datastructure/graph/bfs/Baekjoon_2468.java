package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

public class Baekjoon_2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static void flooding(int height){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]<=height){
                    visited[i][j]=true;
                }
            }
        }
    }
    static void bfs(int row,int col){
        queue.add(new Node(col,row));
        visited[row][col]=true;

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;

            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=N||ny>=N) continue;
                if(visited[ny][nx]) continue;
                queue.add(new Node(nx,ny));
                visited[ny][nx]=true;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int maxCount=0;
        int maxHeight=0;
        int cnt;

        map=new int[N][N];
        visited=new boolean[N][N];
        queue=new LinkedList<>();

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(maxHeight<map[i][j]){
                    maxHeight=map[i][j];
                }
            }
        }

        for(int i=1;i<maxHeight;i++){
            cnt=0;
            flooding(i);
            for(int j=0;j<N;j++){
                for(int k=0;k<N;k++){
                    if(!visited[j][k]){
                        cnt++;
                        bfs(j,k);
                    }
                }
            }
            if(cnt>maxCount){
                maxCount=cnt;
            }
            visited=new boolean[N][N];
        }
        System.out.println((maxCount==0)?1:maxCount);
    }
}
