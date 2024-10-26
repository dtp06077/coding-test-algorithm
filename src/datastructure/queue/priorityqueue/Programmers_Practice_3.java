package datastructure.queue.priorityqueue;

import java.util.*;

public class Programmers_Practice_3 {

    class Task implements Comparable<Task> {
        String subjectName;
        int startTime;
        int processTime;

        Task(String subjectName, int startTime, int processTime) {
            this.subjectName = subjectName;
            this.startTime = startTime;
            this.processTime = processTime;
        }

        @Override
        public int compareTo(Task t) {
            return this.startTime-t.startTime;
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Queue<Task> queue = new PriorityQueue<>();

        for(String[] p : plans) {
            String name = p[0];
            int startH = Integer.parseInt(p[1].substring(0,2));
            int startM = Integer.parseInt(p[1].substring(3,5));
            int playTime = Integer.parseInt(p[2]);

            int startTime = startH*60+startM;

            queue.add(new Task(name, startTime, playTime));
        }

        Stack<Task> stack = new Stack<>();

        Task standardTask = queue.poll();
        int answerIndex = 0;
        int now = standardTask.startTime+standardTask.processTime;

        while(true) {
            if(now<=queue.peek().startTime) {
                answer[answerIndex++]=standardTask.subjectName;

                if(now<queue.peek().startTime&&!stack.isEmpty()) {
                    standardTask = stack.pop();
                    standardTask.startTime = now;
                }
                else {
                    standardTask = queue.poll();
                }
                now=standardTask.startTime+standardTask.processTime;
            }
            else {
                standardTask.processTime = standardTask.startTime+standardTask.processTime-queue.peek().startTime;
                stack.add(standardTask);
                standardTask=queue.poll();
                now=standardTask.startTime+standardTask.processTime;
            }

            if(queue.isEmpty()) {
                answer[answerIndex++]=standardTask.subjectName;
                break;
            }
        }

        while(!stack.isEmpty()) {
            answer[answerIndex++]=stack.pop().subjectName;
        }

        return answer;
    }
}
