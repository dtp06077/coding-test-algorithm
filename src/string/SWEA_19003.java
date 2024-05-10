package string;

import java.io.*;
import java.util.*;

/**
 * 입력으로 주어진 같은 길이(M)의 중복되지 않은 문자열들을 더하는 경우의 수 중에서
 * 가장 길이가 긴 회문을 만들었을 때 그 길이를 구하는 문제.
 * 1) 문자열 중 최소 하나가 회문일 경우, 최소 길이가 M인 회문을 만들 수 있음.
 * 2) 모든 문자열이 회문일 경우, 중복되는 문자열은 없으므로 하나의 회문으로
 *    최대 길이가 M인 회문을 만들 수 있음.
 * 3) 서로 대칭인 문자열이 N쌍 있는 경우 그 문자열들을 활용해 길이가 2*M*N인 회문을 만들 수 있음.
 * 위의 경우의 수를 계산하여 문제를 해결함.
 */
public class SWEA_19003 {
    static boolean pallCheck(String S) {
        int len = S.length();
        for(int i=0;i<len/2;i++) {
            if(S.charAt(i)!=S.charAt(len-1-i)) return false;
        }
        return true;
    }
    static class Pall {
        String S;
        boolean isPall;
        Pall(String S) {
            this.S = S;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        int[] answer = new int[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Pall[] pList = new Pall[N];
            boolean[] visited = new boolean[N];
            int maxLen = 0;
            boolean onePall = false;
            boolean isAllPall = true;

            for(int i=0;i<N;i++) {
                String S = br.readLine();
                Pall pall = new Pall(S);

                if(pallCheck(S)) {
                    if(!onePall) {
                        onePall=true;
                    }
                    pall.isPall=true;
                }
                else {
                    if(isAllPall) isAllPall = false;
                }
                pList[i]=pall;
            }
            if(isAllPall) answer[index]=M;
            else {
                for(int i=0;i<N;i++) {
                    if(pList[i].isPall||visited[i]) continue;
                    String S = pList[i].S;
                    visited[i]=true;

                    for(int j=i+1;j<N;j++) {
                        if(pList[j].isPall) continue;
                        if(pallCheck(S+pList[j].S)) {
                            visited[j]=true;
                            maxLen+=2*M;
                            break;
                        }
                    }
                }
            }
            maxLen=(maxLen!=0)?(onePall)?maxLen+M:maxLen:(onePall)?M:0;
            answer[index]=maxLen;
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
