package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_17140 {

    static int[][] arrList;
    static int calculation(int r, int c, int k, int row, int col){
        int sec=0;
        HashMap<Integer, Integer> map=new HashMap<>();
        while(sec<=100){
            if(arrList[r-1][c-1]==k) return sec;

            if(row>=col){
                col=rCal(row,col,map);
            }
            else{
                row=cCal(row,col,map);
            }
            sec++;
        }
        return -1;
    }
    static int cCal(int row, int col, HashMap<Integer, Integer> map){
        int outRow=row;
        for(int j=0;j<col;j++){
            for(int i=0;i<row;i++){
                int num=arrList[i][j];
                if(num!=0){
                    map.put(num, map.getOrDefault(num,0)+1);
                    arrList[i][j]=0;
                }
            }
            List<Integer> list=mapSort(map);

            int index=0;
            for(int k=0;k<list.size();k++){
                arrList[index][j]=list.get(k);
                arrList[index+1][j]=map.get(list.get(k));
                index+=2;
            }
            map.clear();
            if(index>outRow) outRow=index;
        }
        return outRow;
    }
    static int rCal(int row, int col, HashMap<Integer, Integer> map){
        int outCol=col;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int num=arrList[i][j];
                if(num!=0){
                    map.put(num, map.getOrDefault(num,0)+1);
                    arrList[i][j]=0;
                }
            }
            List<Integer> list=mapSort(map);

            int index=0;
            for(int k=0;k<list.size();k++){
                arrList[i][index]=list.get(k);
                arrList[i][index+1]=map.get(list.get(k));
                index+=2;
            }
            map.clear();
            if(index>outCol) outCol=index;
        }
        return outCol;
    }
    static List<Integer> mapSort(HashMap<Integer, Integer> map){
        List<Integer> list=new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(map.get(o1).equals(map.get(o2))){
                    return o1.compareTo(o2);
                }
                else return map.get(o1)-map.get(o2);
            }
        });
        return list;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int r=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        arrList=new int[201][201];
        for(int i=0;i<3;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                arrList[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(calculation(r,c,k,3,3));
    }
}
