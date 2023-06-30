import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            //배열 입력 받기
            List<Integer> list = new LinkedList<Integer>();
            for(int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int countR = 0; // 연속된 R을 세서 뒤집기 횟수 최소화
            boolean isReverse = false;  // 뒤집은 상태인지
            boolean isPossible = true;  // 가능한 조작인지 (가능하면 true, 불가능하면 false)


            //입력받은 RD 함수를 통해서 배열 조작
            for(int i = 0; i < p.length(); i++) {
                char function = p.charAt(i);

                // 1. 입력받은 함수가 R이라면 뒤집기
                if(function == 'R') {
                    countR++;
                }
                // 2. 입력받은 함수가 D라면 첫 번째 수 버리기
                else if(function == 'D') {
                    // 지금까지 연속된 R의 개수가 홀수라면 뒤집기
                    if(countR % 2 == 1) {
                        isReverse = !isReverse;
                    }

                    countR = 0; //countR 초기화

                    // list가 비어있다면 error
                    if(list.isEmpty()) {
                        isPossible = false;
                        break;
                    }
                    // 비어있지 않을 경우
                    else {
                        // 뒤집어져있지 않다면 앞에꺼 버리기
                        if(!isReverse) {
                            list.remove(0);
                        }
                        //뒤집어져 있다면 맨 뒤에꺼 버리기
                        else {
                            list.remove(list.size() - 1);
                        }
                    }
                }
            }

            if(countR % 2 == 1) {
                isReverse = !isReverse;
            }

            //가능하다면
            if(isPossible) {
                if(isReverse) {
                    Collections.reverse(list);
                }

                if(list.isEmpty()) {
                    sb.append("[]\n");
                } else {
                    sb.append(list).append("\n");
                }
            } else {
                sb.append("error\n");
            }

        }

        String sbValue = String.valueOf(sb);
        String result = sbValue.replace(" ", "");
        System.out.println(result);

    }
}