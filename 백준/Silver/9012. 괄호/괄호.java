import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int TestCase = 0; TestCase < T; TestCase++) {
            int sum = 0;
            String input = br.readLine();

            for(int index = 0; index < input.length(); index++) {
                if(input.charAt(index) == '(') {
                    sum += 1;
                } else {
                    sum -= 1;
                }

                if(sum < 0) {
                    break;
                }
            }

            if(sum == 0) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}