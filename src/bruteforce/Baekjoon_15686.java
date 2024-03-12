package bruteforce;

import java.util.*;
import java.io.*;

public class Baekjoon_15686 {
    static int M;
    static int min=Integer.MAX_VALUE;
    static ArrayList<Node> houses;
    static ArrayList<Node> chickens;
    static boolean[] visited;
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    static void dfs(int index,int count){
        if(count==M){
            int chickenDist=chickenDist();
            min=(min<chickenDist)?min:chickenDist;
            return;
        }
        for(int i=index;i<chickens.size();i++){
            if(!visited[i]){
                visited[i]=true;
                dfs(i+1,count+1);
                visited[i]=false;
            }
        }
    }
    static int chickenDist(){
        int totalDist=0;
        for(int i=0;i<houses.size();i++){
            Node houseNode=houses.get(i);
            int houseX=houseNode.x;
            int houseY=houseNode.y;
            int dist=Integer.MAX_VALUE;

            for(int j=0;j<chickens.size();j++){
                if(visited[j]){
                    Node chickenNode=chickens.get(j);
                    int chickenX=chickenNode.x;
                    int chickenY=chickenNode.y;
                    int street=Math.abs(houseX-chickenX)+Math.abs(houseY-chickenY);
                    dist=(street<dist)?street:dist;
                }
            }
            totalDist+=dist;
        }
        return totalDist;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        houses=new ArrayList<>();
        chickens=new ArrayList<>();
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num=Integer.parseInt(st.nextToken());
                if(num==1){
                    houses.add(new Node(j,i));
                }
                if(num==2){
                    chickens.add(new Node(j,i));
                }
            }
        }
        visited=new boolean[chickens.size()];
        dfs(0,0);
        System.out.println(min);
    }
}
