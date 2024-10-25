package twopointer;

/**
 * 연속된 부분 수열의 합
 */
public class Programmers_Practice_2 {
    static public int[] solution(int[] sequence, int k) {
        int start = 0;
        int end = 0;
        int rStart = 0;
        int rEnd = 0;
        int diff = 1000000;
        while(start<=sequence.length-1) {
            int num = 0;
            for(int i = start; i<=end; i++) {
                num+=sequence[i];
            }
            if(num==k) {
                if(end-start<diff) {
                    rStart = start;
                    rEnd = end;
                    diff=end-start;
                }
            }
            if(num<=k) {
                if(end==sequence.length-1) {
                    start++;
                }
                else end++;
            }
            else {
                start++;
            }
        }
        int[] answer =new int[]{rStart,rEnd};
        return answer;
    }
    public static void main(String[] args) {

    }
}
