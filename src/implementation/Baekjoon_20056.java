package implementation;

import java.util.*;
import java.io.*;

public class Baekjoon_20056 {
    static int N;
    static Fireball[][] map;
    static Queue<Fireball> queue;

    static class Fireball{
        int r,c,m,s,d,cnt;
        boolean isEven, isNotSame;
        Fireball(int r,int c,int m,int s,int d,int cnt){
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
            this.cnt=cnt;

            if(d%2==0){
                this.isEven=true;
            }
        }
    }

    static int moveCommand(int K){
        int totM=0;
        while(true){
            K--;
            //step 1
            while(!queue.isEmpty()){
                Fireball fireball=direction(queue.poll());
                int r= fireball.r;
                int c= fireball.c;

                if(map[r][c]!=null){
                    int m= fireball.m;
                    int s= fireball.s;
                    boolean isEven=fireball.isEven;
                    map[r][c].m+=m;
                    map[r][c].s+=s;
                    map[r][c].cnt++;
                    if(!map[r][c].isNotSame&&map[r][c].isEven!=isEven){
                        map[r][c].isNotSame=true;
                    }
                }
                else{
                    map[r][c]=fireball;
                }
            }
            //step 2
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]!=null){
                        if(map[i][j].cnt>=2){
                            int m=map[i][j].m/5;
                            if(m==0){
                                map[i][j]=null;
                                continue;
                            }
                            int cnt=map[i][j].cnt;
                            int s=map[i][j].s/cnt;
                            if(map[i][j].isNotSame){
                                queue.add(new Fireball(i,j,m,s,1,1));
                                queue.add(new Fireball(i,j,m,s,3,1));
                                queue.add(new Fireball(i,j,m,s,5,1));
                                queue.add(new Fireball(i,j,m,s,7,1));
                            }
                            else{
                                queue.add(new Fireball(i,j,m,s,0,1));
                                queue.add(new Fireball(i,j,m,s,2,1));
                                queue.add(new Fireball(i,j,m,s,4,1));
                                queue.add(new Fireball(i,j,m,s,6,1));
                            }
                            map[i][j]=null;
                        }
                        else{
                            int m=map[i][j].m;
                            int s=map[i][j].s;
                            int d=map[i][j].d;
                            map[i][j]=null;
                            queue.add(new Fireball(i,j,m,s,d,1));
                        }
                    }
                }
            }
            if(K==0){
                for(Fireball fireball: queue){
                    totM+= fireball.m;
                }
                return totM;
            }
        }
    }

    static Fireball direction(Fireball fireball){
        int r= fireball.r;
        int c= fireball.c;
        int d= fireball.d;
        int s= fireball.s;
        switch(d){
            case 0: r=(r+N-s%N)%N; break;
            case 1: r=(r+N-s%N)%N; c=(c+s)%N; break;
            case 2: c=(c+s)%N; break;
            case 3: r=(r+s)%N; c=(c+s)%N; break;
            case 4: r=(r+s)%N; break;
            case 5: r=(r+s)%N; c=(c+N-s%N)%N; break;
            case 6: c=(c+N-s%N)%N; break;
            case 7: r=(r+N-s%N)%N; c=(c+N-s%N)%N; break;
        }
        return new Fireball(r,c, fireball.m, s,d, fireball.cnt);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        map=new Fireball[N][N];
        queue=new LinkedList<>();

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int s=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());
            Fireball fireball=new Fireball(r-1,c-1,m,s,d,1);
            queue.add(fireball);
        }
        System.out.println(moveCommand(K));
    }
}