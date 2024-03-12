package simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_3190 {
    static int N;
    static int index=0;
    static int[][] map;
    static Deque<Node> deque;
    static Direction[] dirList;
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static class Direction{
        int sec;
        String dir;
        Direction(int sec, String dir){
            this.sec=sec;
            this.dir=dir;
        }
    }
    static int game(){
        int time=0;
        String point="E";
        while(true){
            time++;
            Node node=deque.peek();
            Node head=cardinal(node,point);
            int x=head.x;
            int y=head.y;
            if(x<0||y<0||x>=N||y>=N||map[y][x]==1) return time;
            if(map[y][x]!=2){
                Node tail=deque.pollLast();
                int tx=tail.x;
                int ty=tail.y;
                map[ty][tx]=0;
            }
            map[y][x]=1;
            deque.addFirst(head);
            if(time==dirList[index].sec){
                if(dirList[index].dir.equals("L")){
                    switch(point){
                        case "E": point="N"; break;
                        case "W": point="S"; break;
                        case "S": point="E"; break;
                        case "N": point="W"; break;
                    }
                }
                else{
                    switch(point){
                        case "E": point="S"; break;
                        case "W": point="N"; break;
                        case "S": point="W"; break;
                        case "N": point="E"; break;
                    }
                }
                if(index<dirList.length-1){
                    index++;
                }
            }
        }
    }
    static Node cardinal(Node node, String point){
        int x=node.x;
        int y=node.y;
        switch(point){
            case "E": return new Node(x+1,y);
            case "W": return new Node(x-1,y);
            case "S": return new Node(x,y+1);
            case "N": return new Node(x,y-1);
        }
        return node;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];

        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            map[y-1][x-1]=2;
        }

        int L=Integer.parseInt(br.readLine());
        dirList=new Direction[L];
        for(int i=0;i<L;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int sec=Integer.parseInt(st.nextToken());
            String dir=st.nextToken();
            dirList[i]=new Direction(sec,dir);
        }
        deque=new LinkedList<>();
        map[0][0]=1;
        deque.add(new Node(0,0));
        System.out.println(game());
    }
}
