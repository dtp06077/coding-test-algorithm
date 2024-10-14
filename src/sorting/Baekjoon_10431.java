package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_10431 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(P>0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String index = st.nextToken();
            sb.append(index+" ");

            int backStep = 0;
            List<Integer> list = new LinkedList<>();

            list.add(Integer.parseInt(st.nextToken()));

            for (int i = 0; i < 19; i++) {
                int num = Integer.parseInt(st.nextToken());
                if(num < list.get(0)) {
                    backStep+=list.size();
                    list.add(0,num);
                }
                else if(num> list.get(list.size()-1)) {
                    list.add(num);
                }
                else {
                    for (int j = 0; j < list.size(); j++) {
                        if(list.get(j)>num) {
                            backStep+=list.size()-j;
                            list.add(j,num);
                            break;
                        }
                    }
                }
            }
            sb.append(backStep+"\n");
            P--;
        }

        System.out.println(sb);
    }
}