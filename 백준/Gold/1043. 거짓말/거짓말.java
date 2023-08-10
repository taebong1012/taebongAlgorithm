import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static Queue<Integer> q = new LinkedList<>();
    private static int[][] parties;
    private static boolean[] isKnow;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사람 수
        int M = Integer.parseInt(st.nextToken());   // 파티 수

        parties = new int[M][];
        isKnow = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        int knowCnt = Integer.parseInt(st.nextToken());
        for(int i = 0; i < knowCnt; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            q.add(tmp);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            parties[i] = new int[Integer.parseInt(st.nextToken())];

            for(int j = 0; j < parties[i].length; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        bfs();

        // 거짓말 가능한 파티 찾기
        int answer = 0;
        for(int i = 0; i < M; i++) {
            if(parties[i][0] != -1) answer++;
        }

        System.out.println(answer);
    }

    private static void bfs() {

        while (!q.isEmpty()) {

            int know = q.poll();

            if (!isKnow[know]) {

                isKnow[know] = true;

                // 진실을 아는 사람이 있는 파티 찾기
                ArrayList<Integer> knowParties = new ArrayList<>(); // 진실을 아는 파티들
                for (int i = 0; i < parties.length; i++) {
                    for (int j = 0; j < parties[i].length; j++) {
                        // 아는 사람이라면 해당 파티 저장
                        if (parties[i][j] == know) {
                            knowParties.add(i);
                            break;
                        }
                    }
                }

                // 진실을 아는 파티ㅢ 사람들을 -1(앎)처리하고 q에 추가
                for (int p : knowParties) {
                    for (int i = 0; i < parties[p].length; i++) {
                        if (parties[p][i] != -1) {
                            q.add(parties[p][i]);
                            parties[p][i] = -1;
                        }
                    }
                }

            }

        }
    }


    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0 ; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}