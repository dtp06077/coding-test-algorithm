package bfs;

import java.io.*;
import java.util.*;
import static java.lang.System.exit;

public class Baekjoon_5014 {
    static int F,S,G,U,D;
    static int[] lift;
    static int bfs(){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(S);
        lift[S]=1;

        while(!queue.isEmpty()){
            int num=queue.poll();

            if(num==G){
                return lift[num]-1;
            }
            if(num-D>=1&&lift[num-D]==0){
                queue.add(num-D);
                lift[num-D]+=lift[num]+1;
            }
            if(num+U<=F&&lift[num+U]==0){
                queue.add(num+U);
                lift[num+U]+=lift[num]+1;
            }
        }
        System.out.println("use the stairs");
        exit(0);
        return 0;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        F=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());
        G=Integer.parseInt(st.nextToken());
        U=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());

        lift=new int[F+1];
        int min=bfs();
        System.out.println(min);
    }
}
