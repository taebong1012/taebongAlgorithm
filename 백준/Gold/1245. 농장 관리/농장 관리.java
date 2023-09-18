import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int ans, N, M;
    private static boolean[][] isTop;
    private static int[][] map;
    private static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isTop = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {

                if(!isTop[i][j] && map[i][j] != 0) {
                    if(bfs(i, j)) ans++;
                }

            }
        }

        System.out.println(ans);

    }

    private static boolean bfs(int r, int c) {

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));

        boolean[][] isVisited = new boolean[N][M];
        isVisited[r][c] = true;

        List<Point> topList = new ArrayList<>();

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i = 0; i < 8; i++) {

                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (!isIn(nr, nc) || isVisited[nr][nc]) continue;

                // 주위에 현재보다 높은 곳이 있다면
                if (map[nr][nc] > map[cur.r][cur.c]) {
                    return false;
                }
                // 주위에 현재와 같은 높이의 곳이 있다면
                else if (map[nr][nc] == map[cur.r][cur.c]) {
                    q.offer(new Point(nr, nc));
                    topList.add(new Point(nr, nc));
                }

                isVisited[nr][nc] = true;

            }

        }

        for(Point p : topList) {
            isTop[p.r][p.c] = true;
        }

        return true;
    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || r >= N || c < 0 || c >= M) return false;
        else return true;
    }

    private static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}