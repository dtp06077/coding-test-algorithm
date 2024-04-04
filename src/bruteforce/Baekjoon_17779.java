package bruteforce;

import java.io.*;
import java.util.*;

public class Baekjoon_17779 {
    static int N;
    static int[][] city;
    static int minVal=40000;

    //경계선의 x값이 증가하는 경우
    static void xDivision(int x,int y,int d1){
        yDivision(x,y,d1,1);
        if(x+d1<N&&y-d1>1) xDivision(x,y,d1+1);
    }

    //경계선의 y값이 증가하는 경우
    static void yDivision(int x,int y,int d1,int d2){
        //경계의 길이 범위를 넘어가면 종료
        if(y+d2>N||x+d1+d2>N) return;
        manipulate(x,y,d1,d2);
        yDivision(x,y,d1,d2+1);
    }
    //입력받은 경계선 매개변수를 토대로 선거구를 나누는 함수
    static void manipulate(int x,int y,int d1,int d2){
        //5개의 선거구의 인구 수를 저장할 배열
        int[] areaCount=new int[5];
        //경계선과 경계선 내부를 담당하는 5번 선거구를 체크할 2차원 배열
        boolean[][] boundary=new boolean[N+1][N+1];

        //5번 선거구 경계선 체크
        for(int i=0;i<=d1;i++){
            boundary[x+i][y-i]=true;
            boundary[x+d2+i][y+d2-i]=true;
        }
        for(int i=0;i<=d2;i++){
            boundary[x+i][y+i]=true;
            boundary[x+d1+i][y-d1+i]=true;
        }
        //불린형 변수를 활용하여 경계선 안의 5번 선거구 구역 체크
        for(int i=x+1;i<x+d1+d2;i++){
            boolean check=false;
            for(int j=0;j<N;j++){
                if(!check&&boundary[i][j]) check=true;
                else if(check&&boundary[i][j]) check=false;
                else if(check) boundary[i][j]=true;
            }
        }
        //선거구 나누기
        for(int r=1;r<=N;r++){
            for(int c=1;c<=N;c++){
                if(boundary[r][c]) areaCount[4]+=city[r][c];
                else if((1<=r&&r<x+d1)&&(1<=c&&c<=y)) areaCount[0]+=city[r][c];
                else if((1<=r&&r<=x+d2)&&(y<c&&c<=N)) areaCount[1]+=city[r][c];
                else if((x+d1<=r&&r<=N)&&(1<=c&&c<y-d1+d2)) areaCount[2]+=city[r][c];
                else if((x+d2<r&&r<=N)&&(y-d1+d2<=c&&c<=N)) areaCount[3]+=city[r][c];
            }
        }
        //배열을 정렬하여 최댓값 최솟값 찾기
        Arrays.sort(areaCount);
        int diff=areaCount[4]-areaCount[0];
        minVal=Math.min(diff,minVal);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        city=new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                city[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<N-1;i++){
            for(int j=2;j<N;j++){
                xDivision(i,j,1);
            }
        }
        System.out.println(minVal);
    }
}
