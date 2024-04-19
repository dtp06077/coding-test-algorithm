package simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_23288 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int score=0;
    static class Node {
        int x,y;
        Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static Queue<Node> queue=new LinkedList<>();
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static class Dice {
        int bottom,back,right,left,front,top;
        Dice(int top,int back,int right,int left,int front,int bottom) {
            this.top=top;
            this.back=back;
            this.right=right;
            this.left=left;
            this.front=front;
            this.bottom=bottom;
        }
    }
    static Dice dice=new Dice(1,2,3,4,5,6);
    static void rollDice(int x, int y, char direction, int K) {
        if(K==0) return;
        //step 1
        switch (direction) {
            case 'l' : if(y==0) {rollDice(x,y,'r',K);return;}
                y-=1; changeState('l'); break;
            case 'r' : if(y==M-1) {rollDice(x,y,'l',K);return;}
                y+=1; changeState('r'); break;
            case 'u' : if(x==0) {rollDice(x,y,'d',K);return;}
                x-=1; changeState('u'); break;
            case 'd' : if(x==N-1) {rollDice(x,y,'u',K);return;}
                x+=1; changeState('d'); break;
        }
        //step 2
        score+=addScore(x,y);
        //step 3
        if(dice.bottom>map[x][y]) {
            switch (direction) {
                case 'l' : rollDice(x,y,'u',K-1); break;
                case 'u' : rollDice(x,y,'r',K-1); break;
                case 'r' : rollDice(x,y,'d',K-1); break;
                case 'd' : rollDice(x,y,'l',K-1); break;
            }
        }
        else if(dice.bottom<map[x][y]) {
            switch (direction) {
                case 'l' : rollDice(x,y,'d',K-1); break;
                case 'd' : rollDice(x,y,'r',K-1); break;
                case 'r' : rollDice(x,y,'u',K-1); break;
                case 'u' : rollDice(x,y,'l',K-1); break;
            }
        }
        else rollDice(x,y,direction,K-1);

    }
    static int addScore(int x, int y) {
        int num=map[x][y];
        int count=0;
        queue.add(new Node(x,y));
        visited=new boolean[N][M];
        visited[x][y]=true;

        while(!queue.isEmpty()) {
            Node node=queue.poll();
            count++;
            int nx=node.x;
            int ny=node.y;
            for(int i=0;i<4;i++){
                int ax=dx[i]+nx;
                int ay=dy[i]+ny;
                if(ax<0||ay<0||ax>=N||ay>=M) continue;
                if(map[ax][ay]!=num||visited[ax][ay]) continue;
                visited[ax][ay]=true;
                queue.add(new Node(ax,ay));
            }
        }
        return count*num;
    }
    static void changeState(char direction) {
        int temp=dice.top;
        switch (direction) {
            case 'l' :
                dice.top=dice.right;
                dice.right=dice.bottom;
                dice.bottom=dice.left;
                dice.left=temp; break;
            case 'r' :
                dice.top=dice.left;
                dice.left=dice.bottom;
                dice.bottom=dice.right;
                dice.right=temp; break;
            case 'u' :
                dice.top=dice.front;
                dice.front=dice.bottom;
                dice.bottom=dice.back;
                dice.back=temp; break;
            case 'd' :
                dice.top=dice.back;
                dice.back=dice.bottom;
                dice.bottom=dice.front;
                dice.front=temp; break;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        map=new int[N][M];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        rollDice(0,0,'r',K);
        System.out.println(score);
    }
}
