package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

public class Baekjoon_1697 {
    static int N,K;
    static final int[] position=new int[100001];
    static int sec;
    static void bfs(){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(N);
        position[N]=1;

        while(!queue.isEmpty()){
            int num=queue.poll();
            if(num==K){
                sec=position[num]-1;
                break;
            }
            if((num*2)<=100000&&position[num*2]==0){
                queue.add(num*2);
                position[num*2]+=position[num]+1;
            }
            if((num+1)<=100000&&position[num+1]==0){
                queue.add(num+1);
                position[num+1]+=position[num]+1;
            }
            if((num-1)>=0&&position[num-1]==0){
                queue.add(num-1);
                position[num-1]+=position[num]+1;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        bfs();
        System.out.println(sec);
    }
}
