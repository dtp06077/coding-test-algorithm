package implementation;

import java.io.*;
import java.util.*;

public class Baekjoon_14890 {
    static int N,L;
    static int totCnt=0;
    static int[][] map;

    static void pathCount(){
        //한번에 행 계산과 열 계산 실행
        for(int i=0;i<N;i++){
            //행 계산
            rowCal(i,map[i][0],1);
            //열 계산
            colCal(i,map[0][i],1);
        }
    }
    static void rowCal(int row,int val, int length){

        for(int i=1;i<N;i++){
            //경사가 2이상 차이날 경우 종료
            if(Math.abs(map[row][i]-val)>1) return;

            //경사가 같으면 연속된 길의 길이 증가
            if(map[row][i]==val) {
                length++;
            }
            //경사가 오르막일 경우
            else if(map[row][i]-val==1){
                //연속된 길이 L보다 작으면 종료
                if(length<L) return;
                    //새로운 길의 높이 생성
                else{
                    length=1;
                    val=map[row][i];
                }
            }
            //경사가 내리막일 경우
            else if(map[row][i]-val==-1){
                //내리막의 경사로를 놓을 수 있는지 체크
                for(int j=1;j<L;j++){
                    //경사로를 못 놓는다면 종료
                    if(map[row][i]!=map[row][i+j]) return;
                }
                //경사로를 놓고 새로운 길과 높이 초기화
                length=0;
                i=i+L-1;
                val=map[row][i];
            }
        }
        totCnt++;
    }
    static void colCal(int col,int val, int length){

        for(int i=1;i<N;i++){
            if(Math.abs(map[i][col]-val)>1) return;
            if(map[i][col]==val) {
                length++;
            }
            else if(map[i][col]-val==1){
                if(length<L) return;

                else{
                    length=1;
                    val=map[i][col];
                }
            }
            else if(map[i][col]-val==-1){
                for(int j=1;j<L;j++){
                    if(map[i][col]!=map[i+j][col]) return;
                }
                length=0;
                i=i+L-1;
                val=map[i][col];
            }
        }
        totCnt++;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());

        map=new int[N+L][N+L];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        pathCount();
        System.out.println(totCnt);
    }
}
