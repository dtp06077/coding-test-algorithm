package datastructure.queue;

import java.util.*;
import java.io.*;

public class Baekjoon_16235 {
    static int N;
    static int[][] nutriments, additions;

    //Deque를 사용함으로써 정렬필요 x
    //새로운 나무들은 addFirst 메서드를 통해 배열의 앞부분에 추가하면 되기 때문.
    static Deque<Tree> treeList;
    static int[] dx={0,1,1,1,0,-1,-1,-1};
    static int[] dy={-1,-1,0,1,1,1,0,-1};

    static class Tree{
        int x,y,age;

        Tree(int x,int y,int age){
            this.x=x;
            this.y=y;
            this.age=age;
        }
    }

    static int fourSeasons(int K){
        Queue<Tree> nutrimentQueue=new LinkedList<>();
        Queue<Tree> tempQueue=new LinkedList<>();

        while(true){
            //spring
            int size= treeList.size();
            for(int i=0;i<size;i++){
                Tree tree=treeList.poll();
                int x= tree.x;
                int y= tree.y;
                int age= tree.age;
                if(nutriments[y][x]<age){
                    nutrimentQueue.add(tree);
                }
                else{
                    nutriments[y][x]-=age;
                    treeList.add(new Tree(x,y,age+1));
                }
            }
            //summer
            while(!nutrimentQueue.isEmpty()){
                Tree tree=nutrimentQueue.poll();
                int x= tree.x;
                int y= tree.y;
                int nutriment= tree.age/2;
                nutriments[y][x]+=nutriment;
            }
            //fall
            for(Tree tree:treeList){
                if(tree.age%5==0){
                    tempQueue.add(tree);
                }
            }
            while(!tempQueue.isEmpty()){
                Tree tree=tempQueue.poll();
                breeding(tree.x,tree.y);
            }
            //살아있는 나무의 개수를 구하는 목적이므로 양분추가 필요 X
            K--;
            if(K==0) break;

            //winter
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    nutriments[i][j]+=additions[i][j];
                }
            }
        }
        return treeList.size();
    }

    static void breeding(int x,int y){
        for(int i=0;i<8;i++){
            int nx=dx[i]+x;
            int ny=dy[i]+y;
            if(nx<0||ny<0||nx>=N||ny>=N) continue;
            treeList.addFirst(new Tree(nx,ny,1));
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        nutriments=new int[N][N];
        additions=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                nutriments[i][j]=5;
                additions[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        treeList=new LinkedList<>();
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            int z=Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x-1,y-1,z));
        }
        System.out.print(fourSeasons(K));
    }
}
