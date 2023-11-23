import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        
        char[] symbols = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: symbols) {
            map.put(c, 0);
        }

        // 점수 입력 받기
        int length = survey.length;
        for(int i = 0; i < length; i++) {
            int value = choices[i] - 4;
            char symbol;

            if(value < 0) {
                symbol = survey[i].charAt(0);
            } else if(value > 0) {
                symbol = survey[i].charAt(1);
            }
            else {
                continue;
            }

            map.put(symbol, map.get(symbol) + Math.abs(value));
        }

        // 정답 만들기
        String answer = "";
        // 1번 지표
        if(map.get('R') >= map.get('T')) {
            answer += 'R';
        } else {
            answer += 'T';
        }
        // 2번 지표
        if(map.get('C') >= map.get('F')) {
            answer += 'C';
        } else {
            answer += 'F';
        }
        // 3번 지표
        if(map.get('J') >= map.get('M')) {
            answer += 'J';
        } else {
            answer += 'M';
        }
        // 4번 지표
        if(map.get('A') >= map.get('N')) {
            answer += 'A';
        } else {
            answer += 'N';
        }

        return answer;
        
    }
}