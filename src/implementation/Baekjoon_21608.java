package implementation;

import java.util.*;
import java.io.*;

public class Baekjoon_21608 {
    static int N;
    static HashMap<Integer, Integer[]> hashMap=new HashMap<>();
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static int[][] room;
    static class Node{
        int x,y,wishCount,zeroCount;
        Node(int x, int y, int wishCount, int zeroCount){
            this.x=x;
            this.y=y;
            this.wishCount=wishCount;
            this.zeroCount=zeroCount;
        }
    }
    static void placement(int studentId){
        Integer[] wishList=hashMap.get(studentId);
        int f1=wishList[0];
        int f2=wishList[1];
        int f3=wishList[2];
        int f4=wishList[3];

        ArrayList<Node> nodeList=new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int wishCount=0;
                int zeroCount=0;
                for(int k=0;k<4;k++){
                    int nx=dx[k]+j;
                    int ny=dy[k]+i;
                    if(nx<0||ny<0||nx>=N||ny>=N) continue;
                    if(room[ny][nx]==f1||room[ny][nx]==f2||room[ny][nx]==f3||room[ny][nx]==f4){
                        wishCount++;
                    }
                    if(room[ny][nx]==0) zeroCount++;
                }
                nodeList.add(new Node(j,i,wishCount,zeroCount));
            }
        }
        Collections.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if(node1.wishCount<node2.wishCount) return 1;
                else if(node1.wishCount==node2.wishCount){
                    if(node1.zeroCount<node2.zeroCount) return 1;
                    else if(node1.zeroCount==node2.zeroCount){
                        if(node1.y>node2.y) return 1;
                        else if(node1.y==node2.y){
                            if(node1.x>node2.x) return 1;
                        }
                    }
                }
                return -1;
            }
        });
        for(int i=0;i<nodeList.size();i++){
            int x=nodeList.get(i).x;
            int y=nodeList.get(i).y;
            if(room[y][x]==0){
                room[y][x]=studentId;
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        room=new int[N][N];
        int totLike=0;

        for(int i=0;i<N*N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int n3=Integer.parseInt(st.nextToken());
            int n4=Integer.parseInt(st.nextToken());
            int n5=Integer.parseInt(st.nextToken());
            hashMap.put(n1, new Integer[]{n2,n3,n4,n5});
            placement(n1);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int cnt=0;
                Integer[] wishList=hashMap.get(room[i][j]);
                int f1=wishList[0];
                int f2=wishList[1];
                int f3=wishList[2];
                int f4=wishList[3];
                for(int k=0;k<4;k++){
                    int nx=dx[k]+j;
                    int ny=dy[k]+i;
                    if(nx<0||ny<0||nx>=N||ny>=N) continue;
                    if(room[ny][nx]==f1||room[ny][nx]==f2||room[ny][nx]==f3||room[ny][nx]==f4) {
                        cnt++;
                    }
                }
                switch(cnt){
                    case 1: totLike+=1; break;
                    case 2: totLike+=10; break;
                    case 3: totLike+=100; break;
                    case 4: totLike+=1000; break;
                }
            }
        }
        System.out.println(totLike);
    }
}
