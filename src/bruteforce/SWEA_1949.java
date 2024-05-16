package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N*N 배열에 산의 높이가 주어지고 가장 높은 봉우리에서 시작하여 내려가는 등산로를 조성하는
 * 경우의 수 중 가장 긴 등산로를 조성했을 때, 그 길이를 출력하는 문제.
 * 정수 K가 주어지고, 모든 높이는 필요하면 K만큼 소비하여 최소 0까지 깎을 수 있음.
 * =====풀이=====
 * 1) 높은 봉우리의 좌표들을 우선 리스트에 저장.
 *
 * 2) N*N 배열을 돌며 높이가 0보다 크거나 같을때까지, K 만큼의 높이를 깎을 수 있는 모든 경우의 수 탐색.
 *
 * 3) 봉우리의 좌표부터 시작하여 너비우선 탐색으로 가장 낮은 곳 까지 탐색.
 *
 * 4) 각 좌표의 가중치(dist)가 maxDist 보다 클 경우, maxDist 갱신.
 */
public class SWEA_1949 {
    static int N, maxDist;
    static int[][] map;
    static Queue<Node> queue = new LinkedList<>();
    static List<Node> topList;
    static int[] dRow = { 0, -1, 0, 1 };
    static int[] dCol = { 1, 0, -1, 0 };

    static class Node {
        int row, col, dist;

        Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static void queueAdd() {
        for (int i = 0; i < topList.size(); i++) {
            queue.add(topList.get(i));
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int col = node.col;
            int dist = node.dist;
            if (dist>maxDist) maxDist = dist;

            for (int i = 0; i < 4; i++) {
                int nRow = row + dRow[i];
                int nCol = col + dCol[i];
                if (nRow < 0 || nCol < 0 || nRow >= N || nCol >= N)
                    continue;
                if (map[nRow][nCol] >= map[row][col])
                    continue;
                queue.add(new Node(nRow, nCol, dist + 1));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 1;

        while (index <= T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int top = 0;
            map = new int[N][N];
            maxDist = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num > top)
                        top = num;
                    map[i][j] = num;
                }
            }

            topList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == top)
                        topList.add(new Node(i, j, 1));
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int height = map[i][j];
                    int k = 1;
                    while (k <= K) {
                        if (map[i][j] == (-1))
                            break;
                        map[i][j]--;
                        queueAdd();
                        bfs();
                        k++;
                    }
                    map[i][j] = height;
                }
            }
            System.out.println("#" + index + " " + maxDist);
            index++;
        }
    }
}