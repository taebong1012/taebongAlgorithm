import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int h, w;
    private static char[][] map;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static Queue<Point> fireQ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fireQ = new LinkedList<>();

            Point start = new Point(-1, -1);
            for(int i = 0; i < h; i++) {
                String input = br.readLine();
                for(int j = 0; j < w; j++) {
                    char tmp = input.charAt(j);
                    map[i][j] = tmp;

                    if(tmp == '@') start = new Point(i, j);
                    else if(tmp == '*') fireQ.add(new Point(i, j));

                }
            }

            int ans = bfs(start.r, start.c);
            if(ans == -1) {
                sb.append("IMPOSSIBLE\n");
            }
            else {
                sb.append(ans + "\n");
            }

        }

        System.out.println(sb);

    }

    private static int bfs(int i, int j) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j, 0));

        while(!q.isEmpty()) {

            // 불 옮겨짐
            int fireQSize = fireQ.size();

            while(fireQSize-- > 0) {
                Point curFire = fireQ.poll();
                int fr = curFire.r;
                int fc = curFire.c;

                for(int d = 0; d < 4; d++) {
                    int nfr = fr + dr[d];
                    int ncr = fc + dc[d];

                    if(isIn(nfr, ncr) && map[nfr][ncr] != '#' && map[nfr][ncr] != '*') {
                        fireQ.add(new Point(nfr, ncr));
                        map[nfr][ncr] = '*';
                    }
                }
            }

            int qSize = q.size();
            while(qSize-- > 0) {

                Point cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int cnt = cur.cnt;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    int ncnt = cnt + 1;

                    // 밖으로 탈출했으면 성공
                    if(!isIn(nr, nc)) {
                        return cnt + 1;
                    }
                    // 상근이 이동
                    else if(map[nr][nc] == '.') {
                        map[nr][nc] = '@';
                        q.add(new Point(nr, nc, ncnt));
                    }
                }

            }

        }

        return -1;

    }

    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= h || c >= w) return false;
        return true;
    }

    private static class Point {
        int r;
        int c;
        int cnt;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

    }

}