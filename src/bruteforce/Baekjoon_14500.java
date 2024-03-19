package bruteforce;

import java.io.*;
import java.util.*;

public class Baekjoon_14500 {
    static int N,M;
    static int[][] paper;
    static int[] dArr1,dArr2,dArr3,dArr4;
    static int max=0;
    static void findMax(){
        for(int i=0;i<5;i++){
            switch (i){
                case 0: caseZero(); break;
                case 1: caseOne(); break;
                case 2: caseTwo(); break;
                case 3: caseThree(); break;
                case 4: caseFour(); break;
            }
        }
    }
    static int addNum(int row,int col,int[] dx, int[] dy){
        int addNum=0;
        int nCol,nRow;
        for(int i=0;i<4;i++){
            nCol=dx[i]+col;
            nRow=dy[i]+row;
            if(nCol<0||nRow<0||nCol>=M||nRow>=N) return 0;
            addNum+=paper[nRow][nCol];
        }
        return addNum;
    }
    static void caseZero(){
        dArr1=new int[]{0,1,2,3};
        dArr2=new int[4];
        dArr3=new int[]{0,-1,-2,-3};

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<4;k++){
                    int addNum;
                    switch (k){
                        case 0: addNum=addNum(i,j,dArr1,dArr2); max=(max>addNum)?max:addNum; break;
                        case 1: addNum=addNum(i,j,dArr2,dArr1); max=(max>addNum)?max:addNum; break;
                        case 2: addNum=addNum(i,j,dArr3,dArr2); max=(max>addNum)?max:addNum; break;
                        case 3: addNum=addNum(i,j,dArr2,dArr3); max=(max>addNum)?max:addNum; break;
                    }
                }
            }
        }
    }
    static void caseOne(){
        dArr1=new int[]{0,1,0,1};
        dArr2=new int[]{0,0,1,1};

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int addNum=addNum(i,j,dArr1,dArr2);
                max=(max>addNum)?max:addNum;
            }
        }
    }
    static void caseTwo(){
        dArr1=new int[]{0,0,0,1};
        dArr2=new int[]{0,1,2,2};
        dArr3=new int[]{0,0,0,-1};
        dArr4=new int[]{0,-1,-2,-2};

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<8;k++){
                    int addNum;
                    switch (k){
                        case 0: addNum=addNum(i,j,dArr1,dArr2); max=(max>addNum)?max:addNum; break;
                        case 1: addNum=addNum(i,j,dArr3,dArr2); max=(max>addNum)?max:addNum; break;
                        case 2: addNum=addNum(i,j,dArr4,dArr1); max=(max>addNum)?max:addNum; break;
                        case 3: addNum=addNum(i,j,dArr4,dArr3); max=(max>addNum)?max:addNum; break;
                        case 4: addNum=addNum(i,j,dArr3,dArr4); max=(max>addNum)?max:addNum; break;
                        case 5: addNum=addNum(i,j,dArr1,dArr4); max=(max>addNum)?max:addNum; break;
                        case 6: addNum=addNum(i,j,dArr2,dArr3); max=(max>addNum)?max:addNum; break;
                        case 7: addNum=addNum(i,j,dArr2,dArr1); max=(max>addNum)?max:addNum; break;
                    }
                }
            }
        }
    }
    static void caseThree(){
        dArr1=new int[]{0,0,1,1};
        dArr2=new int[]{0,1,1,2};
        dArr3=new int[]{0,0,-1,-1};
        dArr4=new int[]{0,-1,-1,-2};

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<8;k++){
                    int addNum;
                    switch (k){
                        case 0: addNum=addNum(i,j,dArr1,dArr2); max=(max>addNum)?max:addNum; break;
                        case 1: addNum=addNum(i,j,dArr3,dArr2); max=(max>addNum)?max:addNum; break;
                        case 2: addNum=addNum(i,j,dArr4,dArr1); max=(max>addNum)?max:addNum; break;
                        case 3: addNum=addNum(i,j,dArr4,dArr3); max=(max>addNum)?max:addNum; break;
                        case 4: addNum=addNum(i,j,dArr3,dArr4); max=(max>addNum)?max:addNum; break;
                        case 5: addNum=addNum(i,j,dArr1,dArr4); max=(max>addNum)?max:addNum; break;
                        case 6: addNum=addNum(i,j,dArr2,dArr3); max=(max>addNum)?max:addNum; break;
                        case 7: addNum=addNum(i,j,dArr2,dArr1); max=(max>addNum)?max:addNum; break;
                    }
                }
            }
        }
    }
    static void caseFour() {
        dArr1 = new int[]{0, -1, 1, 0};
        dArr2 = new int[]{0, 0, 0, 1};
        dArr3 = new int[]{0, 0, 0, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    int addNum;
                    switch (k) {
                        case 0: addNum=addNum(i,j,dArr1,dArr2); max=(max>addNum)?max:addNum; break;
                        case 1: addNum=addNum(i,j,dArr3,dArr1); max=(max>addNum)?max:addNum; break;
                        case 2: addNum=addNum(i,j,dArr1,dArr3); max=(max>addNum)?max:addNum; break;
                        case 3: addNum=addNum(i,j,dArr2,dArr1); max=(max>addNum)?max:addNum; break;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        paper=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                paper[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        findMax();
        System.out.println(max);
    }
}
