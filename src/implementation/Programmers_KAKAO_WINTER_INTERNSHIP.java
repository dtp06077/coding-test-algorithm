package implementation;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 가장 많이 받은 선물
 */
public class Programmers_KAKAO_WINTER_INTERNSHIP {
    static class Info {
        int give;
        int receive;

        Info(int give, int receive) {
            this.give = give;
            this.receive = receive;
        }
    }
    static int solution(String[] friends, String[] gifts) {
        int len = friends.length;
        Info[][] friendsInfo = new Info[len][len];

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(friends[i],i);
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                Info info = new Info(0, 0);
                friendsInfo[i][j]=info;
            }
        }

        for (int i = 0; i < gifts.length; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            String giver = st.nextToken();
            String receiver = st.nextToken();
            int gIndex = map.get(giver);
            int rIndex = map.get(receiver);
            friendsInfo[gIndex][rIndex].give++;
            friendsInfo[rIndex][gIndex].receive++;
        }

        int[] giftScore = new int[len];

        for (int i = 0; i < len; i++) {
            int give = 0;
            int receive = 0;
            for (int j = 0; j < len; j++) {
                give+=friendsInfo[i][j].give;
                receive+=friendsInfo[i][j].receive;
            }
            giftScore[i]=give-receive;
        }
        int answer = 0;
        for (int i = 0; i < len; i++) {
            int receiveGift = 0;
            for (int j = 0; j < len; j++) {
                if(friendsInfo[i][j].give>friendsInfo[i][j].receive) receiveGift++;
                else if(friendsInfo[i][j].give==friendsInfo[i][j].receive) {
                    if(giftScore[i]>giftScore[j]) receiveGift++;
                }
            }
            answer=Math.max(answer, receiveGift);
        }
        return answer;
    }
    public static void main(String[] args) {
    }
}
