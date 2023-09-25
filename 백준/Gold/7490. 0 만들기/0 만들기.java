import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static int N;
    private static char[] op;
    private static ArrayList<char[]> al;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {

            N = Integer.parseInt(br.readLine());
            op = new char[N-1];
            al = new ArrayList<>();

            make(N, N, 0, N-2, 1);

            // ASCII 순서에 맞춰서 다시 정렬
            Collections.sort(al, (o1, o2) -> {
                String strA = new String(o1);
                String strB = new String(o2);
                return strA.compareTo(strB);
            });

            for(char[] c : al) {
                sb.append(1);
                for(int i = 0; i < c.length; i++) {
                    sb.append(c[i]).append(i+2);
                }
                sb.append("\n");
            }

            sb.append("\n");

        }

        System.out.println(sb);

    }

    private static void make(int num, int value, int result, int index, int ten) {

        // 종료조건
        if(num == 1) {

            if(ten == 1) {
                result += 1;
            }
            else {
                result += value + (num * ten);
            }

            if(result == 0) {
                char[] tmp = new char[op.length];
                for(int i = 0; i < op.length; i++) {
                    tmp[i] = op[i];
                }
                al.add(tmp);
            }

            return;
        }

        if(ten == 1) {
            value = num;
        }
        else {
            value += num * ten;
        }

        op[index] = ' ';
        make(num-1, value, result, index-1, ten * 10);

        op[index] = '+';
        make(num-1, value, result+value, index-1, 1);

        op[index] = '-';
        make(num-1, value, result-value, index-1, 1);

    }

}