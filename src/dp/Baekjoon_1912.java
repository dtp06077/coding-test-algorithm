package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int totalMax = 0;
        int subMax = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(i==0) {
                totalMax=num;
                subMax=num;
            }
            else {
                subMax=Math.max(num, subMax+num);
                totalMax=Math.max(totalMax, subMax);
            }
        }

        System.out.println(totalMax);
    }
}