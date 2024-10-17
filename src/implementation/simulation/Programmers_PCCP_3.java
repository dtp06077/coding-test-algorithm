package implementation.simulation;

import java.util.*;

/**
 * 충돌위험 찾기
 */
public class Programmers_PCCP_3 {

    int[][] map = new int[101][101];
    Queue<Node> queue;
    int cnt = 0;
    class Node {
        int x,y, targetX, targetY, robotNum, level;
        Node(int x, int y, int targetX, int targetY, int robotNum, int level) {
            this.x = x;
            this.y = y;
            this.targetX = targetX;
            this.targetY = targetY;
            this.robotNum = robotNum;
            this.level = level;
        }
    }

    public void bfs(int[][] points, int[][] routes) {

        while(!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Node node = queue.poll();
                int x = node.x;
                int y = node.y;
                int targetX = node.targetX;
                int targetY = node.targetY;
                int robotNum = node.robotNum;
                int level = node.level;

                map[y][x]+=1;

                if(x==targetX&&y==targetY) {
                    if(level==routes[0].length-1) continue;
                    level+=1;
                    int newPoint = routes[robotNum][level];
                    targetX = points[newPoint-1][1];
                    targetY = points[newPoint-1][0];
                }

                if(y==targetY) {
                    x=move(x, targetX);
                }
                else {
                    y=move(y, targetY);
                }
                queue.add(new Node(x,y,targetX,targetY,robotNum,level));
            }

            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= 100; j++) {
                    if(map[i][j]>=1) {
                        if(map[i][j]>=2) cnt+=1;
                        map[i][j]=0;
                    }
                }
            }
        }
    }

    int move(int pos, int target) {
        if(pos<target) {
            pos+=1;
        }
        else if(pos>target) {
            pos-=1;

        }
        return pos;
    }

    public int solution(int[][] points, int[][] routes) {
        queue = new LinkedList<>();

        for (int i = 0; i < routes.length; i++) {
            int startIndex = routes[i][0];
            int targetIndex = routes[i][1];
            int x = points[startIndex-1][1];
            int y = points[startIndex-1][0];
            int targetX = points[targetIndex-1][1];
            int targetY = points[targetIndex-1][0];
            Node node = new Node(x,y,targetX,targetY,i,1);
            queue.add(node);
        }
        bfs(points, routes);
        return cnt;
    }
}
