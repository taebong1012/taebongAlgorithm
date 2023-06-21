import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 만들어야하는 수열을 배열에 저장
        int[] sequence = new int[n];
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        int curIndex = 0;  //현재 탐색중인 인덱스
        int length = 0; // 만들고 있는 수열의 길이

        Stack<Integer> stack = new Stack<Integer>();
        for(int num = 1; num <= n; num++) {
            stack.push(num);
            sb.append("+\n");

            while(curIndex < n && !stack.isEmpty() && stack.peek() == sequence[curIndex]) {
                stack.pop();
                sb.append("-\n");

                curIndex++;
                length++;
            }
        }

        if(length == n) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }
    }
}