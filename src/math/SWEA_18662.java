package math;

import java.io.*;
import java.util.*;

/**
 * 주어진 세 정수를 오름차순의 등차수열로 만드는 문제.
 * x -> y -> z 순으로 등차수열이므로 x는 y, y는 z보다 무조건 작거나 같아야 함.
 * z에서 x를 뺀 값을 2로 나누고 x에 더하면 등차수열을 이룰 수 있는 y값이 됨.
 * 주어진 y값과 비교하여 그 차이를 출력하면 문제 해결.
 */
public class SWEA_18662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        double[] answer = new double[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Integer.parseInt(st.nextToken());
            double y = Integer.parseInt(st.nextToken());
            double z = Integer.parseInt(st.nextToken());
            double diff = (z-x)/2;

            double newY = diff+x;
            answer[index]= Math.abs(y-newY);
            index++;
        }

        for(int i = 1; i<=T; i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
