package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

/**
 * datastructure.graph.bfs.dfs 탐색을 활용한 bruteforce로 계산하면 N의 값이 최대 100이라 시간초과
 * datastructure.graph.bfs 탐색으로 가중치 비교를 통해 경우의 수를 줄여가면서 문제를 해결해야 함.
 */

public class SWEA_1249 {
    static int[][] map;
    //가중치 값을 업데이트할 복사본 2차원 배열
    static int[][] copyMap;
    static boolean[][] visited;
    static int N;
    static int minVal;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,-1,0,1};
    static Queue<Node> queue = new LinkedList<>();

    static class Node {
        int x,y,weight;

        Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
    //Queue를 활용한 datastructure.graph.bfs 탐색
    static void bfs() {
        queue.add(new Node(0,0,0));
        visited = new boolean[N][N];
        visited[0][0]=true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int nodeX = node.x;
            int nodeY = node.y;
            int weight = node.weight;

            //가중치 값이 최솟값을 넘어가면 탐색할 필요가 없으므로 건너뜀.
            if(weight>minVal) continue;
            if(nodeX==N-1&&nodeY==N-1) {
                minVal=weight;
                continue;
            }

            for(int i=0;i<4;i++) {
                int newX = nodeX+dx[i];
                int newY = nodeY+dy[i];
                if(newX<0||newY<0||newX>=N||newY>=N) continue;
                //방문한 노드가 아닐 경우 가중치 값으로 노드 업데이트.
                if(!visited[newX][newY]) {
                    copyMap[newX][newY]+=weight;
                    visited[newX][newY]=true;
                    queue.add(new Node(newX,newY,copyMap[newX][newY]));
                }
                //방문한 노드일 경우 이미 가중치 값으로 노드가 업데이트 되어 있으므로
                //해당 노드값과 가중치와 비교하여 더 작은 수로 업데이트.
                else {
                    if(weight+map[newX][newY]<copyMap[newX][newY]) {
                        copyMap[newX][newY]=weight+map[newX][newY];
                        queue.add(new Node(newX,newY,copyMap[newX][newY]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answer = new int[T];

        for(int i=0;i<T;i++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            copyMap = new int[N][N];
            minVal = Integer.MAX_VALUE;

            for(int j=0;j<N;j++) {
                String line = br.readLine();
                for(int k=0;k<N;k++) {
                    map[j][k] = line.charAt(k)-'0';
                    copyMap[j][k] = line.charAt(k)-'0';
                }
            }
            bfs();
            answer[i] = minVal;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}