import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};    // 우하좌상
    private static boolean[][] isSnake;
    private static Deque<Point> snake = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 보드의 크기
        int K = Integer.parseInt(br.readLine());    // 사과의 개수

        boolean[][] isApple = new boolean[N+1][N+1];
        isSnake = new boolean[N+1][N+1];

        StringTokenizer st;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            isApple[r][c] = true;
        }

        int L = Integer.parseInt(br.readLine());    // 뱀의 방향 변환 정보
        Queue<Info> infos = new LinkedList<>();
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            infos.add(new Info(x, c));
        }

        // 구현
        int time = 0;   // 진행 시간 (정답)
        int curDir = 0; // 0: 오른쪽, 1: 아래, 2: 왼쪽, 3: 위
        snake.add(new Point(1, 1));
        isSnake[1][1] = true;

        while(isEnd(snake.peekLast().r + dr[curDir], snake.peekLast().c + dc[curDir])) {

            int nr = snake.peekLast().r + dr[curDir];
            int nc = snake.peekLast().c + dc[curDir];

            // 뱀 이동 및 길이 업데이트
            isSnake[nr][nc] = true;
            snake.add(new Point(nr, nc));

            // 이동한 자리에 사과가 있다면
            if(isApple[nr][nc]) {
                isApple[nr][nc] = false;
            }
            // 이동한 자리에 사과가 없다면
            else {
                Point tail = snake.pollFirst();
                isSnake[tail.r][tail.c] = false;
            }

            // 시간 증가 후 방향 바꿀 시간이면 방향 바꾸기
            time++;
            if(infos.size() != 0 && time == infos.peek().x) {
                curDir = changeDir(curDir, infos.poll().c);
            }

        }

        System.out.println(time+1);

    }

    private static boolean isEnd(int r, int c) {
        if(r < 1 || r >= N+1 || c < 1 || c >= N+1) return false;
        else if(isSnake[r][c]) return false;

        return true;
    }

    private static int changeDir(int curDir, char change) {
        // 왼쪽으로 회전
        if(change == 'L') {
            if(curDir == 0) return 3;
            else return curDir-1;
        }
        // 오른쪽으로 회전
        else {
            if(curDir == 3) return 0;
            else return curDir+1;
        }

    }

    private static class Point {
        int r;
        int c;

        private Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Info {
        int x;
        char c;

        private Info(int x, char c) {
            this.x = x;
            this.c = c;
        }

    }

}