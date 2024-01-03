import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int ans = 0;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        isVisited = new boolean[F+1];

        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        bfs(F, S, G, U, D);

        if(ans == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(ans);
        }
    }

    private static void bfs(int F, int S, int G, int U, int D) {

        Queue<Floor> q = new LinkedList<>();

        q.add(new Floor(S, 0));
        isVisited[S] = true;

        while(!q.isEmpty()) {

            Floor cur = q.poll();
            int curFloor = cur.floor;
            int curCnt = cur.cnt;

            if(curFloor == G) {
                ans = curCnt;
                return;
            }

            // 위로 올라갔을 경우
            int upFloor = curFloor + U;
            if(upFloor <= F && !isVisited[upFloor]) {
                isVisited[upFloor] = true;
                q.add(new Floor(upFloor, curCnt+1));
            }

            // 아래로 내려갔을 경우
            int downFloor = curFloor - D;
            if(downFloor >= 1 && !isVisited[downFloor]) {
                isVisited[downFloor] = true;
                q.add(new Floor(downFloor, curCnt+1));
            }

        }

        ans = -1;

    }

    private static class Floor {
        int floor;
        int cnt;

        Floor(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }
}