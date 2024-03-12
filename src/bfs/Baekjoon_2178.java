package bfs;

import java.util.*;
import java.io.*;

public class Baekjoon_2178 {
    static int N,M;
    static int[][] maze;
    static boolean[][] visited;

    static class Node{
        int x;
        int y;
        int dist;
        Node(int x,int y,int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
    }
    public static int bfs(){
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(0,0,0));
        visited[0][0]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int dist=node.dist;

            for(int i=0;i<4;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];
                if(nx==M-1&&ny==N-1){
                    return dist+1;
                }
                if(nx<0||ny<0||nx>=M||ny>=N) continue;
                if(maze[ny][nx]==0||visited[ny][nx]) continue;
                queue.add(new Node(nx,ny,dist+1));
                visited[ny][nx]=true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        maze=new int[N][M];
        visited=new boolean[N][M];

        for(int i=0;i<N;i++){
            String str=br.readLine();
            for(int j=0;j<M;j++){
                int num=Character.getNumericValue(str.charAt(j));
                maze[i][j]=num;
            }
        }
        int min=bfs()+1;
        System.out.println(min);
    }
}
