import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long cnt = 0;
        Stack<Integer> buildings = new Stack<>();

        for(int i = 0; i < N; i++) {

            int curBuilding = Integer.parseInt(br.readLine());

            while(!buildings.isEmpty() && buildings.peek() <= curBuilding) {
                buildings.pop();
            }

            buildings.add(curBuilding);

            cnt += buildings.size() - 1;

        }

        System.out.println(cnt);

    }

}