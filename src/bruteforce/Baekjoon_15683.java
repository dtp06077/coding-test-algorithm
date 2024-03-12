package bruteforce;

import java.io.*;
import java.util.*;

public class Baekjoon_15683 {
    static int N,M,K;
    static int[][] officeRoom;
    static ArrayList<Cctv> cctvList;
    static int minArea=Integer.MAX_VALUE;

    static class Cctv{
        int num,x,y;
        Cctv(int num, int x, int y){
            this.num=num;
            this.x=x;
            this.y=y;
        }
    }
    static int calArea(int[][] copyRoom){
        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copyRoom[i][j]==0) cnt++;
            }
        }
        return cnt;
    }
    static int[][] copy(int[][] room){
        int[][] copyRoom=new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copyRoom[i][j]=room[i][j];
            }
        }
        return copyRoom;
    }

    static void dfs(int index, int[][] officeRoom){
        if(index==K){
            int area=calArea(officeRoom);
            minArea=(minArea<area)?minArea:area;
            return;
        }
        Cctv cctv=cctvList.get(index);
        int x= cctv.x;
        int y= cctv.y;
        int[][] copyRoom;
        switch(cctv.num){
            case 1:{
                copyRoom=copy(officeRoom);
                upObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                downObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                leftObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                rightObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);
                break;
            }
            case 2:{
                copyRoom=copy(officeRoom);
                upObserve(x,y,copyRoom);
                downObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                leftObserve(x,y,copyRoom);
                rightObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);
                break;
            }
            case 3:{
                copyRoom=copy(officeRoom);
                upObserve(x,y,copyRoom);
                rightObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                rightObserve(x,y,copyRoom);
                downObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                downObserve(x,y,copyRoom);
                leftObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                leftObserve(x,y,copyRoom);
                upObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);
                break;
            }
            case 4:{
                copyRoom=copy(officeRoom);
                leftObserve(x,y,copyRoom);
                upObserve(x,y,copyRoom);
                rightObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                upObserve(x,y,copyRoom);
                rightObserve(x,y,copyRoom);
                downObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                rightObserve(x,y,copyRoom);
                downObserve(x,y,copyRoom);
                leftObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);

                copyRoom=copy(officeRoom);
                downObserve(x,y,copyRoom);
                leftObserve(x,y,copyRoom);
                upObserve(x,y,copyRoom);
                dfs(index+1,copyRoom);
                break;
            }
            case 5: {
                copyRoom=copy(officeRoom);
                upObserve(x, y, copyRoom);
                rightObserve(x, y, copyRoom);
                downObserve(x, y, copyRoom);
                leftObserve(x, y, copyRoom);
                dfs(index + 1, copyRoom);
                break;
            }
        }
    }
    static void upObserve(int x, int y, int[][] copyRoom){
        if(y<0||copyRoom[y][x]==6){
            return;
        }
        if(copyRoom[y][x]==0){
            copyRoom[y][x]=-1;
        }
        upObserve(x,y-1, copyRoom);
    }
    static void downObserve(int x, int y, int[][] copyRoom){
        if(y>=N||copyRoom[y][x]==6){
            return;
        }
        if(copyRoom[y][x]==0){
            copyRoom[y][x]=-1;
        }
        downObserve(x,y+1, copyRoom);
    }

    static void leftObserve(int x, int y, int[][] copyRoom){
        if(x<0||copyRoom[y][x]==6){
            return;
        }
        if(copyRoom[y][x]==0){
            copyRoom[y][x]=-1;
        }
        leftObserve(x-1,y, copyRoom);
    }
    static void rightObserve(int x, int y, int[][] copyRoom){
        if(x>=M||copyRoom[y][x]==6){
            return;
        }
        if(copyRoom[y][x]==0){
            copyRoom[y][x]=-1;
        }
        rightObserve(x+1,y, copyRoom);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        officeRoom=new int[N][M];
        cctvList=new ArrayList<>();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int num=Integer.parseInt(st.nextToken());
                if(num==1||num==2||num==3||num==4||num==5){
                    cctvList.add(new Cctv(num,j,i));
                }
                officeRoom[i][j]=num;
            }
        }
        K=cctvList.size();
        dfs(0,officeRoom);
        System.out.println(minArea);
    }
}
