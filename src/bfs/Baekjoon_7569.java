package bfs;

import java.io.*;
import java.util.*;
import static java.lang.System.exit;

public class Baekjoon_7569 {
    static int M,N,H;
    static int[][][] boxs;
    static boolean[][][] visited;
    static Queue<Point> queue;
    static class Point{
        int x;
        int y;
        int z;
        int weight;
        Point(int x,int y,int z, int weight){
            this.x=x;
            this.y=y;
            this.z=z;
            this.weight=weight;
        }
    }
    static void bfs(){
        int[] dx={1,0,-1,0,0,0};
        int[] dy={0,-1,0,1,0,0};
        int[] dz={0,0,0,0,1,-1};

        while(!queue.isEmpty()){
            Point point=queue.poll();
            int x=point.x;
            int y=point.y;
            int z=point.z;
            int weight= point.weight;

            for(int i=0;i<6;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                int nz=dz[i]+z;

                if(nx<0||ny<0||nz<0||nx>=M||ny>=N||nz>=H) continue;
                if(boxs[nz][ny][nx]==-1||visited[nz][ny][nx]) continue;
                boxs[nz][ny][nx]=weight;
                queue.add(new Point(nx,ny,nz,weight+1));
                visited[nz][ny][nx]=true;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        boxs=new int[H][N][M];
        visited=new boolean[H][N][M];
        queue=new LinkedList<>();

        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                st=new StringTokenizer(br.readLine());
                for(int k=0;k<M;k++){
                    int num=Integer.parseInt(st.nextToken());
                    if(num==1){
                        queue.add(new Point(k,j,i,1));
                        visited[i][j][k]=true;
                    }
                    boxs[i][j][k]=num;
                }
            }
        }

        bfs();
        int max=Integer.MIN_VALUE;

        for(int i=0;i<H;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<M;k++){
                    int num=boxs[i][j][k];
                    if(num==0){
                        System.out.println(-1);
                        exit(0);
                    }
                    else if(boxs[i][j][k]>=max){
                        max=num;
                    }
                }
            }
        }
        if(max==1){
            max-=1;
        }
        System.out.println(max);
    }
}
