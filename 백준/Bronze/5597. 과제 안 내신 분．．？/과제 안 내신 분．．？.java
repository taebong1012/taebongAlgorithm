import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        boolean[] booleans = new boolean[31];

        for(int i = 0; i < 28; i++) {
            booleans[sc.nextInt()] = true;
        }
        
        for(int i = 1; i < 31; i++) {
            if(!booleans[i]) System.out.println(i);
        }

    }
}