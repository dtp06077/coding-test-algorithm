package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_8979 {

    static class MedalInfo implements Comparable<MedalInfo> {
        int countryNum, goldCount, silverCount, bronzeCount;

        public MedalInfo(int countryNum, int goldCount, int silverCount, int bronzeCount) {
            this.countryNum = countryNum;
            this.goldCount = goldCount;
            this.silverCount = silverCount;
            this.bronzeCount = bronzeCount;
        }
        @Override
        public int compareTo(MedalInfo o) {
            if(this.goldCount!=o.goldCount) {
                return Integer.compare(o.goldCount, this.goldCount);
            }
            if(this.silverCount!=o.silverCount) {
                return Integer.compare(o.silverCount, this.silverCount);
            }
            if(this.bronzeCount!=o.bronzeCount) {
                return Integer.compare(o.bronzeCount, this.bronzeCount);
            }
            return 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MedalInfo medalInfo = (MedalInfo) o;
            return goldCount == medalInfo.goldCount && silverCount == medalInfo.silverCount && bronzeCount == medalInfo.bronzeCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(goldCount, silverCount, bronzeCount);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int targetCountry = Integer.parseInt(st.nextToken());
        List<MedalInfo> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());
            int n4 = Integer.parseInt(st.nextToken());

            list.add(new MedalInfo(n1, n2, n3, n4));
        }

        Collections.sort(list);

        int rank = 1;
        int increase = 1;
        for (int i = 0; i < list.size(); i++) {
            if(i!=0) {
                if(list.get(i).equals(list.get(i-1))) {
                    increase++;
                }
                else {
                    rank+=increase;

                    if(increase!=1) {
                        increase=1;
                    }
                }
            }

            if(list.get(i).countryNum==targetCountry) {
                break;
            }
        }
        System.out.println(rank);
    }
}