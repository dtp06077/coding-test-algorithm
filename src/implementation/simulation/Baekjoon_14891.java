package implementation.simulation;

import java.io.*;
import java.util.*;

public class Baekjoon_14891 {
    static LinkedList[] gears;
    static boolean[] contact;
    static void rolling(int direction,int index){
        if(direction==-1){
            gears[index].add(gears[index].removeFirst());
        }
        else{
            gears[index].addFirst(gears[index].removeLast());
        }
        switch(index){
            case 0:
                if(contact[0]){
                    contact[0]=false;
                    rolling(-1*direction, 1);
                }
                break;
            case 1:
                if(contact[0]){
                    contact[0]=false;
                    rolling(-1*direction, 0);
                }
                if(contact[1]){
                    contact[1]=false;
                    rolling(-1*direction, 2);
                }
                break;
            case 2:
                if(contact[1]){
                    contact[1]=false;
                    rolling(-1*direction, 1);
                }
                if(contact[2]){
                    contact[2]=false;
                    rolling(-1*direction, 3);
                }
                break;
            case 3:
                if(contact[2]){
                    contact[2]=false;
                    rolling(-1*direction, 2);
                }
                break;
        }
    }

    static void contactCheck(){
        for(int i=0;i<3;i++){
            if(gears[i].get(2)!=gears[i+1].get(6)){
                contact[i]=true;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        gears=new LinkedList[4];

        for(int i=0;i<4;i++){
            String line=br.readLine();
            LinkedList<Integer> gear=new LinkedList<>();

            for(int j=0;j<8;j++){
                int pole=line.charAt(j)-'0';
                gear.add(pole);
            }
            gears[i]=gear;
        }

        int K=Integer.parseInt(br.readLine());
        contact=new boolean[3];

        for(int i=0;i<K;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int index=Integer.parseInt(st.nextToken())-1;
            int direction=Integer.parseInt(st.nextToken());

            contactCheck();
            rolling(direction,index);
            contact=new boolean[4];
        }

        int score=0;
        for(int i=0;i<4;i++){
            if(gears[i].get(0).equals(1)){
                switch(i){
                    case 0:
                        score+=1;
                        break;
                    case 1:
                        score+=2;
                        break;
                    case 2:
                        score+=4;
                        break;
                    case 3:
                        score+=8;
                        break;

                }
            }
        }
        System.out.print(score);
    }
}
