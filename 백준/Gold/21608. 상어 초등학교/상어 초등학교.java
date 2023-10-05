import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private static int[][] seats;
    private static int N;
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seats = new int[N+1][N+1];
        int[][] info = new int[N*N + 1][4];

        // 자리 배치하기
        StringTokenizer st;
        for(int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());

            int studentNum = Integer.parseInt(st.nextToken());

            int[] likes = new int[4];
            for(int j = 0; j < 4; j++) {
                likes[j] = Integer.parseInt(st.nextToken());
                info[studentNum][j] = likes[j];
            }

            Seat result = getLikes(likes);
            seats[result.r][result.c] = studentNum;

        }



        // 만족도 조사
        int ans = 0;

        for(int r = 1; r < N+1; r++) {
            for(int c = 1; c < N+1; c++) {

                int curSeat = seats[r][c];
                int cnt = 0;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(isIn(nr, nc)) {

                        int num = seats[nr][nc];

                        for(int i = 0; i < 4; i++) {
                            if(num == info[curSeat][i]) {
                                cnt++;
                            }
                        }

                    }

                }

                switch (cnt) {
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                    default:
                        ans += 0;
                }

            }
        }

        System.out.println(ans);

    }

    // 비어있는 칸 중 좋아하는 학생이 인접한 칸 구하기
    private static Seat getLikes(int[] likes) {

        ArrayList<Seat> seatList = new ArrayList<>();

        int max = -1;
        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= N; c++) {

                if(seats[r][c] != 0) continue;  // 이미 누가 앉은 자리라면 스킵

                int cnt = 0;

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(isIn(nr, nc)) {
                        for(int like : likes) {
                            if(seats[nr][nc] == like) {
                                cnt++;
                            }
                        }
                    }
                }

                // 인접한 칸에 있는 좋아하는 친구 수가 갱신된다면
                if(cnt > max) {
                    max = cnt;
                    seatList.clear();
                    seatList.add(new Seat(r, c));
                }
                // 최대값과 같다면
                else if(cnt == max) {
                    seatList.add(new Seat(r, c));
                }

            }
        }

        // 좋아하는 학생이 가장 많이 인접한 칸이 1개라면 바로 리턴
        if(seatList.size() == 1) return seatList.get(0);
        // 아니라면 그 중 비어있는 인접칸이 많은 곳 찾으러
        else return getBlank(seatList);
    }

    // 인접한 칸 중 비어있는 칸이 가장 많은 칸 구하기
    private static Seat getBlank(ArrayList<Seat> seatsList) {

        ArrayList<Seat> blankList = new ArrayList<>();

        int maxBlank = -1;

        for(Seat s : seatsList) {

            int cntBlank = 0;
            for(int d = 0; d < 4; d++) {
                int nr = s.r + dr[d];
                int nc = s.c + dc[d];

                if(isIn(nr, nc)) {
                    if(seats[nr][nc] == 0) {
                        cntBlank++;
                    }
                }
            }

            if(cntBlank > maxBlank) {
                maxBlank = cntBlank;
                blankList.clear();
                blankList.add(s);
            }
            else if(cntBlank == maxBlank) {
                blankList.add(s);
            }

        }

        // 인접한 칸 중에서 비어있는 칸이 가장 많은 칸이 한 개라면 리턴
        if(blankList.size() == 1) return blankList.get(0);
        else {
            // 행이 가장 작은 자리, 열이 가장 작은 자리 찾기
            Collections.sort(blankList, (o1, o2) -> {
                return o1.r == o2.r ? o1.c - o2.c : o1.r - o2.r;
            });

            return blankList.get(0);

        }

    }

    private static boolean isIn(int r, int c) {
        if(r < 1 || r >= N+1 || c < 1 || c >= N+1) return false;
        return true;
    }

    private static class Seat {
        int r;
        int c;

        Seat(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
    }

}