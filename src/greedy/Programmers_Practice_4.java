package greedy;

import java.util.*;

public class Programmers_Practice_4 {

    class MineralSet implements Comparable<MineralSet> {
        int diaCnt;
        int ironCnt;
        int stoneCnt;

        MineralSet(int diaCnt, int ironCnt, int stoneCnt) {
            this.diaCnt = diaCnt;
            this.ironCnt = ironCnt;
            this.stoneCnt = stoneCnt;
        }

        @Override
        public int compareTo(MineralSet m) {
            if(this.diaCnt==m.diaCnt) {
                if(this.ironCnt==m.ironCnt) {
                    return m.stoneCnt-this.stoneCnt;
                }
                return m.ironCnt-this.ironCnt;
            }
            return m.diaCnt-this.diaCnt;
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        Queue<MineralSet> queue = new PriorityQueue<>();

        int diaCnt = 0;
        int ironCnt = 0;
        int stoneCnt = 0;

        int totPicks = picks[0]*5+picks[1]*5+picks[2]*5;

        for(int i = 1; i <= minerals.length; i++) {
            if(i>totPicks) break;
            switch(minerals[i-1]) {
                case "diamond" : diaCnt++; break;
                case "iron" : ironCnt++; break;
                case "stone" : stoneCnt++; break;
            }

            if(i%5==0||i==minerals.length) {
                queue.add(new MineralSet(diaCnt, ironCnt, stoneCnt));
                diaCnt = 0;
                ironCnt = 0;
                stoneCnt = 0;
            }
        }

        int index = 0;
        while(!queue.isEmpty()) {
            if(picks[index]==0) {
                if(index==2) break;
                else {
                    index++;
                    continue;
                }
            }
            MineralSet mins = queue.poll();
            int dc = mins.diaCnt;
            int ic = mins.ironCnt;
            int sc = mins.stoneCnt;

            int dMul = 1;
            int iMul = 1;
            switch(index) {
                case 1: dMul=5; break;
                case 2: dMul=25; iMul=5; break;
            }
            picks[index]-=1;
            answer+=(dc*dMul+ic*iMul+sc);
        }

        return answer;
    }
}