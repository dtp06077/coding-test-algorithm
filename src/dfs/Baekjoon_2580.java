package dfs;

import java.io.*;
import java.util.*;

public class Baekjoon_2580 {
    static int[][] matrix=new int[9][9];

    public static void dfs(int row, int col){
        if(row==9){
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    sb.append(matrix[i][j]+" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
        }
        if(col==9){
            dfs(row+1,0);
            return;
        }
        if(matrix[row][col]==0){
            for(int i=1;i<=9;i++){
                if(check(row, col, i)){
                    matrix[row][col]=i;
                    dfs(row,col+1);
                }
            }
            matrix[row][col]=0;
            return;
        }
        dfs(row,col+1);
    }

    public static boolean check(int row, int col, int val){
        for(int i=0;i<9;i++){
            if(matrix[row][i]==val){
                return false;
            }
            else if(matrix[i][col]==val){
                return false;
            }
        }
        int set_row=row/3*3;
        int set_col=col/3*3;
        for(int i=set_row;i<set_row+3;i++){
            for(int j=set_col;j<set_col+3;j++){
                if(matrix[i][j]==val){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<9;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                matrix[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
    }
}
