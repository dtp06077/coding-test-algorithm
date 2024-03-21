package simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_15685 {
    static boolean[][] grid=new boolean[101][101];

    static void dragonCurve(int x,int y,int d,int gnt){

        //노드 정보를 지정할 어레이리스트
        ArrayList<int[]> list=new ArrayList<>();

        //시작 노드 방문
        if(!grid[y][x]) grid[y][x]=true;

        //시작노드의 좌표와 방향(시작노드는 방향 x), 화살표 머리부분인지 여부(0은 머리 x)
        int[] node=new int[]{x,y,4,0};
        list.add(node);
        //0세대 드래곤 커브
        list.add(direction(node,d));

        //세대별 드래곤커브 시작
        while(gnt>0){
            //노드 리스트 사이즈
            int size=list.size();

            //노드리스트의 좌표를 순차적으로 돌며 계산
            for(int i=size-1;i>0;i--){

                //i번째 인덱스 노드
                int[] indexNode= list.get(i);
                //마지막 인덱스 노드
                int[] lastNode= list.get(list.size()-1);
                //마지막 인덱스 노드의 좌표
                int lx=lastNode[0];
                int ly=lastNode[1];
                //마지막 인덱스 노드의 화살표 머리부분 여부
                int isHead=lastNode[3];

                //i번째 인덱스의 방향
                int nd=indexNode[2];
                //마지막 인덱스 노드 방문표시
                if(!grid[ly][lx]) grid[ly][lx]=true;
                //마지막 인덱스 노드가 화살표의 꼬리부분이라면
                if(isHead==0){
                    //시계방향이 화살표의 꼬리부분을 중심으로 돎
                    //i번째 인덱스의 방향에 따라 새로운 노드 추가
                    switch (nd){
                        case 0:list.add(new int[]{lx,ly+1,3,1}); break;
                        case 1:list.add(new int[]{lx+1,ly,0,1}); break;
                        case 2:list.add(new int[]{lx,ly-1,1,1}); break;
                        case 3:list.add(new int[]{lx-1,ly,2,1}); break;
                    }
                }
                //마지막 인덱스 노드가 화살표의 머리부분이라면
                else{
                    //시계방향이 화살표의 머리부분을 중심으로 돎
                    //i번째 인덱스의 방향에 따라 새로운 노드 추가
                    switch (nd){
                        case 0:list.add(new int[]{lx,ly-1,3,0}); break;
                        case 1:list.add(new int[]{lx-1,ly,0,0}); break;
                        case 2:list.add(new int[]{lx,ly+1,1,0}); break;
                        case 3:list.add(new int[]{lx+1,ly,2,0}); break;
                    }
                }
            }
            //for문에서 제외된 마지막 노드 방문표시
            int[] lastNode=list.get(list.size()-1);
            if(!grid[lastNode[1]][lastNode[0]])
                grid[lastNode[1]][lastNode[0]]=true;
            gnt--;
        }

    }
    static int[] direction(int[] node,int d){
        node[2]=d;
        node[3]=1;

        switch (d){
            case 0: node[0]++; break;
            case 1: node[1]--; break;
            case 2: node[0]--; break;
            case 3: node[1]++; break;
        }
        if(!grid[node[1]][node[0]]) grid[node[1]][node[0]]=true;
        return node;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            int gnt=Integer.parseInt(st.nextToken());

            dragonCurve(x,y,d,gnt);
        }

        //사각형의 갯수 세기
        int totCnt=0;
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(grid[i][j]&&grid[i+1][j]&&grid[i][j+1]&&grid[i+1][j+1]) totCnt++;

            }
        }

        System.out.println(totCnt);
    }
}
