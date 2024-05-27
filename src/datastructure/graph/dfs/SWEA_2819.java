package datastructure.graph.dfs;

import java.io.*;
import java.util.*;

public class SWEA_2819 {
    static int[][] table;
    static Set<String> set;
    static void dfs(int row, int col, int depth, String seven) {
        if(row<0||col<0||row>=4||col>=4) return;
        if(depth==7) {
            set.add(seven);
            return;
        }
        seven+=table[row][col];
        dfs(row-1,col,depth+1,seven);
        dfs(row,col-1,depth+1,seven);
        dfs(row+1,col,depth+1,seven);
        dfs(row,col+1,depth+1,seven);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[] answer = new int[T];

        while(index<T) {
            table = new int[4][4];
            set = new HashSet<>();

            for(int i=0;i<4;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<4;j++) {
                    table[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<4;i++) {
                for(int j=0;j<4;j++) {
                    String seven="";
                    dfs(i,j,0,seven);
                }
            }

            answer[index]=set.size();
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
