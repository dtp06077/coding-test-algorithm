package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * N개(N<=6)의 수, C개(C<=10)의 교환 횟수가 주어지고,
 * 주어진 수를 2개씩 총 C번 교환하여 가장 큰 수를 조합하는 문제(중복 교환 허용)
 * =====풀이=====
 * 1) 두개의 배열을 선언하고 하나는 주어진 수 그대로(기본 배열), 하나는 주어진 수를
 *    내림차순하여(내림차순 배열) 저장.
 *
 * 2) 기본 배열의 순서를 내림차순 배열과 비교하며 교환횟수 만큼 교환.
 *    기본 배열은 마지막 인덱스부터 비교하여 가장 큰 수가 앞으로 오게끔 해야함.
 *
 * 3) 중복 교환이 허용되니 이미 가장 큰 수가 만들어졌음에도 교환 횟수가 남아있다면,
 *    중복되는 수가 존재할 경우 바로 종료(중복되는 수를 계속 교환하면 되므로).
 *
 *    ****개인적으로 가장 어려웠던 부분***
 * 4) 만약 가장 큰 수가 1개 이상이라면, 뒤에서부터 비교하는 로직의 특성상,
 *    배열의 뒷부분이 정렬되지 않을 수 있음.
 *    ex) 32555 2를 예시로 들자. 뒤에서부터 비교하며 교환하면 55523이 최종값이 됨.
 *        이는 가장 큰 수(55532)가 될 수 없음.
 *    따라서 최댓값이 1개 이상이고 교환횟수보다 최댓값의 갯수가 크거나 같으면,
 *    교환횟수만큼의 인덱스를 뒤에서부터 다시 정렬해줘야함.
 */
public class SWEA_1244 {
    static int[] numList;
    static Integer[] sortedList;
    static int size;

    static void indexChange(int index) {
        for (int i = size - 1; i >= 0; i--) {
            if (numList[i] == sortedList[index]) {
                int temp = numList[i];
                numList[i] = numList[index];
                numList[index] = temp;
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 1;

        while (index <= T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String numbers = st.nextToken();
            int changeCount = Integer.parseInt(st.nextToken());
            size = numbers.length();
            numList = new int[size];
            sortedList = new Integer[size];

            for (int i = 0; i < size; i++) {
                numList[i] = numbers.charAt(i) - '0';
                sortedList[i] = numbers.charAt(i) - '0';
            }

            Arrays.sort(sortedList, Collections.reverseOrder());
            boolean sameCheck = false;
            int maxSameCount = 1;

            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if(i==0&&sortedList[i]==sortedList[j]) maxSameCount++;
                    if (sortedList[i] == sortedList[j]) {
                        sameCheck = true;
                    }
                }
                if (sameCheck)
                    break;
            }

            boolean isSame;
            int copyCount = changeCount;
            while (changeCount > 0) {
                isSame = true;
                for (int i = 0; i < size; i++) {
                    if (numList[i] != sortedList[i]) {
                        indexChange(i);
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    if (sameCheck)
                        break;
                    else {
                        int temp = numList[size - 1];
                        numList[size - 1] = numList[size - 2];
                        numList[size - 2] = temp;
                    }
                }
                changeCount--;
            }
            // 뒷부분을 추가로 정렬해주는 조건문
            if(maxSameCount>=copyCount) {
                for (int i = size-copyCount; i < size-1; i++) {
                    for (int j = i + 1; j < size; j++) {
                        if(numList[i]<numList[j]) {
                            int temp = numList[i];
                            numList[i] = numList[j];
                            numList[j] = temp;
                        }
                    }
                }
            }
            System.out.print("#" + index + " ");
            for (int i : numList) {
                System.out.print(i);
            }
            System.out.println();
            index++;
        }
    }
}
