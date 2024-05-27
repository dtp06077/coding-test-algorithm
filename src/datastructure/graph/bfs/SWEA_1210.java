package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

public class SWEA_1210 {
    static int[][] ladder;
    static boolean[][] visited;
    static Queue<Node> nodeQueue=new LinkedList<>();
    static List<Node> nodeList;
    static int[] dx={-1,1,0};
    static int[] dy={0,0,1};
    static class Node {
        int x,y;
        Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static int findX() {

        for(int i=0;i<nodeList.size();i++) {
            //매 탐색마다 방문배열 초기화
            visited=new boolean[100][100];
            int x=nodeList.get(i).x;
            int y=nodeList.get(i).y;;
            visited[y][x]=true;
            nodeQueue.add(new Node(x,y));

            if(bfs()) {
                return x;
            }
        }
        return 0;
    }

    static boolean bfs() {
        while(!nodeQueue.isEmpty()) {
            Node node=nodeQueue.poll();
            int x=node.x;
            int y=node.y;
            for(int i=0;i<3;i++) {
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(nx<0||ny<0||nx>=100||ny>=100) continue;
                if(visited[ny][nx]) continue;
                if(ladder[ny][nx]==2) return true;
                if(ladder[ny][nx]==1) {
                    visited[ny][nx]=true;
                    nodeQueue.add(new Node(nx,ny));
                    break;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] answer=new int[10][2];

        for(int i=0;i<10;i++) {
            int T = Integer.parseInt(br.readLine());
            ladder = new int[100][100];
            nodeList = new ArrayList<>();

            for(int j=0;j<100;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0;k<100;k++) {
                    ladder[j][k] = Integer.parseInt(st.nextToken());
                    if(j==0&&ladder[j][k]==1) {
                        nodeList.add(new Node(k, j));
                    }
                }
            }
            answer[i][0] = T;
            answer[i][1] = findX();
        }
        for(int[] a : answer) {
            System.out.println("#"+a[0]+" "+a[1]);
        }
    }
}
