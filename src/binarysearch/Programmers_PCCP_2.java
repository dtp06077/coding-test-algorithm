package binarysearch;

import java.util.Arrays;

/**
 * 퍼즐 게임 챌린지
 */
public class Programmers_PCCP_2 {
    public long spendTime(int fail, int time_cur, int time_prev) {
        return fail*(time_cur+time_prev)+time_cur;
    }
    public int solution(int[] diffs, int[] times, long limit) {

        int minLev = 1;
        int maxLev = Arrays.stream(diffs).max().getAsInt();

        while(minLev<maxLev) {
            long time_cur = times[0];
            int midLev = (minLev+maxLev)/2;

            for (int i = 1; i < diffs.length; i++) {
                if(midLev>=diffs[i]) time_cur+=times[i];
                else time_cur+=spendTime(diffs[i]-midLev, times[i], times[i-1]);
            }

            if(time_cur>limit) {
                minLev=midLev+1;
            } else maxLev=midLev;
        }
        return minLev;
    }
}