package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

/**
 * 1~N^2의 수가 적혀있는 2차원 N*N 배열이 있고 현재 노드에서 다음 노드로 이동하기 위해선
 * 다음 노드의 수가 현재 노드의 수보다 정확히 1이 더 커야 이동할 수 있다.
 * 최대로 많이 이동하기 위해서 어느 노드에서 출발해야 하는지 구하는 문제.
 * 완전탐색으로 모든 경우의 수를 찾고 각각 너비우선탐색(BFS)를 활용하여 상하좌우를 탐색해
 * 현재 노드보다 1이 클 경우에만 다음 노드의 좌표 저장
 */
public class SWEA_1861 {
    static int N;
    static int[][] map;
    static Queue<Node> queue = new LinkedList<>();
    static int[] dCol = {1,0,-1,0};
    static int[] dRow = {0,-1,0,1};

    static class Node {
        int row,col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    //너비 우선 탐색
    static int bfs(int row, int col) {
        int count = 1;
        queue.add(new Node(row, col));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int nRow = node.row;
            int nCol = node.col;

            for(int i=0;i<4;i++) {
                int dr = dRow[i]+nRow;
                int dc = dCol[i]+nCol;

                if(dr<0||dc<0||dr>=N||dc>=N) continue;
                //다음 노드가 현재 노드보다 1 클 경우 count+1후 다음 노드 탐색
                if(map[dr][dc]==map[nRow][nCol]+1) {
                    queue.add(new Node(dr,dc));
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[][] answer = new int[T][2];

        while(index<T) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            int maxCount = 0;
            int maxIndex = N*N+1;

            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    int count = bfs(i,j);
                    if(maxCount<count) {
                        maxIndex=map[i][j];
                        maxCount=count;
                    }
                    else if(maxCount==count) {
                        if(maxIndex>map[i][j]) {
                            maxIndex=map[i][j];
                        }
                    }
                }
            }

            answer[index][0] = maxIndex;
            answer[index][1] = maxCount;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1][0]+" "+answer[i-1][1]);
        }
    }
}
