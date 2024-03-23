package dfs;

import java.util.*;
import java.io.*;

public class Baekjoon_14888 {
    static int[] arr;
    static int[] ope=new int[4];
    static int N;
    static int MAX=Integer.MIN_VALUE;
    static int MIN=Integer.MAX_VALUE;

    public static void dfs(int num,int index){
        if(index==N){
            MAX=(MAX<num)?num:MAX;
            MIN=(MIN>num)?num:MIN;
            return;
        }
        for(int i=0;i<4;i++){
            if(ope[i]>0){
                ope[i]--;

                switch(i){
                    case 0:dfs(num+arr[index],index+1); break;
                    case 1:dfs(num-arr[index],index+1); break;
                    case 2:dfs(num*arr[index],index+1); break;
                    case 3:dfs(num/arr[index],index+1); break;
                }
                ope[i]++;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        arr=new int[N];

        StringTokenizer st1=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st1.nextToken());
        }

        StringTokenizer st2=new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            ope[i]=Integer.parseInt(st2.nextToken());
        }
        dfs(arr[0],1);
        System.out.println(MAX);
        System.out.println(MIN);
    }
}
