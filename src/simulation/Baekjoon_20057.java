package simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_20057 {
    static int N;
    static int[][] map;
    //직진해야하는 칸의 수
    static int moveCount=1;
    //격자의 밖으로 나간 모래의 양
    static int outSandAmount=0;
    //모래가 흩어질 네 방향의 좌표를 구성하는 배열
    static int[] node1={1,1,0,0,0,0,-1,-1,-2,-1};
    static int[] node2={-1,-1,0,0,0,0,1,1,2,1};
    static int[] node3={-1,1,-2,2,-1,1,-1,1,0,0};

    static void tornado(int row, int col, int move, int change, String direction){
        //(0,0)에 도착 시 해당 칸의 모래를 격자 밖의 모래와 합친 후 종료
        if(row==0&&col==0) {
            outSandAmount+=map[row][col];
            return;
        }
        //칸 이동
        move++;
        //방향에 따라 모래를 흩날릴 좌표 재구성
        switch (direction){
            case "left" : col-=1;move(row,col,node1,node3); break;
            case "right" : col+=1;move(row, col,node2,node3); break;
            case "up" : row-=1;move(row,col,node3,node1); break;
            case "down" : row+=1;move(row, col, node3,node2); break;
        }
        //직진해야하는 칸을 다 채웠을 경우
        if(move==moveCount){
            //방향 변경
            switch (direction){
                case "left" : direction="down"; break;
                case "right" : direction="up"; break;
                case "up" : direction="left"; break;
                case "down" : direction="right"; break;
            }
            //칸 이동 초기화
            move=0;
            change++;
            //동일한 개수의 직진칸을 두 번 이동했다면
            if(change==2){
                //직진해야하는 칸의 수 증가
                moveCount++;
                change=0;
            }
        }
        //재귀적 호출
        tornado(row,col,move,change,direction);
    }

    //모래 이동 함수
    static void move(int row, int col, int[] nodeX, int[] nodeY){
        int sandAmount=map[row][col];
        map[row][col]=0;
        //모래의 양이 10보다 작으면 흩날릴 모래가 없으므로 방향별 칸 이동
        if(sandAmount<10){
            int nRow=nodeY[9]+row;
            int nCol=nodeX[9]+col;
            if(nRow<0||nCol<0||nRow>=N||nCol>=N) {
                outSandAmount+=sandAmount;
            }
            else{
                map[nRow][nCol]+=sandAmount;
            }
            return;
        }

        int alpha=0, casePer;
        //좌표별 흩날릴 모래 이동
        for(int i=0;i<10;i++){
            int nRow=nodeY[i]+row;
            int nCol=nodeX[i]+col;
            if(i<2) {
                casePer=sandAmount/100;
                alpha+=casePer;
            }
            else if(i<4) {
                casePer=sandAmount*2/100;
                alpha+=casePer;
            }
            else if(i<6) {
                casePer=sandAmount*7/100;
                alpha+=casePer;
            }
            else if(i<8) {
                casePer=sandAmount*10/100;
                alpha+=casePer;
            }
            else if(i==8) {
                casePer=sandAmount*5/100;
                alpha+=casePer;
            }
            else casePer=sandAmount-alpha;

            //격자 밖을 나간다면 격자 밖 모래양 증가
            if(nRow<0||nCol<0||nRow>=N||nCol>=N) {
                outSandAmount+=casePer;
                continue;
            }
            map[nRow][nCol]+=casePer;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        tornado(N/2,N/2,0,0,"left");
        System.out.print(outSandAmount);
    }
}
