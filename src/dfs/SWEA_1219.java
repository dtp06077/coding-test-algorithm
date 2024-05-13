package dfs;

import java.io.*;
import java.util.*;

/**
 * 다음 노드를 가리키는 0~99가 적힌 노드들이 주어지고
 * 0에서 시작하여 99에 도달할 수 있는지 확인하는 문제.
 * 1) 단방향 탐색으로 방문표시 필요 X.
 * 2) 99에 도달할 수 있는 것을 먼저 확인하는 것이 중요하므로 너비우선탐색보단 깊이우선탐색.
 * 3) 99에 도달할 수 있다는 것이 확인되면 더 이상 탐색할 필요가 없으므로
 *    if 문을 활용해 깊이우선탐색의 분기점을 없애는 것이 중요.
 */
public class SWEA_1219 {
    static int[][] graph;
    static Queue<Integer> queue;
    static int isPossible;

    static void dfs(int start) {
        if(graph[start][0]==0) return;
        if(graph[start][0]==99||graph[start][1]==99) {
            isPossible = 1;
            return;
        }
        dfs(graph[start][0]);

        //99에 도달할 수 있다는 것이 확인되면 불필요한 분기점은 만들지 않음(더이상 탐색 X)
        if(graph[start][1]!=0&&isPossible!=1) {
            dfs(graph[start][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] answer = new int[10][2];
        int index = 0;

        while(index<10) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int TC = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            //최대 2개의 분기가 생기는 이진트리의 형태이므로 100*2 배열로 선언
            graph = new int[100][2];
            queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                if(graph[start][0]!=0) {
                    graph[start][1]=end;
                }
                else graph[start][0]=end;
            }

            isPossible = 0;
            dfs(0);

            answer[index][0] = TC;
            answer[index][1] = isPossible;
            index++;
        }

        for(int[] ans : answer) {
            System.out.println("#"+ans[0]+" "+ans[1]);
        }
    }
}
