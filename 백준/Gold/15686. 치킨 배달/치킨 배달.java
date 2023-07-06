import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int answer = Integer.MAX_VALUE;
    private static int chickenHouseCnt = 0;    //치킨 집의 개수
    private static int M;   // 몇개를 남길 것인지
    private static ChickenHouse[] selectedChickenHouse;
    private static ArrayList<ChickenHouse> chickenHouseList;
    private static int[][] map;

    public static class ChickenHouse {
        int r, c;

        public ChickenHouse(int i, int j) {
            this.r = i;
            this.c = j;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chickenHouseList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    chickenHouseCnt++;  //치킨 집 개수 세기
                    chickenHouseList.add(new ChickenHouse(i, j));   //치킨 집의 좌표가 들어있는 배열
                }
            }
        }

        selectedChickenHouse = new ChickenHouse[M];

        comb(0, 0);

        System.out.println(answer);
    }

    // 치킨 거리 구하는 함수
    public static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    //치킨 집들 중 남길 것을 고르는 조합 메소드
    public static void comb(int cnt, int start) {

        //치킨집 M개를 모두 골랐다면
        if(cnt == M) {

            int minDistanceAmount = 0;  //현재 고른 치킨집일 경우의 도시의 치킨 거리
            //지도를 돌면서
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    if(map[i][j] == 1) {    //집인 곳을 찾음
                        int minDistance = Integer.MAX_VALUE;
                        //고른 치킨집 들 중 해당 집에서 가장 치킨 거리가 짧은 곳 구하기
                        for(int k = 0; k < M; k++) {
                            int distance = getDistance(i, j, selectedChickenHouse[k].r, selectedChickenHouse[k].c);
                            minDistance = Math.min(minDistance, distance);
                        }
                        minDistanceAmount += minDistance;
                    }
                }
            }

            answer = Math.min(minDistanceAmount, answer);

            return;
        }

        for(int i = start; i < chickenHouseCnt; i++) {
            selectedChickenHouse[cnt] = chickenHouseList.get(i);
            comb(cnt+1, i+1);
        }
    }
}