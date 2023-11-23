import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        long sum1 = getSum(queue1);
        long sum2 = getSum(queue2);

        // 총합이 짝수가 아니라면 불가능
        if((sum1 + sum2) % 2 != 0) return -1;

        int limit = queue1.length * 4;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }


        int answer = 0;
        while(sum1 != sum2) {

            // 불가능 종료조건
            if(answer > limit) return -1;

            // sum1이 더 클경우
            // q1에서 뽑아서 q2에 넣고 sum들 갱신
            if(sum1 > sum2) {
                int val = q1.poll();
                q2.add(val);
                sum2 += val;
                sum1 -= val;
            }

            // sum2가 더 클 경우
            // q2에서 뽑아서 q1에 넣고 sum들 갱신
            else {
                int val = q2.poll();
                q1.add(val);
                sum1 += val;
                sum2 -= val;
            }

            answer++;
        }

        return answer;
    }

    private static long getSum(int[] arr) {

        long sum = 0;

        for(int i = 0; i < arr.length; i++) {
            sum += (long) arr[i];
        }

        return sum;
    }
}