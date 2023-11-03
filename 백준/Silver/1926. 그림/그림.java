import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, maxArea = 0;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        isVisited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int picCnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !isVisited[i][j]) {
                    picCnt++;
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(picCnt).append("\n").append(maxArea);
        System.out.println(sb);

    }

    private static void bfs(int i, int j) {

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

                if(isIn(nr, nc) && map[nr][nc] == 1 && !isVisited[nr][nc]) {
                    isVisited[nr][nc] = true;
                    cnt++;
                    q.add(new Point(nr, nc));
                }
            }

        }

        maxArea = Math.max(maxArea, cnt);

    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= n || c >= m) return false;
        return true;
    }

    private static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}