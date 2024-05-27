package implementation.simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_20055 {
    static int N,K;
    static int phase=0;
    static int zeroCount=0;

    static LinkedList<Belt> containerBelts;

    static class Belt{
        int durability;
        boolean isRobot;
        Belt(int durability, boolean isRobot){
            this.durability=durability;
            this.isRobot=isRobot;
        }
    }
    static void rotation(){
        while(true){
            phase++;
            //step 1
            containerBelts.addFirst(containerBelts.pollLast());
            containerBelts.getLast().isRobot=false;
            //step 2
            robotMove();
            //step 3
            if(containerBelts.getFirst().durability>0){
                containerBelts.getFirst().durability--;
                if(containerBelts.getFirst().durability==0) {
                    zeroCount++;
                }
                containerBelts.getFirst().isRobot=true;
            }
            //step 4
            if(zeroCount>=K) break;
        }
    }
    static void robotMove(){
        for(int i=N-1;i>=0;i--){
            if(i==N-1){
                if(containerBelts.get(i).isRobot){
                    containerBelts.get(i).isRobot=false;
                }
            }
            else{
                if((containerBelts.get(i).isRobot)&&(containerBelts.get(i+1).durability>0)&&(!containerBelts.get(i+1).isRobot)){
                    containerBelts.get(i+1).durability--;
                    if(containerBelts.get(i+1).durability==0){
                        zeroCount++;
                    }
                    containerBelts.get(i).isRobot=false;
                    containerBelts.get(i+1).isRobot=true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        containerBelts=new LinkedList<>();
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
            int num=Integer.parseInt(st.nextToken());
            containerBelts.add(new Belt(num,false));
        }
        rotation();
        System.out.println(phase);
    }
}
