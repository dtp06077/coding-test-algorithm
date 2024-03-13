package bfs;

import java.io.*;
import java.util.*;


public class Baekjoon_16234 {
    static int N,L,R;

    static int[][] countries;
    static boolean[][] visited;
    static Queue<Node> queue;

    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    static void bfs(ArrayList<Node> united){
        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;

            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=N||ny>=N) continue;
                if(visited[ny][nx]) continue;
                int line=Math.abs(countries[y][x]-countries[ny][nx]);
                if(L<=line&&line<=R){
                    united.add(new Node(nx,ny));
                    queue.add(new Node(nx,ny));
                    visited[ny][nx]=true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        countries=new int[N][N];
        visited=new boolean[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                countries[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        queue=new LinkedList<>();
        boolean check;
        Queue<ArrayList> unitedList=new LinkedList<>();
        int days=0;

        while(true){
            check=true;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j]){
                        visited[i][j]=true;
                        ArrayList<Node> united=new ArrayList<>();
                        queue.add(new Node(j,i));
                        united.add(new Node(j,i));
                        bfs(united);
                        unitedList.add(united);
                    }
                }
            }
            while(!unitedList.isEmpty()){
                ArrayList<Node> united=unitedList.poll();
                int totCnt=0;
                for(int i=0;i<united.size();i++){
                    Node node=united.get(i);
                    int x=node.x;
                    int y=node.y;
                    totCnt+=countries[y][x];
                }
                totCnt/=united.size();
                for(int i=0;i< united.size();i++){
                    Node node=united.get(i);
                    int x=node.x;
                    int y=node.y;
                    if(check&&totCnt!=countries[y][x]){
                        check=false;
                    }
                    countries[y][x]=totCnt;
                }
            }
            if(check) break;
            days++;
            visited=new boolean[N][N];
        }
        System.out.println(days);
    }
}
