package bruteforce;

import java.util.*;

/**
 * 공원
 */
public class Programmers_PCCE_10 {

    public boolean check(int row, int col, int max, String[][] park) {
        for(int i = row; i<row+max;i++) {
            for(int j = col;j<col+max;j++) {
                if(i>=park.length||j>=park[i].length) return false;
                if(!park[i][j].equals("-1")) return false;
            }
        }

        return true;
    }

    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        Arrays.sort(mats);
        int index = mats.length-1;

        while(index>=0) {
            for(int i = 0; i<park.length;i++) {
                for(int j = 0;j<park[i].length;j++) {
                    if(park[i][j].equals("-1")) {
                        if(check(i,j,mats[index],park)) {
                            answer=mats[index];
                            break;
                        }
                    }
                }
            }
            if(answer!=-1) break;
            index--;
        }

        return answer;
    }
}
