package datastructure.graph.bfs;

import java.util.*;
import java.io.*;

public class Baekjoon_9205 {
    static int n,startX,startY,targetX,targetY;
    static boolean[] visited;
    static Queue<Node> queue;
    static ArrayList<Node> list;
    static class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static String bfs(ArrayList<Node> list){
        queue.add(new Node(startX,startY));

        while(!queue.isEmpty()){
            Node n1=queue.poll();
            int sX=n1.x;
            int sY=n1.y;

            if(Math.abs(targetX-sX)+Math.abs(targetY-sY)<=1000){
                return "happy";
            }
            for(int i=0;i<n;i++){
                if(!visited[i]){
                    Node n2=list.get(i);
                    int storeX=n2.x;
                    int storeY=n2.y;
                    if(Math.abs(storeX-sX)+Math.abs(storeY-sY)<=1000){
                        visited[i]=true;
                        queue.add(new Node(storeX,storeY));
                    }
                }
            }
        }
        return "sad";
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        while(t>0){
            n=Integer.parseInt(br.readLine());
            visited=new boolean[n];
            queue=new LinkedList<>();
            list=new ArrayList<>();

            for(int i=0;i<n+2;i++){
                StringTokenizer st=new StringTokenizer(br.readLine());
                int x=Integer.parseInt(st.nextToken());
                int y=Integer.parseInt(st.nextToken());

                if(i==0){
                    startX=x;
                    startY=y;
                }
                else if(i==(n+1)){
                    targetX=x;
                    targetY=y;
                }
                else{
                    list.add(new Node(x,y));
                }
            }
            sb.append(bfs(list)+"\n");
            t--;
        }
        System.out.println(sb);
    }
}
