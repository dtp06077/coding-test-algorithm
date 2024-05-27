package datastructure.graph.bfs;

import java.util.*;
import java.io.*;

public class Baekjoon_2606 {
    static int N,M;
    static boolean[][] net;
    static boolean[] visited;
    static int cnt=0;

    public static void bfs(){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(1);
        visited[0]=true;
        while(!queue.isEmpty()){
            int num=queue.poll();
            for(int i=0;i<N;i++){
                if(net[num-1][i]&&!visited[i]){
                    queue.add(i+1);
                    visited[i]=true;
                    cnt++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());
        net=new boolean[N][N];
        visited=new boolean[N];

        for(int i=0;i<M;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int num1=Integer.parseInt(st.nextToken());
            int num2=Integer.parseInt(st.nextToken());
            net[num1-1][num2-1]=true;
            net[num2-1][num1-1]=true;
        }
        bfs();
        System.out.println(cnt);
    }
}
