package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 100개의 정수를 입력받아 dump 만큼의 횟수 안에 최댓값과 최소값의 차를 최소화하는 평탄화 작업 문제.
 * ====풀이====
 * 1) 100개의 정수를 입력받고 오름차순으로 정렬
 *
 * 2) start 인덱스와 end 인덱스를 기점으로 로직 구성
 *    - start 인덱스와 다음 인덱스의 값이 같을 경우 -> 평탄화가 완료된 것이므로 0부터 다시 평탄화
 *    - start 인덱스보다 다음 인덱스의 값이 작을 경우 -> 다음 인덱스 평탄화
 *    - start 인덱스보다 다음 인덱스의 값이 클 경우 -> 0부터 다시 평탄화
 *    - end 인덱스도 위의 로직과 같음.
 *
 * **중요**
 * 3) dump 횟수를 모두 소비하면 현재 start 인덱스와 end 인덱스의 차이를 리턴 -> X
 *    평탄화 작업이 수행중일 수 있으므로 start 인덱스와 end 인덱스 사이에 최댓값과 최솟값이 존재할 수 있음.
 *    이를 찾아낸 후 리턴해야 함.
 */

public class SWEA_1208 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 1;
        int[] boxList;
        while (TC <= 10) {
            int dump = Integer.parseInt(br.readLine());
            boxList = new int[100];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                boxList[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(boxList);
            int startIndex = 0;
            int endIndex = 99;

            while (true) {
                if (dump == 0) {
                    for (int i = startIndex; i < 100; i++) {
                        if (boxList[i] < boxList[startIndex]) {
                            startIndex = i;
                            break;
                        }
                    }
                    for (int i = endIndex; i >= 0; i--) {
                        if (boxList[endIndex] < boxList[i]) {
                            endIndex = i;
                            break;
                        }
                    }
                    break;
                }
                if (boxList[endIndex] - boxList[startIndex] <= 1 || startIndex + 1 == endIndex)
                    break;

                if (boxList[startIndex] == boxList[startIndex + 1]) {
                    startIndex = 0;
                } else if (boxList[startIndex] > boxList[startIndex + 1]) {
                    startIndex++;
                } else {
                    startIndex = 0;
                }
                if (boxList[endIndex] == boxList[endIndex - 1]) {
                    endIndex = 99;
                } else if (boxList[endIndex] < boxList[endIndex - 1]) {
                    endIndex--;
                } else {
                    endIndex = 99;
                }
                boxList[startIndex]++;
                boxList[endIndex]--;
                dump--;
            }

            System.out.println("#" + TC + " " + (boxList[endIndex] - boxList[startIndex]));
            TC++;
        }
    }
}
