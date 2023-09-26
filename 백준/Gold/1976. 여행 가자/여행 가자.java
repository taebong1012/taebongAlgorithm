import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N+1];
        for(int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }

        StringTokenizer st;
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++) {

                if(Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }

            }
        }

        int[] toVisits = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            toVisits[i] = Integer.parseInt(st.nextToken());
        }

        int parent = find(toVisits[0]);
        boolean isPossible = true;
        for(int city : toVisits) {
            if(parent != find(city)) {
                isPossible = false;
                break;
            }
        }

        if(isPossible) System.out.println("YES");
        else System.out.println("NO");

    }

    private static int find(int city) {

        if(parents[city] == city) return city;
        else return parents[city] = find(parents[city]);

    }

    private static void union(int city1, int city2) {

        city1 = find(city1);
        city2 = find(city2);

        parents[city1] = city2;

    }

}