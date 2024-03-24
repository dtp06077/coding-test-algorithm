package dfs;

import java.io.*;
import java.util.*;

public class Baekjoon_15684 {
    static int  N,M,H;
    static boolean[][] ladder;
    //static Queue<Node> queue=new LinkedList<>();
    static int minLine=0;
//    static class Node{
//        int x,y;
//        Node(int x,int y){
//            this.x=x;
//            this.y=y;
//        }
//    }
    static int ladderGame(){

        while(minLine<4){
            manipulation(0,minLine);
            minLine++;
        }
        return -1;
    }
    static void manipulation(int row, int line){
        if(line==0){
            if(indexVertical()){
                System.out.println(minLine);
                System.exit(0);
            }
            return;
        }
        //가로선을  추가한 행을 더 이상 백트래킹할 필요가 없음!!!(중요...시간복잡도를 크게 좌우함)
        for(int i=row;i<H;i++){
            for(int j=0;j<N-1;j++){
                if(ladder[i][j] || (j > 0 && ladder[i][j - 1]) || (j < N - 2 && ladder[i][j + 1])){
                    continue;
                }

                ladder[i][j]=true;
                manipulation(i, line-1);
                ladder[i][j]=false;

            }
        }
    }
    //해당 인덱스가 사다리게임을 통해 다시 자신의 인덱스로 돌아오는지 확인하는 메서드
    static boolean indexVertical(){
        for(int i=0;i<N;i++){
            int startX=i;
            int startY=0;
            while(startY<H){
                if(startX<N-1&&ladder[startY][startX]){
                    startX++;
                    startY++;
                }
                else if(startX>0&&ladder[startY][startX-1]){
                    startX--;
                    startY++;
                }
                else{
                    startY++;
                }
            }
            if(startX!=i) return false;
        }
        return true;
    }

    //시간 초과의 원인. 탐색마다 2차원 배열을 선언하므로 메모리낭비 + 시간낭비
//    static boolean verticalDown(int index){
//        boolean[][] isVisited=new boolean[H][N];
//        while(!queue.isEmpty()){
//            Node node=queue.poll();
//            int x=node.x;
//            int y=node.y;
//            if(y==H){
//                if(x==index) return true;
//                else return false;
//            }
//
//            if(x>0&&!isVisited[y][x-1]&&ladder[y][x-1]){
//                queue.add(new Node(x-1,y));
//            }
//            else if(x<N-1&&!isVisited[y][x+1]&&ladder[y][x]){
//                queue.add((new Node(x+1,y)));
//            }
//            else{
//                queue.add(new Node(x,y+1));
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        ladder=new boolean[H][N];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int row=Integer.parseInt(st.nextToken())-1;
            int col=Integer.parseInt(st.nextToken())-1;
            ladder[row][col]=true;
        }
        System.out.println(ladderGame());
    }
}
