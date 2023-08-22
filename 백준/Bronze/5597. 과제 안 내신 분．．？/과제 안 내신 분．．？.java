import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        boolean[] booleans = new boolean[31];

        for(int i = 0; i < 28; i++) {
            booleans[sc.nextInt()] = true;
        }

        int[] arr = new int[2];
        int index = 0;
        for(int i = 1; i < 31; i++) {
            if(!booleans[i]) arr[index++] = i;
        }

        if(arr[0] < arr[1]) System.out.println(arr[0] + "\n" + arr[1]);
        else System.out.println(arr[1] + "\n" + arr[0]);
    }
}