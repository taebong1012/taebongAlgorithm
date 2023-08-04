import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 순열
public class Main {

    private static int L, C, moeumCnt;
    private static char[] characters, selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        selected = new char[C];
        characters = new char[C];
        String input = br.readLine();
        int inputIndex = 0;
        for(int i = 0; i < C; i++) {
            characters[i] = input.charAt(inputIndex);
            inputIndex += 2;
        }

        Arrays.sort(characters);

        comb(0, 0);

        System.out.println(sb);

    }

    private static void comb(int cnt, int start) {
        if(cnt == L) {
            // 모음이 1개 이상, 자음이 2개 이상이라면
            if(moeumCnt >= 1 && L-moeumCnt >= 2) {
                for(int i = 0; i < L; i++) sb.append(selected[i]);
                sb.append("\n");
            }
            return;
        }

        for(int i = start; i < C; i++) {
            boolean isMoeum = false;
            if(characters[i] == 'a' || characters[i] == 'e'
                    || characters[i] == 'i' || characters[i] == 'o' || characters[i] == 'u') {
                moeumCnt++;
                isMoeum = true;
            }

            selected[cnt] = characters[i];
            comb(cnt+1, i+1);

            if(isMoeum) moeumCnt--;

        }

    }

}