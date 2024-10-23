package greedy;

import java.io.*;
import java.util.*;

public class Baekjoon_2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] scoreList = new boolean[N+1];
        List<Integer> dupList = new ArrayList<>();

        for(int i = 1; i <= N ;i++) {
            int num = Integer.parseInt(br.readLine());
            if(num<=N&&!scoreList[num]) {
                scoreList[num]=true;
            }
            else dupList.add(num);
        }

        Collections.sort(dupList);
        long min = 0;
        int index = 0;
        for(int i = 1; i <= N ;i++) {
            if(!scoreList[i]) {
                min+=Math.abs(dupList.get(index)-i);
                index++;
            }
        }

        System.out.println(min);
    }
}
