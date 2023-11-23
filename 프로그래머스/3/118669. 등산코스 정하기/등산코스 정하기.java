import java.util.*;

class Solution {
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private static int[] values;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 그래프 초기화
        for(int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] path : paths) {
            graph.get(path[0]).add(new Node(path[1], path[2]));
            graph.get(path[1]).add(new Node(path[0], path[2]));
        }
        values = new int[n+1];
        for(int i = 1; i < n+1; i++) {
            values[i] = Integer.MAX_VALUE;
        }

        int[] answer = dijkstra(gates, summits);

        return answer;

    }

    private static int[] dijkstra(int[] gates, int[] summits) {

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 입구
        for(int gate : gates) {
            pq.offer(new Node(gate, 0));
            values[gate] = 0;
        }

        while(!pq.isEmpty()) {

            Node cur = pq.poll();
            if(isSummit(cur.index, summits)) continue;
            if(values[cur.index] < cur.value) continue;

            for(Node next : graph.get(cur.index)) {
                int value = (next.value == Integer.MAX_VALUE) ? cur.value : Math.max(cur.value, next.value);
                if(values[next.index] > value) {
                    values[next.index] = value;
                    pq.offer(new Node(next.index, values[next.index]));
                }
            }

        }

        Arrays.sort(summits);
        int index = -1;
        int minValue = Integer.MAX_VALUE;
        for(int s : summits) {
            if(values[s] < minValue) {
                minValue = values[s];
                index = s;
            }
        }

        return new int[] {index, minValue};

    }

    private static boolean isSummit(int index, int[] summits) {
        for(int s : summits) {
            if(s == index) return true;
        }
        return false;
    }

    private static class Node implements Comparable<Node> {
        int index;
        int value;

        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;    // 오름차순으로 정렬
        }
    }
}