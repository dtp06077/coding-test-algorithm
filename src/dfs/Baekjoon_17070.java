package dfs;

import java.io.*;
import java.util.*;

public class Baekjoon_17070 {
    static int N;
    static int[][] house;
    static int totCount=0;

    //백트래킹을 활용하여 방향(오른쪽, 아랫쪽, 대각선)별로 경우의 수 탐색
    static void dfs(int headX, int headY, char direction) {
        //목적지에 도착했을 경우 방법의 개수 ++:
        if(headX==N-1&&headY==N-1) {
            totCount++;
            return;
        }
        switch (direction) {
            case 'r' : {
                if(check(headX,headY,'r')) dfs(headX,headY+1,'r');
                if(check(headX,headY,'c')) dfs(headX+1,headY+1,'c');
                break;
            }
            case 'd' : {
                if(check(headX,headY,'d')) dfs(headX+1,headY,'d');
                if(check(headX,headY,'c')) dfs(headX+1,headY+1,'c');
                break;
            }
            case 'c' : {
                if(check(headX,headY,'c')) dfs(headX+1,headY+1,'c');
                if(check(headX,headY,'d')) dfs(headX+1,headY,'d');
                if(check(headX,headY,'r')) dfs(headX,headY+1,'r');
            }
        }
    }
    static boolean check(int headX, int headY, char direction) {
        switch (direction) {
            case 'r' : {
                if(headY>=N-1||house[headX][headY+1]==1) return false;
                break;
            }
            case 'c' : {
                if(headX>=N-1||headY>=N-1) return false;
                if(house[headX+1][headY]==1||
                        house[headX][headY+1]==1||house[headX+1][headY+1]==1) return false;
                break;
            }
            case 'd' : {
                if(headX>=N-1||house[headX+1][headY]==1) return false;
                break;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        house=new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                house[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,1,'r');
        System.out.println(totCount);
    }
}
