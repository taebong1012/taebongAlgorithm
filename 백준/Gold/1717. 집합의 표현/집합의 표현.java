import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] roots;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        roots = new int[n+1];
        for(int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int kind = Integer.parseInt(st.nextToken());    // 0(합집합 실행)인지 1(확인)인지
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            // 합집합 실행
            if(kind == 0) {
                union(num1, num2);
            }

            // 같은 집합인지 확인
            else {
                if(find(num1) == find(num2)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }

        System.out.print(sb);

    }

    private static int find(int num) {
        if(roots[num] == num) return num;
        else return roots[num] = find(roots[num]);
    }

    private static void union(int num1, int num2) {

        num1 = find(num1);
        num2 = find(num2);

        roots[num2] = num1;

    }

}