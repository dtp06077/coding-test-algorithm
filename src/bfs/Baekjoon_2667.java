package bfs;

import java.util.*;
import java.io.*;

public class Baekjoon_2667 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static int houseCount=0;

    public static void dfs(int y, int x){

        for(int i=0;i<4;i++){
            int nx=dx[i]+x;
            int ny=dy[i]+y;

            if(nx<0||ny<0||nx>=N||ny>=N) continue;
            if(map[ny][nx]==0||visited[ny][nx]) continue;
            visited[ny][nx]=true;
            houseCount++;
            dfs(ny,nx);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];
        ArrayList<Integer> setCount=new ArrayList<>();

        for(int i=0;i<N;i++){
            String line=br.readLine();
            for(int j=0;j<N;j++){
                map[i][j]=line.charAt(j)-'0';
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1&&!visited[i][j]){
                    visited[i][j]=true;
                    houseCount++;
                    dfs(i,j);
                    setCount.add(houseCount);
                    houseCount=0;
                }
            }
        }
        Collections.sort(setCount);
        System.out.println(setCount.size());

        for(int i : setCount){
            System.out.println(i);
        }
    }
}
