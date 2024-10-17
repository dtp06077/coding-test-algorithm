package datastructure.string;

/**
 * 동영상 재생기
 */
public class Programmers_PCCP_1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int v = Integer.parseInt(video_len.substring(0,2))*60+Integer.parseInt(video_len.substring(3,5));
        int p = Integer.parseInt(pos.substring(0,2))*60+Integer.parseInt(pos.substring(3,5));
        int os = Integer.parseInt(op_start.substring(0,2))*60+Integer.parseInt(op_start.substring(3,5));
        int oe = Integer.parseInt(op_end.substring(0,2))*60+Integer.parseInt(op_end.substring(3,5));

        int now = (p>=os&&p<=oe)?oe:p;
        for (int i = 0; i < commands.length; i++) {
            String comm = commands[i];

            switch (comm) {
                case "next" : now+=10; break;
                case "prev" : now-=10; break;
            }

            if(now<0) now=0;
            if(now>v) now=v;
            if (now>=os&&now<=oe) {
                now=oe;
            }

        }
        String mm = String.valueOf(now/60);
        String ss = String.valueOf(now%60);
        if(now/60<10) {
            mm="0"+mm;
        }
        if(now%60<10) {
            ss="0"+ss;
        }

        return mm+":"+ss;
    }
}
