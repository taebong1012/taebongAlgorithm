import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        if(n == 1 || n == 0) {
            System.out.println(n);
            return;
        }

        n--;

        long[][] origin = {{1, 1}, {1, 0}};
        long[][] A = {{1, 0}, {0, 1}};

        while(n > 0) {

            if(n % 2  == 1) {
                A = multiply(origin, A);
            }
            origin = multiply(origin, origin);

            n /= 2;

        }

        System.out.println(A[0][0]);

    }

    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] result = new long[2][2];

        result[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % 1000000007;
        result[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % 1000000007;
        result[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % 1000000007;
        result[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % 1000000007;

        return result;
    }
}