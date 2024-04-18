package implementation;

import java.io.*;
import java.util.*;

public class Baekjoon_16637 {
    static int N;
    static String sb;
    //괄호를 추가할 연산자 방문을 체크할 배열
    static boolean[] visited;
    //수를 저장할 배열
    static ArrayList<Long> numList;
    //연산자를 저장할 배열
    static StringBuilder opeList;
    //정답의 최솟값은 (-)2의 31제곱
    static long max=-(long)Math.pow(2,31);
    //괄호 개수를 추가하지 않는 경우의 수도 있으므로 0부터 시작
    static int target=0;

    //괄호를 추가하는 경우의 수를 구할 메서드
    static void addBrackets() {

        //괄호의 최대 갯수는 N/4를 넘지 않음
        while(target<=N/4) {
            //괄호의 수가 최대 N/4개 까지 모든 경우의 수 탐색
            bfs(1,0);
            target++;
            visited=new boolean[N];
        }
    }

    static void bfs(int index, int count) {
        if(count==target) {
            //괄호를 추가할 연산자를 다 선택했으면 계산 메서드 호출
            max=Math.max(max,calc());
            return;
        }
        for(int i=index;i<N-1;i+=2){
            if(!visited[i]) {
                visited[i]=true;
                bfs(i+4,count+1);
                visited[i]=false;
            }
        }
    }
    static long calc() {
        long total=0;
        numList=new ArrayList<>();
        opeList=new StringBuilder();

        for(int i=1;i<N-1;i+=2) {
            //해당 연산자에 괄호가 추가됐다면
            if(visited[i]) {
                long n1=sb.charAt(i-1)-'0';
                long n2=sb.charAt(i+1)-'0';
                char ope=sb.charAt(i);
                //첫번째 연산자일 경우 숫자 배열이 비어있으므로 패스
                if(!numList.isEmpty()) {
                    numList.remove(numList.size()-1);
                }
                //미리 계산하여 숫자 배열에 추가
                numList.add(arithmetic(n1,n2,ope));
            }
            //괄호가 추가되지 않은 연산자라면
            else {
                if(i==1) {
                    numList.add((long)sb.charAt(i-1)-'0');
                }
                //숫자 리스트에 수, 연산자 리스트에 수식 추가
                numList.add((long)sb.charAt(i+1)-'0');
                opeList.append(sb.charAt(i));
            }
        }
        //런타임 에러 발생
        //입력받은 문자열이 하나의 수라면 그 수를 리턴
        if(numList.isEmpty()) {
            return sb.charAt(0)-'0';
        }
        total+=numList.get(0);
        for(int i=1;i<numList.size();i++) {
            total=arithmetic(total, numList.get(i),opeList.charAt(i-1));
        }
        return total;
    }
    //문자열로 된 연산자를 계산하게 해주는 메서드
    static long arithmetic(long n1, long n2, char ope) {
        switch(ope) {
            case '-' : return n1-n2;
            case '+' : return n1+n2;
            case '*' : return n1*n2;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        visited=new boolean[N];

        sb=br.readLine();
        addBrackets();
        System.out.println(max);
    }
}
