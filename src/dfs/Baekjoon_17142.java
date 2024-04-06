package dfs;

import java.io.*;
import java.util.*;

public class Baekjoon_17142 {

    static int N, M;
    static int[][] lab;
    static boolean[][] visited;
    static Queue<Node> queue=new LinkedList<>();
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    //시간의 최대치는 최악의 경우 50!근사치 이므로 안전하게 MAX_VALUE 사용
    static int minSec=Integer.MAX_VALUE;

    //좌표 저장 노드 클래스
    static class Node{
        //각각의 노드가 갖는 시간 가중치 설정
        int x,y,sec;
        Node(int x,int y,int sec){
            this.x=x;
            this.y=y;
            this.sec=sec;
        }
    }
    //dfs로 모든 경우의 수 탐색
    //활성 바이러스를 선택할 때마다 모든 연구소 좌표를 방문하면 시간초과.
    //조합 알고리즘으로 순서 필요 X, 활성 바이러스 선택 후 다음 좌표로 dfs 해야함.
    static void dfs(int row,int col,int cnt){
        if(cnt==M){
            visited=new boolean[N][N];
            //선택한 시작 노드 큐에 저장
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(lab[i][j]==-1) {
                        visited[i][j]=true;
                        queue.add(new Node(j,i,0));
                    }
                }
            }
            //bfs 탐색
            int maxSec=bfs();
            //모든 빈칸에 바이러스를 퍼뜨릴 수 없는 경우 종료
            for(int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    if(!visited[i][j]&&lab[i][j]==0){
                        return;
                    }
                }
            }
            //최솟값 저장
            minSec=Math.min(maxSec,minSec);
            return;
        }
        if(row==N){
            return;
        }
        for(int j=col;j<N;j++){
            if(lab[row][j]==2){
                lab[row][j]=-1;
                dfs(row,j+1,cnt+1);
                lab[row][j]=2;
            }
        }
        dfs(row+1,0,cnt);
    }
    //bfs로 바이러스가 퍼지는 시간 계산
    static int bfs(){
        int maxSec=0;
        while(!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int sec= node.sec;
            //시간의 최댓값 갱신
            if(sec>maxSec) {
                //만약 최댓값 좌표가 비활성 바이러스를 가리킨다면 건너뜀
                //이미 바이러스가 존재하는 좌표이기 때문에
                //탐색하여 최댓값을 갱신할 필요가 없음.
                //찾는데 오래걸린 에러

                //continue를 사용하여 for문까지 생략해버릴 수 있으니 조심...
                //80%에서 틀릴 수 있는 1시간동안 찾은 에러
                //if(lab[y][x]==2) continue;

                //비교 연산자를 같지 않음으로 바꾸자
                if(lab[y][x]!=2) maxSec=sec;
            }

            for(int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=N||ny>=N) continue;
                if(visited[ny][nx]||lab[ny][nx]==1) continue;
                visited[ny][nx]=true;
                queue.add(new Node(nx,ny,sec+1));
            }
        }
        return maxSec;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        lab=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                lab[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);

        //바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없다면
        //-1출력
        if(minSec==Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else System.out.println(minSec);
    }
}
