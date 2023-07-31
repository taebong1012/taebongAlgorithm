import java.util.*;

class Solution {
    
    public long solution(int[] sequence) {
        
        int sequenceLength = sequence.length;

        //-1로 시작하는 펄스 수열로 만드는 누적합
        long[] negative = new long[sequenceLength];
        negative[0] = sequence[0] * -1;

        //1로 시작하는 펄스 수열로 만드는 누적합
        long[] positive = new long [sequenceLength];
        positive[0] = sequence[0];

        int pulse = 1;

        if(sequence.length == 1) return Math.abs(sequence[0]);

        for(int i = 1; i < sequenceLength; i++) {
            negative[i] = Math.max(negative[i-1] + (sequence[i] * pulse), (sequence[i] * pulse));
            pulse *= -1;
            positive[i] = Math.max(positive[i-1] + (sequence[i] * pulse), (sequence[i] * pulse));
        }

        //각 배열에서 가장 큰 수와 가장 작은 수를 뽑기위해 오름차순으로 정렬
        Arrays.sort(negative);
        Arrays.sort(positive);

        return Math.max(negative[sequenceLength - 1], positive[sequenceLength - 1]);
    }
}