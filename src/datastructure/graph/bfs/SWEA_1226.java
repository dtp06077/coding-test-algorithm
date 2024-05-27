package datastructure.graph.bfs;

import java.util.*;
import java.io.*;

public class SWEA_1226 {
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> queue;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;

            if(map[y][x]==3) {
                return 1;
            }

            for(int i=0;i<4;i++) {
                int nx = dx[i]+x;
                int ny = dy[i]+y;
                if(nx<0||ny<0||nx>=16||ny>=16) continue;
                if(map[ny][nx]==1||visited[ny][nx]) continue;
                visited[ny][nx]=true;
                queue.add(new Node(nx,ny));
            }
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] answer = new int[10][2];

        for(int i=0;i<10;i++) {
            int T = Integer.parseInt(br.readLine());
            map = new int[16][16];
            visited = new boolean[16][16];
            queue = new LinkedList<>();

            for(int j=0;j<16;j++) {
                String line = br.readLine();
                for(int k=0;k<16;k++) {
                    map[j][k] = line.charAt(k)-'0';

                    if(map[j][k]==2) {
                        queue.add(new Node(k,j));
                        visited[k][j]=true;
                    }
                }
            }

            int result = bfs();
            answer[i][0] = T;
            answer[i][1] = result;
        }

        for(int[] output : answer) {
            System.out.println("#"+output[0]+" "+output[1]);
        }
    }
}
