import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String sequence = "";

        makeSequence(sequence);

    }

    public static void makeSequence(String sequence) {
        if(sequence.length() == N) {
            System.out.println(sequence);
            System.exit(0);
        }

        for(int i = 1; i < 4; i++) {
            String afterSequence = sequence.concat(String.valueOf(i));
            if(isGood(afterSequence)) {
                makeSequence(afterSequence);
            }
        }
    }

    //좋은 수열인지 판단하는 메소드
    public static boolean isGood(String sequence) {

        for(int targetLength = 1; targetLength <= sequence.length()/2; targetLength++) {

            for(int startIndex = 0; startIndex <= sequence.length() - (targetLength * 2); startIndex++) {
                String target = sequence.substring(startIndex, startIndex + targetLength);
                String next = sequence.substring(startIndex + targetLength, startIndex+ (targetLength * 2));

                if(target.equals(next)) return false;
            }

        }
        return true;
    }
}