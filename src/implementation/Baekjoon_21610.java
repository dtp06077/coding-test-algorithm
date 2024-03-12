package implementation;

import java.io.*;
import java.util.*;

public class Baekjoon_21610 {
    static int N,M;
    static int[][] buckets;
    static boolean[][] visited;
    static LinkedList<Cloud> clouds;
    static int[] dx={-1,1,1,-1};
    static int[] dy={-1,-1,1,1};
    static class Cloud{
        int x, y;
        Cloud(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static void rainWishful(int direction, int distance){
        int size=clouds.size();
        //step 1
        for(int i=0;i<size;i++){
            Cloud cloud=clouds.poll();
            int x=cloud.x;
            int y=cloud.y;
            clouds.add(move(direction,distance,x,y));
        }
        //step 2
        for(int i=0;i<size;i++){
            Cloud cloud=clouds.get(i);
            int x=cloud.x;
            int y=cloud.y;
            buckets[y][x]++;
            visited[y][x]=true;
        }
        //step 3, step 4
        while(!clouds.isEmpty()){
            Cloud cloud=clouds.poll();
            int x=cloud.x;
            int y=cloud.y;
            buckets[y][x]+=waterBug(x,y);
        }
        //step 5
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(buckets[i][j]>=2&&!visited[i][j]){
                    clouds.add(new Cloud(j,i));
                    buckets[i][j]-=2;
                }
            }
        }
        visited=new boolean[N][N];
    }
    static int waterBug(int x, int y){
        int cnt=0;
        for(int i=0;i<4;i++){
            int nx=dx[i]+x;
            int ny=dy[i]+y;
            if(nx<0||ny<0||nx>=N||ny>=N) continue;
            if(buckets[ny][nx]==0) continue;
            cnt++;
        }
        return cnt;
    }
    static Cloud move(int direction, int distance, int x, int y){
        switch (direction){
            case 1:
                x=(N+x-distance%N)%N;
                break;
            case 2:
                x=(N+x-distance%N)%N;
                y=(N+y-distance%N)%N;
                break;
            case 3:
                y=(N+y-distance%N)%N;
                break;
            case 4:
                x=(x+distance)%N;
                y=(N+y-distance%N)%N;
                break;
            case 5:
                x=(x+distance)%N;
                break;
            case 6:
                x=(x+distance)%N;
                y=(y+distance)%N;
                break;
            case 7:
                y=(y+distance)%N;
                break;
            case 8:
                x=(N+x-distance%N)%N;
                y=(y+distance)%N;
                break;
        }
        return new Cloud(x,y);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        buckets=new int[N][N];
        visited=new boolean[N][N];
        clouds=new LinkedList<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num=Integer.parseInt(st.nextToken());
                if((i==N-1&&j==0)||(i==N-1&&j==1)||(i==N-2&&j==0)||(i==N-2&&j==1)){
                    clouds.add(new Cloud(j,i));
                }
                buckets[i][j]=num;
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int direction=Integer.parseInt(st.nextToken());
            int distance=Integer.parseInt(st.nextToken());
            rainWishful(direction,distance);
        }

        int totalCount=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                totalCount+=buckets[i][j];
            }
        }

        System.out.println(totalCount);
    }
}
