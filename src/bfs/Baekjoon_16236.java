package bfs;

import java.io.*;
import java.util.*;

public class Baekjoon_16236 {
    static int N, sharkX, sharkY;
    static boolean isPossible;
    static int[][] map;
    //방문 여부를 체크할 배열
    static boolean[][] isVisited;
    //방문 노드를 저장할 큐
    static Queue<Node> queue=new LinkedList<>();
    //같은 최소 거리를 갖는 노드를 저장할 리스트
    static  ArrayList<Node> arrayList=new ArrayList<>();
    static int time=0;
    static int[] dx={0,-1,1,0};
    static int[] dy={-1,0,0,1};
    static class Node{
        int x,y,dist;
        Node(int x,int y,int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
    }
    static int sharkMove(){
        int sharkSize=2;
        int count=2;
        map[sharkY][sharkX]=0;

        while(true){
            //먹을 수 있는 물고기가 없으면 종료
            if(!isPossible) return time;
            //시작 노드 방문
            isVisited[sharkY][sharkX]=true;
            //큐에 시작 노드를 넣고 dfs 탐색
            queue.add(new Node(sharkX,sharkY,0));
            dfs(sharkSize);

            //리스트에 값이 존재하면
            if(!arrayList.isEmpty()){
                //람다식으로 y좌표 오름차순, x좌표 오름차순으로 노드 리스트를 정렬
                arrayList.sort( (o1,o2) ->  {
                    if(o1.y==o2.y){
                        return o1.x-o2.x;
                    }
                    else return o1.y-o2.y;
                });
                //정렬된 리스트의 첫번째 노드를 시작좌표로 지정 후 시간 추가
                sharkX=arrayList.get(0).x;
                sharkY=arrayList.get(0).y;
                time+=arrayList.get(0).dist;
            }
            //리스트에 값이 존재하지 않으면 종료
            else return time;

            map[sharkY][sharkX]=0;
            //먹은 물고기의 수를 카운트, 사이즈만큼 먹으면 사이즈 증가
            count--;
            if(count==0){
                sharkSize++;
                count=sharkSize;
            }
            isPossible=false;
            //먹을 수 있는 물고기가 있는지 탐색
            for (int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]!=0&&map[i][j]<sharkSize){
                        isPossible=true;
                        break;
                    }
                }
            }
            //방문 배열과 리스트 초기화
            if(isPossible){
                isVisited=new boolean[N][N];
                arrayList.clear();
            }
        }
    }
    static void dfs(int sharkSize){
        int minDist=500;

        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int dist=node.dist;
            //최소 거리를 갖는 물고기의 노드를 리스트에 저장
            if(dist<=minDist&&map[y][x]!=0&&map[y][x]<sharkSize) {
                minDist=dist;
                arrayList.add(node);
            }
            //최소 거리보다 큰 노드는 탐색 필요 X
            if(dist>minDist) continue;
            //상하좌우 노드 탐색
            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=N||ny>=N) continue;
                if(isVisited[ny][nx]||map[ny][nx]>sharkSize) continue;
                queue.add(new Node(nx,ny,dist+1));
                isVisited[ny][nx]=true;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        isVisited=new boolean[N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num=Integer.parseInt(st.nextToken());
                if(num==9){
                    sharkX=j;
                    sharkY=i;
                }
                else if(!isPossible&&num!=0&&num<2){
                    isPossible=true;
                }
                map[i][j]=num;
            }
        }
        System.out.println(sharkMove());
    }
}
