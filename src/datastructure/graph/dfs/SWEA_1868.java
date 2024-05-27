package datastructure.graph.dfs;

import java.io.*;
import java.util.*;

/**
 * 지뢰찾기 게임을 구현하는 문제, 좌표 클릭시 주변의 지뢰 갯수를 맵에 나타냄
 * 지뢰가 아닌 모든 구역을 최소 클릭으로 찾는것이 관건
 * 주변 8방향에 지뢰가 없으면 연쇄적으로 8방향의 칸도 자동으로 숫자를 표시함
 * 즉, 주변에 지뢰가 없는 좌표를 우선적으로 클릭하여 최대한 연쇄적으로 칸을 탐색 후
 * 그 다음 나머지 클릭 수를 계산하면 해결
 */

public class SWEA_1868 {
    static int N, count;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> checkQueue = new LinkedList<>();

    //주변 8방향을 탐색할 row, col 배열
    static int[] dCol = {-1,0,1,1,1,0,-1,-1};
    static int[] dRow = {-1,-1,-1,0,1,1,1,0};
    static class Node {
        int row,col;
        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    //입력한 좌표의 주변 8방향을 돌며 isChain 메서드 실행
    //만약 해당 방향의 isChain 값이 참이 나오면 깊이우선탐색
    static void chainCheck(int row, int col) {

        for(int i=0;i<8;i++) {
            int nRow = row+dRow[i];
            int nCol = col+dCol[i];
            if(nRow<0||nCol<0||nRow>=N||nCol>=N) continue;
            if(isChain(nRow,nCol)) {
                chainCheck(nRow,nCol);
            }
        }
    }
    //입력한 좌표의 주변 8방향에 지뢰가 있는지 확인하는 메서드
    //만약 지뢰가 없다면 체크한 방향을 방문 표시 후 표시한 만큼 전체 클릭수에서 빼기(count--)
    static boolean isChain(int row, int col) {
        int decrease = 0;
        for(int i=0;i<8;i++) {
            int nRow = dRow[i] + row;
            int nCol = dCol[i] + col;
            if(nRow<0||nCol<0||nRow>=N||nCol>=N) continue;
            if(map[nRow][nCol]=='*') {
                checkQueue.clear();
                return false;
            }
            if(!visited[nRow][nCol]) {
                checkQueue.add(new Node(nRow,nCol));
                decrease++;
            }
        }
        if(checkQueue.isEmpty()) return false;

        checkQueue.add(new Node(row,col));

        while(!checkQueue.isEmpty()) {
            Node node = checkQueue.poll();
            visited[node.row][node.col] = true;
        }
        count-=decrease;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[] answer = new int[T];

        while(index<T) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];
            count=0;

            for(int i=0;i<N;i++) {
                String line = br.readLine();
                for(int j=0;j<N;j++) {
                    map[i][j] = line.charAt(j);
                    if(map[i][j]=='*') visited[i][j]=true;
                        //지뢰를 제외한 모든 클릭 수를 미리 계산
                    else count++;
                }
            }

            //맵을 돌며 연쇄적으로 클릭되는 좌표를 우선 탐색하며 전체 클릭 수에서 빼면
            //다음에 클릭해야 할 좌표를 또 다시 탐색하지 않아도 됨
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(!visited[i][j]&&isChain(i,j)) {
                        chainCheck(i,j);
                    }
                }
            }

            answer[index]=count;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
