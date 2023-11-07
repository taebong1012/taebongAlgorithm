import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static boolean[][] isTrash;
    private static boolean[][] isVisited;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        isTrash = new boolean[N][M];
        isVisited = new boolean[N][M];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            isTrash[r][c] = true;
        }

        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!isTrash[i][j] || isVisited[i][j]) continue;
                ans = Math.max(ans, bfs(i, j));
            }
        }

        System.out.println(ans);

    }

    private static int bfs(int i, int j) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        isVisited[i][j] = true;
        int cnt = 1;


        while(!q.isEmpty()) {

            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isIn(nr, nc) && !isVisited[nr][nc] && isTrash[nr][nc]) {
                    q.add(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                    cnt++;
                }
            }

        }

        return cnt;
    }

    private static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= M) return false;
        return true;
    }

}