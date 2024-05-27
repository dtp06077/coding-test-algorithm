package datastructure.graph.bfs;

import java.io.*;
import java.util.*;

/**
 * 가중치를 활용한 너비 우선 그래프탐색 문제.
 * 양방향 그래프를 구현한 2차원 배열 network를 활용해 서로 이어진 노드를 찾아내고
 * visited 배열을 활용해 방문표시와 가중치 저장을 동시에 해결한다.
 * 가중치가 max인 노드들 중 index가 가장 큰 값을 반환하면 문제 해결.
 */

public class SWEA_1238 {

    static boolean[][] network;
    static int[] visited;
    static Queue<Node> queue;
    static int maxNum, maxWeight;
    static class Node {
        int num, weight;
        Node(int num, int weight) {
            this.num = num;
            this. weight = weight;
        }
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int num = node.num;
            int weight = node.weight;

            if(weight>maxWeight) maxWeight = weight;

            for(int i=1;i<=100;i++) {
                if(visited[i]==0&&network[num][i]) {
                    queue.add(new Node(i,weight+1));
                    visited[i]=weight+1;
                }
            }
        }
        for(int i=1;i<=100;i++) {
            if(visited[i]==maxWeight) {
                maxNum=i;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answer = new int[10];
        int index = 0;

        while(index<10) {
            network = new boolean[101][101];
            visited = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int inputLen = Integer.parseInt(st.nextToken());
            int startNum = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<inputLen/2;i++) {
                int rowNum = Integer.parseInt(st.nextToken());
                int colNum = Integer.parseInt(st.nextToken());
                network[rowNum][colNum]=true;
            }

            maxNum=1;
            maxWeight=1;
            queue = new LinkedList<>();
            queue.add(new Node(startNum,1));
            visited[startNum]=1;
            bfs();

            answer[index] = maxNum;
            index++;
        }

        for(int i=1;i<=10;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}