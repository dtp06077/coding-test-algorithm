package greedy;

import java.io.*;

public class Baekjoon_2885 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int size = 1;

        while (K > size) {
            size *= 2;
        }

        int div = 0;
        int divSize = size;
        while(true) {
            if(K%divSize==0) break;
            divSize/=2;
            div++;
        }

        System.out.println(size+" "+div);
    }
}
