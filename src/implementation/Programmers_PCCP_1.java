package implementation;

/**
 * 붕대 감기
 */
public class Programmers_PCCP_1 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int spendTime = 0;
        int time = 0;
        int attackIndex = 0;
        int maxTime = bandage[0];
        int perHeal = bandage[1];
        int addHeal = bandage[2];
        int nowHealth = health;

        while(attackIndex<attacks.length) {
            time++;
            if(time==attacks[attackIndex][0]) {
                nowHealth-=attacks[attackIndex][1];
                if(nowHealth<=0) {
                    break;
                }
                attackIndex++;
                spendTime=0;
            }
            else {
                spendTime++;
                int heal = perHeal;
                if(spendTime==maxTime) {
                    heal+=addHeal;
                    spendTime=0;
                }
                nowHealth = (nowHealth+heal<=health)?nowHealth+heal:health;
            }
        }
        answer=(nowHealth<=0)?-1:nowHealth;
        return answer;
    }
}
