package implementation.simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_17144 {
    static int R,C,T;
    static int[][] room;
    static Node[] airCleaner;
    static Queue<Node> queue;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Node{
        int x,y,val;
        Node(int x,int y, int val){
            this.x=x;
            this.y=y;
            this.val=val;
        }
    }

    static void cleanFinedust(){
        while(true){
            T--;

            diffusion();
            upWind();
            downWind();

            if(T==0) break;

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(room[i][j]>=5){
                        queue.add(new Node(j,i,room[i][j]));
                    }
                }
            }
        }
    }
    static void diffusion(){
        int cnt;
        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int val=node.val;
            cnt=0;

            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=C||ny>=R) continue;
                if(room[ny][nx]==-1) continue;
                room[ny][nx]+=val/5;
                cnt++;
            }
            room[y][x]-=(val/5*cnt);
        }
    }

    static void upWind(){
        int step=1;
        Node ac=airCleaner[0];
        int ax=ac.x;
        int ay=ac.y;
        queue.add(new Node(ax+1,ay,room[ay][ax+1]));
        room[ay][ax+1]=0;

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int val=node.val;
            switch(step){
                case 1:{
                    if(x+1>=C){
                        queue.add(new Node(x,y-1,room[y-1][x]));
                        room[y-1][x]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x+1,y,room[y][x+1]));
                        room[y][x+1]=val;
                    }
                    break;
                }
                case 2:{
                    if(y-1<0){
                        queue.add(new Node(x-1,y,room[y][x-1]));
                        room[y][x-1]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x,y-1,room[y-1][x]));
                        room[y-1][x]=val;
                    }
                    break;
                }
                case 3:{
                    if(x-1<0){
                        queue.add(new Node(x,y+1,room[y+1][x]));
                        room[y+1][x]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x-1,y,room[y][x-1]));
                        room[y][x-1]=val;
                    }
                    break;
                }
                case 4:{
                    if(room[y+1][x]!=-1){
                        queue.add(new Node(x,y+1,room[y+1][x]));
                        room[y+1][x]=val;
                    }
                    break;
                }
            }
        }
    }
    static void downWind(){
        int step=1;
        Node ac=airCleaner[1];
        int ax=ac.x;
        int ay=ac.y;
        queue.add(new Node(ax+1,ay,room[ay][ax+1]));
        room[ay][ax+1]=0;

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int val=node.val;
            switch(step){
                case 1:{
                    if(x+1>=C){
                        queue.add(new Node(x,y+1,room[y+1][x]));
                        room[y+1][x]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x+1,y,room[y][x+1]));
                        room[y][x+1]=val;
                    }
                    break;
                }
                case 2:{
                    if(y+1>=R){
                        queue.add(new Node(x-1,y,room[y][x-1]));
                        room[y][x-1]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x,y+1,room[y+1][x]));
                        room[y+1][x]=val;
                    }
                    break;
                }
                case 3:{
                    if(x-1<0){
                        queue.add(new Node(x,y-1,room[y-1][x]));
                        room[y-1][x]=val;
                        step++;
                    }
                    else{
                        queue.add(new Node(x-1,y,room[y][x-1]));
                        room[y][x-1]=val;
                    }
                    break;
                }
                case 4:{
                    if(room[y-1][x]!=-1){
                        queue.add(new Node(x,y-1,room[y-1][x]));
                        room[y-1][x]=val;
                    }
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        airCleaner=new Node[2];
        int index=0;
        room=new int[R][C];
        queue=new LinkedList<>();

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int num=Integer.parseInt(st.nextToken());
                if(num==-1){
                    airCleaner[index]=new Node(j,i,num);
                    index++;
                }
                else if(num>=5){
                    queue.add(new Node(j,i,num));
                }
                room[i][j]=num;
            }
        }
        cleanFinedust();
        int totCnt=0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(room[i][j]>0){
                    totCnt+=room[i][j];
                }
            }
        }
        System.out.println(totCnt);
    }
}
