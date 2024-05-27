package datastructure.string;

import java.io.*;

public class SWEA_20019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] answer = new String[T];

        for(int i=0;i<T;i++) {
            String S = br.readLine();
            answer[i]=palindrome(S);
        }
        for(int i=1;i<=T;i++) {
            System.out.println("#"+i+" "+answer[i-1]);
        }
    }

    static String palindrome(String S) {
        int len=S.length();

        for(int i=0;i<len/2;i++) {
            if(S.charAt(i)!=S.charAt(len-1-i)) return "NO";
            if(S.charAt(i)!=S.charAt(len/2-1-i)) return "NO";
        }
        return "YES";
    }
}
