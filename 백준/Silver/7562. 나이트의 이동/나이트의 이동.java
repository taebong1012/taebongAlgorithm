import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int I, toR, toC;
    private static boolean[][] isVisited;
    private static int[] dr={-1, -2, -2, -1, 1, 2, 2, 1}, dc={-2, -1, 1, 2, 2, 1,-1,-2};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            isVisited = new boolean[I][I];

            st = new StringTokenizer(br.readLine());
            int fromR = Integer.parseInt(st.nextToken());
            int fromC = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            toR = Integer.parseInt(st.nextToken());
            toC = Integer.parseInt(st.nextToken());

            int ans = bfs(fromR, fromC, 0);

            sb.append(ans).append("\n");

        }   // 테스트 케이스 끝

        System.out.println(sb);

    }

    private static int bfs(int fromR, int fromC, int fromCnt) {

        int ans = Integer.MAX_VALUE;
        Queue<Knight> q = new LinkedList<>();
        q.add(new Knight(fromR, fromC, fromCnt));
        isVisited[fromR][fromC] = true;

        while(!q.isEmpty()) {

            Knight cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int cnt = cur.cnt;

            // 도착지인지 확인
            if(r == toR && c == toC) {
                ans = Math.min(ans, cnt);
            }

            for(int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int ncnt = cnt+1;

                if(isIn(nr, nc) && !isVisited[nr][nc]) {
                    isVisited[nr][nc] = true;
                    q.add(new Knight(nr, nc, ncnt));
                }
            }

        }

        return ans;

    }

    private static class Knight {
        int r;
        int c;
        int cnt;

        Knight(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= I || c >= I) return false;
        return true;
    }

}