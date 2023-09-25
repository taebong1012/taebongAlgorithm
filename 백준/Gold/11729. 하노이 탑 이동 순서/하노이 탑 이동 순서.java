import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int cnt;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 어디서 어디로 이동하는지
        move(N, 1, 2, 3);

        System.out.println(cnt);
        System.out.println(sb);


    }

    private static void move(int N, int start, int mid, int end) {

        // 재귀 종료 조건(원반 1개 남았으면)
        if(N == 1) {
            cnt++;
            sb.append(start + " " + end + "\n");
            return;
        }

        // 옮겨야하는 제일 큰 원반 빼고 N-1개를 중간으로 옮기기
        move(N-1, start, end, mid);

        cnt++;
        sb.append(start + " " + end + "\n");

        // 중간으로 옮겼던것을 3번 막대로 옮기기
        move(N-1, mid, start, end);

    }
}