package datastructure.tree;

import java.io.*;
import java.util.*;

/**
 * 크루스칼 알고리즘으로 최소신장트리(MST)를 구현하는 문제.
 * =====풀이=====
 * 1) 노드간의 부모 자식 정보를 저장하는 배열을 생성. 초기값은 자기 자신으로 설정.
 *
 * 2) 모든 노드간의 거리를 구하여 리스트에 저장 후 오름차순 정렬.
 *    클래스를 활용하여 두 노드와 거리를 한번에 저장.
 *
 * 3) 리스트의 요소를 순서대로 탐색. 두 노드 간의 부모를 비교 후 부모가 같으면 건너뛰고
 *    부모가 같지 않으면 노드를 다른 노드의 부모관계로 귀속.
 *
 * 4) 간선을 N-1개 찾았을 경우 종료.
 */
public class SWEA_1251 {
    static int[] parent;

    //두 노드와 두 노드의 거리를 저장할 클래스
    static class NodeInfo {
        int node1, node2;
        long dist;

        NodeInfo(int node1, int node2, long dist) {
            this.node1 = node1;
            this.node2 = node2;
            this.dist = dist;
        }
    }
    // 재귀적으로 노드의 부모를 찾는 함수
    static int find(int target) {
        if(parent[target]==target) return target;
        return find(parent[target]);
    }
    // 두 노드의 부모가 같지 않을 때, 한 노드의 부모를 다른 노드로 귀속하는 함수
    static void union(int parent1, int parent2) {
        if(parent1>parent2) parent[parent1]=parent2;
        else parent[parent2]=parent1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 1;

        while(index<=T) {
            int N = Integer.parseInt(br.readLine());
            int[] xList = new int[N];
            int[] yList = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                xList[i] = Integer.parseInt(st.nextToken());
                parent[i] = i;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                yList[i] = Integer.parseInt(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());
            ArrayList<NodeInfo> nodeList = new ArrayList<>();

            for (int i = 0; i < N-1; i++) {
                for (int j = i + 1; j < N; j++) {
                    long dist = (long)Math.pow(xList[i]-xList[j],2)+(long)Math.pow(yList[i]-yList[j],2);
                    nodeList.add(new NodeInfo(i,j,dist));
                }
            }
            //저장한 노드들의 거리를 기준으로 오름차순 정렬
            Collections.sort(nodeList, new Comparator<NodeInfo>() {
                @Override
                public int compare(NodeInfo o1, NodeInfo o2) {
                    if(o1.dist>=o2.dist){
                        return 1;
                    }
                    else return -1;
                }
            });

            long minDist = 0;
            int trunk = 0;

            for(int i = 0 ; i <nodeList.size();i++) {
                int node1 = nodeList.get(i).node1;
                int node2 = nodeList.get(i).node2;
                int parent1 = find(node1);
                int parent2 = find(node2);
                if(parent1==parent2) continue;
                union(parent1,parent2);
                minDist+=nodeList.get(i).dist;
                trunk++;
                if(trunk==N-1) break;
            }
            System.out.println("#"+index+" "+Math.round(E*minDist));
            index++;
        }
    }
}
