package implementation;

import java.io.*;
import java.util.*;

public class Baekjoon_20058 {
    static int nPow, totalIce, maxChunk;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static Queue<Integer> intQueue=new LinkedList<>();
    static Queue<Node> nodeQueue=new LinkedList<>();

    static class Node{
        int x,y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    //파이어 스톰 함수
    static void fireStorm(int L){
        int division=(int)Math.pow(2,L);
        int M=nPow/division;

        //L값에 따른 덩어리 별로 rotation 함수 실행
        for(int i=0;i<M;i++){
            for(int j=0;j<M;j++){
                rotation(i*division,j*division,division);
            }
        }

        //4방향의 얼음을 탐색해서 조건에 맞지 않으면 노드큐에 좌표 저장
        //좌표를 저장하지 않고 맵에서 즉시 수정하면 순서상 다음 좌표의 계산에 영향을 줌
        for(int i=0;i<nPow;i++){
            for(int j=0;j<nPow;j++){
                if(map[i][j]==0) continue;
                int cnt=0;
                for(int k=0;k<4;k++){
                    int nRow=dy[k]+i;
                    int nCol=dx[k]+j;
                    if(nRow<0||nCol<0||nRow>=nPow||nCol>=nPow) continue;
                    if(map[nRow][nCol]==0) continue;
                    cnt++;
                }
                if(cnt<3) nodeQueue.add(new Node(j,i));
            }
        }
        //노드큐에 저장된 좌표의 얼음의 양 1--
        while(!nodeQueue.isEmpty()){
            Node node=nodeQueue.poll();
            map[node.y][node.x]--;
        }
    }
    //격자 시계방향 회전 함수
    static void rotation(int row,int col,int division){
        //열의 순서대로 행의 역순으로 정수큐에 저장
        for(int j=col;j<division+col;j++){
            for(int i=row+division-1;i>=row;i--){
                intQueue.add(map[i][j]);
            }
        }
        //정수큐에 저장된 값을 원래의 순서로 배열
        for(int i=row;i<row+division;i++){
            for(int j=col;j<col+division;j++){
                map[i][j]=intQueue.poll();
            }
        }
    }
    //bfs를 활용하여 덩어리의 최댓값 탐색
    static int getMaxChunk(){
        int cnt=1;
        while(!nodeQueue.isEmpty()){
            Node node=nodeQueue.poll();
            int x=node.x;
            int y=node.y;
            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=nPow||ny>=nPow) continue;
                if(visited[ny][nx]||map[ny][nx]==0) continue;
                visited[ny][nx]=true;
                nodeQueue.add(new Node(nx,ny));
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        nPow=(int) Math.pow(2,N);
        map=new int[nPow][nPow];

        for(int i=0;i<nPow;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<nPow;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){
            int L=Integer.parseInt(st.nextToken());
            fireStorm(L);
        }

        visited=new boolean[nPow][nPow];
        totalIce=0;
        maxChunk=0;
        //순서대로 얼음의 합과 가장 큰 덩어리 계산
        for(int i=0;i<nPow;i++){
            for(int j=0;j<nPow;j++){
                totalIce+=map[i][j];
                if(map[i][j]>0&&!visited[i][j]){
                    visited[i][j]=true;
                    nodeQueue.add(new Node(j,i));
                    maxChunk=Math.max(maxChunk,getMaxChunk());
                }
            }
        }
        System.out.println(totalIce);
        System.out.println(maxChunk);
    }
}
