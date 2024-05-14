package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A는 소수를 더하고, B는 소수를 빼서 둘의 값을 같아지게 하는 문제.
 * 최대한 많은 소수를 활용해야 함. 같아질 수 없으면 -1
 * 모든 소수는 2와 3을 더하여 만들 수 있는 것을 활용
 * 1) A가 B보다 크면 같아질 수 없으므로 -1
 * 2) B와 A의 차가 짝수면 (B-A)/2 출력. 2를 활용하는 것이 최댓값이기 때문
 * 3) B와 A의 차가 3보다 크거나 같고, 홀수면 (B-A-3)/2+1 출력. 하나의 3과 2를 활용해야 하기 때문.
 * 4) 나머지는 -1.
 */
public class SWEA_17642 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 1;

        while(index<=T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            long diff = B-A;

            diff=(diff>=0&&diff%2==0)?diff/2:(diff>=3&&diff%2!=0)?(diff-3)/2+1:-1;
            System.out.println("#"+index+" "+diff);
            index++;
        }
    }
}
