import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        long[][] dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        dp[0][0] = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                int move = map[i][j];
                if(move == 0) break;

                if(i + move < N) dp[i+move][j] += dp[i][j];
                if(j + move < N) dp[i][j+move] += dp[i][j];

            }
        }

        System.out.println(dp[N-1][N-1]);

    }

}