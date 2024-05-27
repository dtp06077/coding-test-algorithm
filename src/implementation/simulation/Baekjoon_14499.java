package implementation.simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_14499{
    static int N,M,K;
    static int[][] map;
    static int[] commandList;
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static class Dice{
        int front,back,top,bottom,left,right;
        Dice(int front, int back, int top, int bottom, int left, int right){
            this.front=front;
            this.back=back;
            this.top=top;
            this.bottom=bottom;
            this.left=left;
            this.right=right;
        }
    }
    static void move(int x, int y){
        Dice dice=new Dice(0,0,0,0,0,0);
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(x,y));
        int index=0;

        while(index<K){
            int command=commandList[index];
            Node node=queue.poll();
            int sx=node.x;
            int sy=node.y;
            switch (command){
                case 1: sy+=1; break;
                case 2: sy-=1; break;
                case 3: sx-=1; break;
                case 4: sx+=1; break;
            }
            if(sx<0||sy<0||sy>=M||sx>=N) {
                queue.add(node);
                index++;
                continue;
            }
            else{
                rolling(dice, command);
                if(map[sx][sy]==0){
                    map[sx][sy]=dice.bottom;
                }
                else{
                    dice.bottom=map[sx][sy];
                    map[sx][sy]=0;
                }
                System.out.println(dice.top);
                queue.add(new Node(sx,sy));
                index++;
            }
        }
    }
    static void rolling(Dice dice, int direct){
        int temp1, temp2, temp3;
        switch(direct){
            case 1:{
                temp1=dice.top;
                dice.top=dice.right;
                temp2=dice.left;
                dice.left=temp1;
                temp3=dice.bottom;
                dice.bottom=temp2;
                dice.right=temp3;
                break;
            }
            case 2:{
                temp1=dice.top;
                dice.top=dice.left;
                temp2=dice.right;
                dice.right=temp1;
                temp3=dice.bottom;
                dice.bottom=temp2;
                dice.left=temp3;
                break;
            }
            case 3:{
                temp1=dice.top;
                dice.top=dice.back;
                temp2=dice.front;
                dice.front=temp1;
                temp3=dice.bottom;
                dice.bottom=temp2;
                dice.back=temp3;
                break;
            }
            case 4:{
                temp1=dice.front;
                dice.front=dice.bottom;
                temp2=dice.top;
                dice.top=temp1;
                temp3=dice.back;
                dice.back=temp2;
                dice.bottom=temp3;
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int x=Integer.parseInt(st.nextToken());
        int y=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        map=new int[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        commandList=new int[K];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            commandList[i]=Integer.parseInt(st.nextToken());
        }
        move(x,y);
    }
}

