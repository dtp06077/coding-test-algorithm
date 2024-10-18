package datastructure.graph.bfs;

import java.util.*;

/**
 * 석유 시추
 */
public class Programmers_PCCP_2 {

    int[] dr = {1,0,-1,0};
    int[] dc = {0,-1,0,1};
    Map<Integer, Integer> oilMap = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();

    public class Node {
        int row, col, oilNum;
        Node(int row, int col, int oilNum) {
            this.col=col;
            this.row=row;
            this.oilNum=oilNum;
        }
    }

    public int bfs(int[][] land, boolean[][] visited) {
        int cnt = 0;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int col = node.col;
            int oilNum = node.oilNum;
            land[row][col] = oilNum;
            cnt+=1;

            for(int i = 0;i<4;i++) {
                int dRow = row+dr[i];
                int dCol = col+dc[i];
                if(dRow<0||dCol<0||dRow>=land.length||dCol>=land[dRow].length) continue;
                if(land[dRow][dCol]!=1||visited[dRow][dCol]) continue;
                visited[dRow][dCol]=true;
                queue.add(new Node(dRow, dCol, oilNum));
            }
        }
        return cnt;
    }

    public int solution(int[][] land) {
        int answer = 0;
        int oilNum = 2;
        boolean[][] visited = new boolean[land.length][land[0].length];

        for(int i = 0; i<land.length;i++) {
            for(int j = 0;j<land[i].length;j++) {
                if(land[i][j]==1) {
                    Node node = new Node(i,j,oilNum);
                    queue.add(node);
                    visited[i][j]=true;
                    int cnt = bfs(land, visited);
                    oilMap.put(oilNum, cnt);
                    oilNum++;
                }
            }
        }

        for(int j = 0; j<land[0].length;j++) {
            int amount = 0;
            boolean[] check = new boolean[62501];
            for(int i = 0; i<land.length;i++) {
                if(land[i][j]!=0) {
                    int on = land[i][j];
                    if(!check[on]) {
                        amount+=oilMap.get(on);
                        check[on]=true;
                    }
                }
            }
            answer=Math.max(amount,answer);
        }
        return answer;
    }
}
