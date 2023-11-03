import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, p1, p2, ans = 0;
    private static boolean[][] isRelation;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());    // 전체 사람의 수

        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken()) - 1;  // 촌수를 계산해야하는 사람1
        p2 = Integer.parseInt(st.nextToken()) - 1;  // 촌수를 계산해야하는 사람2

        m = Integer.parseInt(br.readLine());

        isRelation = new boolean[n][n];
        isVisited = new boolean[n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            isRelation[n1][n2] = isRelation[n2][n1] = true; // 무향 그래프
        }

        dfs(p1, 0);

        if(ans == 0) ans = -1;
        System.out.println(ans);
    }

    private static void dfs(int from, int cnt) {
        if(from == p2) {
            ans = cnt;
            return;
        }

        isVisited[from] = true;

        for(int i = 0; i < n; i++) {
            if(!isVisited[i] && isRelation[from][i]) {
                dfs(i, cnt+1);
            }
        }

    }

}