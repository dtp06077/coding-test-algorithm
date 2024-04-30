package implementation;

import java.io.*;
import java.util.*;

public class Baekjoon_17135 {
    static int N,M,D;
    static List<EnemyNode> enemyList;
    static List<EnemyNode> copyList;
    static int[] archerList;
    static int maxCount=0;

    //궁수와의 거리별로 적 리스트를 정렬하기 위해 Comparable 인터페이스 오버라이딩
    static class EnemyNode implements Comparable<EnemyNode> {
        int x,y,dist;
        boolean isAttacked;

        EnemyNode(int x, int y) {
            this.x=x;
            this.y=y;
        }
        //궁수와의 거리별로 오름차순 하되, 거리가 같으면 y값이 작은 순으로 정렬
        @Override
        public int compareTo(EnemyNode o) {
            if(this.dist==o.dist) {
                return this.y-o.y;
            }
            return this.dist-o.dist;
        }
    }
    //dfs로 3명의 궁수를 선택하는 경우의 수 탐색
    static void dfs(int col, int index) {
        if(index==3) {
            maxCount=Math.max(maxCount,archerAttack());
            return;
        }
        for(int i=col;i<M;i++) {
            archerList[index]=i;
            dfs(col+1,index+1);
        }
    }
    //궁수의 공격 메서드
    static int archerAttack() {
        int count=0;
        copyList=new ArrayList<>();

        for(int i=0;i<enemyList.size();i++) {
            EnemyNode enemyNode = enemyList.get(i);
            EnemyNode newNode = new EnemyNode(enemyNode.x,enemyNode.y);
            copyList.add(newNode);
        }
        //복사한 적 리스트의 적들이 모두 제거될때 까지 반복
        while(!copyList.isEmpty()) {
            for(int i=0;i<3;i++) {
                int archerX=N;
                int archerY=archerList[i];

                for(int j=0;j<copyList.size();j++) {
                    int enemyX=copyList.get(j).x;
                    int enemyY=copyList.get(j).y;
                    int dist=Math.abs(archerX-enemyX)+Math.abs(archerY-enemyY);

                    copyList.get(j).dist=dist;
                }
                //거리별로 정렬 후 거리가 d보다 작은 우선순위 적 공격
                Collections.sort(copyList);

                if(copyList.get(0).dist<=D) {
                    copyList.get(0).isAttacked=true;
                }
            }
            //공격당한 적은 제거 후 카운트, 성에 들어온 적은 제거
            for(int i=0;i<copyList.size();i++) {
                if(copyList.get(i).isAttacked) {
                    copyList.remove(i);
                    count++;
                    i--;
                }
                else if(copyList.get(i).x==N-1) {
                    copyList.remove(i);
                    i--;
                }
                else copyList.get(i).x++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        enemyList = new ArrayList<>();
        archerList = new int[3];

        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                if(st.nextToken().equals("1")) {
                    enemyList.add(new EnemyNode(i,j));
                }
            }
        }
        dfs(0,0);
        System.out.println(maxCount);
    }
}