import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, answer;
    private static Egg[] eggs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        eggs = new Egg[N];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(durability, weight);
        }

        doPuzzle(0, 0);

        System.out.println(answer);

    }

    private static void doPuzzle(int index, int cnt) {

        if(index == N) {
            answer = Math.max(answer, cnt);
            return;
        }

        // 집은 달걀이 깨져있다면 다음 달걀 집기
        if(eggs[index].durability <= 0 || cnt == N-1) {
            doPuzzle(index+1, cnt);
            return;
        }

        for(int i = 0; i < N; i++) {

            // 집은 달걀과 깰 달걀이 같다면 다음
            if(index == i) continue;

            // 깨려는 달걀이 이미 깨져있다면 다음
            if(eggs[i].durability <= 0) continue;

            // 달걀 치기
            eggs[i].durability -= eggs[index].weight;
            eggs[index].durability -= eggs[i].weight;

            int newCnt = cnt;
            if(eggs[i].durability <= 0) newCnt++;
            if(eggs[index].durability <= 0) newCnt++;

            doPuzzle(index+1, newCnt);

            // 달걀 치기 전으로
            eggs[i].durability += eggs[index].weight;
            eggs[index].durability += eggs[i].weight;

        }

    }

    private static class Egg {

        int durability; // 내구도
        int weight; // 무게

        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

    }

}