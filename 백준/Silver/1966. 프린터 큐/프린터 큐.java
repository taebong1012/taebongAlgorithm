import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Document {
        int num;
        int importance;

        Document(int num, int importance) {
            this.num = num;
            this.importance = importance;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());    //문서의 개수
            int M = Integer.parseInt(st.nextToken());   //궁금한 문서가 몇번째에 있는지

            st = new StringTokenizer(br.readLine());    //문서 중요도 받기
            PriorityQueue<Integer> importancePQ = new PriorityQueue<Integer>(Collections.reverseOrder());
            Queue<Document> documents = new LinkedList<Document>();

            for(int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                importancePQ.add(importance);
                documents.add(new Document(i, importance));
            }

            int answer = 0;
            while(!documents.isEmpty()) {
                Document firstDocument = documents.poll();
                if(importancePQ.peek().intValue() == firstDocument.importance) {
                    answer++;
                    importancePQ.poll();
                    if(M == firstDocument.num) {
                        sb.append(answer+"\n");
                        break;
                    }
                } else {
                    documents.add(firstDocument);
                }
            }

        }
            System.out.println(sb);
    }
}