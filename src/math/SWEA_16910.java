package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * N이 주어질 때, 원점을 중심으로 반지름이 N인 원 안에 포함되는 격자점(x,y 좌표가 모두 정수인 점)의 개수를 구하는 프로그램을 작성하라.
 * 다시 말하자면, x2+y2<=N2인 격자점의 개수를 구하는 프로그램을 작성하라.
 * ===풀이===
 * 4분면 중 하나의 분면만 계산하여 4를 곱하고 1을 더함.(1울 더하는 이유는 {0,0}이 있기 때문)
 * x값을 1부터 N까지, y값은 N부터 시작하여 x^2+y^2가 N^2보다 크면 y를 줄여가는 식으로 계산
 */
public class SWEA_16910 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 1;

        while (index <= T) {
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            int y = N;

            for (int x = 1; x <= N; x++) {
                while(Math.pow(x, 2)+Math.pow(y, 2)>Math.pow(N, 2)) {
                    y--;
                }
                answer+=(y+1);
            }
            answer = 4*answer+1;
            System.out.println("#" + index + " " + answer);
            index++;
        }
    }
}
