import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean isEnd = false;
    private static int[] roots;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        roots = new int[n];
        for(int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }

        int cnt = 0;
        for(int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            union(num1, num2);
            cnt++;
            if(isEnd) break;

        }

        if(isEnd) System.out.println(cnt);
        else System.out.println(0);

    }

    private static int find(int num) {

        if(roots[num] == num) return num;
        else return roots[num]= find(roots[num]);

    }

    private static void union(int num1, int num2) {

        num1 = find(num1);
        num2 = find(num2);

        if(num1 == num2) {
            isEnd = true;
        }

        roots[num2] = num1;

    }

}