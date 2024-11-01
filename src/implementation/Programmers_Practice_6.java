package implementation;

public class Programmers_Practice_6 {

    boolean check = false;

    public void rowCheck(int row, char chr, String[] board) {
        if(check) return;
        String line = board[row];
        for(int j = 0; j < 3; j++) {
            if(line.charAt(j)!=chr) return;
        }
        check=true;
    }

    public void colCheck(int col, char chr, String[] board) {
        if(check) return;
        for(int i = 0; i < 3; i++) {
            String line = board[i];
            if(line.charAt(col)!=chr) return;
        }
        check=true;
    }

    public void leftCheck(char chr, String[] board) {
        if(check) return;
        for(int i = 0 ; i<3; i++) {
            String line = board[i];
            if(line.charAt(i)!=chr) return;
        }
        check=true;
    }

    public void rightCheck(char chr, String[] board) {
        if(check) return;
        for(int i = 0 ; i<3; i++) {
            String line = board[i];
            if(line.charAt(2-i)!=chr) return;
        }
        check=true;
    }

    public int solution(String[] board) {
        int answer = 1;
        int oCnt = 0;
        int xCnt = 0;

        for(int i = 0; i < 3; i++) {
            String line = board[i];
            for(int j = 0; j < 3; j++) {
                if(line.charAt(j)=='O') oCnt++;
                if(line.charAt(j)=='X') xCnt++;
            }
        }

        if(xCnt>oCnt) answer=0;
        else {
            if(oCnt-xCnt>1) answer=0;
            else {
                if(oCnt==xCnt) {
                    for(int i = 0; i<3 ; i++) {
                        rowCheck(i, 'O', board);
                        colCheck(i, 'O', board);
                    }
                    leftCheck('O', board);
                    rightCheck('O', board);
                    if(check) answer = 0;
                }
                else {
                    for(int i = 0; i<3 ; i++) {
                        rowCheck(i, 'X', board);
                        colCheck(i, 'X', board);
                    }
                    leftCheck('X', board);
                    rightCheck('X', board);
                    if(check) answer = 0;
                }
            }
        }
        return answer;
    }
}
