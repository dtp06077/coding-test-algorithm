package simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_14503 {
    static Queue<Node> queue;
    static int[][] room;

    static class Node{
        int x,y,point;

        Node(int x,int y,int point){
            this.x=x;
            this.y=y;
            this.point=point;
        }
    }

    static int bfs(){
        int cnt=1;
        int n;
        Queue<Node> direction=new LinkedList<>();

        direction.add(new Node(0,-1,0));
        direction.add(new Node(-1,0,3));
        direction.add(new Node(0,1,2));
        direction.add(new Node(1,0,1));

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int point=node.point;

            while(true){
                if(direction.peek().point==point){
                    direction.add(direction.poll());
                    break;
                }
                direction.add(direction.poll());
            }

            n=4;
            while(n>0){
                int nx=direction.peek().x+x;
                int ny=direction.peek().y+y;

                if(room[ny][nx]==0){
                    room[ny][nx]=2;
                    queue.add(new Node(nx,ny,direction.peek().point));
                    cnt++;
                    break;
                }
                n--;
                direction.add(direction.poll());
            }

            if(n==0){
                switch(point){
                    case 0: y+=1; break;
                    case 1: x-=1; break;
                    case 2: y-=1; break;
                    case 3: x+=1; break;
                }
                if(room[y][x]!=1){
                    queue.add(new Node(x,y,point));
                }
                else{
                    break;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        queue=new LinkedList<>();
        room=new int[N][M];

        st=new StringTokenizer(br.readLine());
        int y=Integer.parseInt(st.nextToken());
        int x=Integer.parseInt(st.nextToken());
        int point=Integer.parseInt(st.nextToken());
        queue.add(new Node(x,y,point));

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                room[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        room[y][x]=2;

        System.out.println(bfs());

    }
}
