import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int w, h;
    private static int[][] map;
    private static boolean[][] isVisited;
    private static int[] dr = {-1, -1 , -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {

            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 테스트 케이스 끝
            if(w == 0 && h == 0) break;

            map = new int[h][w];
            isVisited = new boolean[h][w];

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // bfs 돌리기
            int cnt = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] == 1 && !isVisited[i][j]) {
                        cnt++;
                        bfs(i, j);
                    }
                }
            }

            sb.append(cnt).append("\n");

        }

        System.out.println(sb);
    }

    private static void bfs(int i, int j) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));

        while(!q.isEmpty()) {

            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            for(int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(isIn(nr, nc) && map[nr][nc] == 1 && !isVisited[nr][nc]) {
                    isVisited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }

            }
        }

    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= h || c >= w) return false;
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