package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] channels = new String[N+1];
        int kbs1Index = 0;
        int kbs2Index = 0;
        String buttonOrder = "";
        boolean kbsCheck = false;

        for (int i = 1; i <= N; i++) {
            String channel = br.readLine();
            channels[i]=channel;

            if(channel.equals("KBS1")) {
                kbsCheck = true;
                kbs1Index = i;
            }
            if(channel.equals("KBS2")) {
                kbs2Index = i;
            }
            if(!kbsCheck) buttonOrder+="1";
        }

        channels[0]="";
        String preTemp = channels[0];

        for (int i = 1; i <= kbs1Index; i++) {
            String inTemp = channels[i];
            channels[i]=preTemp;
            preTemp = inTemp;

            if(i!=kbs1Index) buttonOrder+="4";

            if(i!=2&&channels[i].equals("KBS2")) {
                kbs2Index=i;
            }
        }

        channels[1] = "KBS1";
        for (int i = 1; i < kbs2Index; i++) {
            buttonOrder+="1";
        }
        for (int i = 2; i < kbs2Index; i++) {
            buttonOrder+="4";
        }
        System.out.println(buttonOrder);
    }
}
