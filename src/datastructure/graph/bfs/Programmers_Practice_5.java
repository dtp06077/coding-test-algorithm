package datastructure.graph.bfs;

import java.util.*;

public class Programmers_Practice_5 {

    int[] dRow = {0,1,0,-1};
    int[] dCol = {1,0,-1,0};
    int[][] visited;
    int gRow, gCol;
    int bRow, bCol;
    int answer;

    Queue<Node> queue;

    class Node {
        int row, col, move;

        Node(int row, int col, int move) {
            this.row = row;
            this.col = col;
            this.move = move;
        }
    }

    public void bfs() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int nRow = node.row;
            int nCol = node.col;
            int move = node.move;

            if(nRow==gRow&&nCol==gCol) {
                answer=move;
                break;
            }

            for(int i = 0 ; i < 4 ; i++) {
                int dr = nRow;
                int dc = nCol;

                while(true) {
                    if(dr+dRow[i]<0||dc+dCol[i]<0||dr+dRow[i]>=bRow||dc+dCol[i]>=bCol) break;
                    if(visited[dr+dRow[i]][dc+dCol[i]]==-1) break;
                    dr+=dRow[i];
                    dc+=dCol[i];
                }

                if(visited[dr][dc]!=0) continue;
                visited[dr][dc]=move;
                queue.add(new Node(dr, dc, move+1));
            }
        }
    }

    public int solution(String[] board) {
        answer = -1;
        bRow = board.length;
        bCol = board[0].length();
        visited = new int[bRow][bCol];
        queue = new LinkedList<>();

        for(int i = 0; i<bRow; i++) {
            for(int j = 0; j<bCol; j++) {
                if(board[i].charAt(j)=='G') {
                    gRow = i;
                    gCol = j;
                }
                else if(board[i].charAt(j)=='R') {
                    visited[i][j]=1;
                    queue.add(new Node(i, j, 0));
                }
                else if(board[i].charAt(j)=='D') {
                    visited[i][j]=-1;
                }
            }
        }

        bfs();

        return answer;
    }
}
