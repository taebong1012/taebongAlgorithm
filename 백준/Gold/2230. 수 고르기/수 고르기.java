import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;

        int[] nums = new int[N];

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순으로 정렬
        Arrays.sort(nums);

        int index1 = 0;
        int index2 = 0;

        while(index1 < N && index2 < N) {

            int num1 = nums[index1];
            int num2 = nums[index2];
            int diff = num2 - num1;

            if(diff < M) {
                index2++;
            }
            else {
                answer = Math.min(answer, diff);
                index1++;
            }

        }

        System.out.println(answer);

    }

}