package bfs;

import java.util.*;
import java.io.*;
public class Baekjoon_2573 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int icebergCount(){
        int years=0;

        while(true){
            int lumps=0;
            for(int i=0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if (map[i][j] != 0&&!visited[i][j]) {
                        lumps++;
                        queue.add(new Node(j, i));
                        visited[i][j] = true;
                        bfs();
                    }
                }
            }

            if(lumps==0){
                return 0;
            }
            if(lumps>1){
                return years;
            }
            years++;
            visited=new boolean[N][M];
        }
    }

    static void bfs(){

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;

            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;

                if(nx<0||ny<0||nx>=M||ny>=N) continue;
                if(visited[ny][nx]) continue;
                if(map[y][x]>0&&map[ny][nx]==0){
                    map[y][x]--;
                }
                if(map[ny][nx]!=0){
                    queue.add(new Node(nx,ny));
                    visited[ny][nx]=true;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][M];
        visited=new boolean[N][M];
        queue=new LinkedList<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int num=icebergCount();
        System.out.println(num);
    }
}
