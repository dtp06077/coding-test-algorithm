package dfs;

import java.io.*;
import java.util.*;

/**
 * 재귀적으로 자기 자신을 호출하여 주어진 명령을 수행.
 * row, col, memory, direction 정보를 갖는 방문 4차원 배열을 활용해 방문 체크.
 * 무한루프에 빠지는 경우의 수를 방지하기 위함.
 * '@'에 도달하면 isPossible 참으로 체크하고 함수 종료.
 * 명령이 '?'라면 동서남북으로 이동하는 경우의 수를 모두 확인해야 함.
 * 하나의 경우의 수라도 '@'에 도달하면 isPossible 체크 후 함수 종료.
 */
public class SWEA_1824 {
    static int R,C;
    static char[][] program;
    static boolean[][][][] nodeInfo;
    static boolean isPossible;
    static char[] dirList = {'<','>','^','v'};

    static void command(int row, int col, int memory, char direction) {
        if(row<0||col<0||row>=R||col>=C) {
            //마지막 노드인 R-1, C-1로 선언해야 하는데 R,C로 선언하여 무한루프 발생.
            //사소한 실수로 인해 알고리즘이 꼬일 수 있으니 꼼꼼히 체크하자.
            if(row<0) command(R-1,col,memory,direction);
            else if(row>=R) command(0,col,memory,direction);
            else if(col<0) command(row,C-1,memory,direction);
            else command(row,0,memory,direction);
            return;
        }
        if(program[row][col]=='@') {
            isPossible=true;
            return;
        }
        char com = program[row][col];
        int dirIndex = 0;
        memory = memTrans(com,memory);
        direction = dirTrans(com,memory,direction);

        for(int i=0;i<4;i++) {
            if(dirList[i]==direction) {
                dirIndex=i;
                break;
            }
        }
        if(nodeInfo[row][col][memory][dirIndex]) {
            return;
        }
        nodeInfo[row][col][memory][dirIndex]=true;

        if(com=='?') {
            command(row-1,col,memory,'^');
            if(isPossible) return;
            command(row+1,col,memory,'v');
            if(isPossible) return;
            command(row,col-1,memory,'<');
            if(isPossible) return;
            command(row,col+1,memory,'>');
            return;
        }

        switch (direction) {
            case '<' : command(row,col-1,memory,direction); break;
            case '>' : command(row,col+1,memory,direction); break;
            case 'v' : command(row+1,col,memory,direction); break;
            case '^' : command(row-1,col,memory,direction); break;
        }
    }
    static char dirTrans(char com, int memory, char direction) {
        if(com=='<'||com=='^'||com=='v'||com=='>') {
            direction=com;
        }
        else if(com=='_') {
            if(memory==0) direction='>';
            else direction='<';
        }
        else if(com=='|') {
            if(memory==0) direction='v';
            else direction='^';
        }

        return direction;
    }
    static int memTrans(char com, int memory) {
        if(0<=com-'0'&&com-'0'<=9) memory=com-'0';
        else if(com=='+') {
            if(memory==15) memory=0;
            else memory+=1;
        }
        else if(com=='-') {
            if(memory==0) memory=15;
            else memory-=1;
        }
        return memory;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int index = 0;
        String[] answer = new String[T];

        while(index<T) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            program = new char[R][C];
            nodeInfo = new boolean[R][C][16][4];
            isPossible = false;

            for(int i=0;i<R;i++) {
                String line = br.readLine();
                for(int j=0;j<C;j++) {
                    program[i][j]=line.charAt(j);
                    if(program[i][j]=='@') {
                        isPossible=true;
                    }
                }
            }
            if(!isPossible) {
                answer[index]="NO";
            }
            else {
                isPossible=false;
                command(0,0,0,'>');
                answer[index]=(isPossible)?"YES":"NO";
            }
            index++;
        }

        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }
}
