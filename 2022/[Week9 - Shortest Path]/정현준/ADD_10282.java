import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node;
    long time;

    public Edge(int node, long time) {
        this.node = node;
        this.time = time;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.valueOf(this.time).compareTo(o.time);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node=" + node +
                ", time=" + time +
                '}';
    }
}

class Main {
    static int nodeCount, edgeCount, start;
    static List<List<Edge>> edges;
    static StringBuilder sb;
    static final long INF = 1_000_000_000_001l;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeCount = Integer.parseInt(st.nextToken());
            edgeCount = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken()) - 1;
            edges = new ArrayList<>();

            for (int i = 0; i < nodeCount; i++)
                edges.add(new ArrayList<>());

            // a depends on b (a가 b를 의존하다)
            for (int i = 0; i < edgeCount; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int time = Integer.parseInt(st.nextToken());
                edges.get(b).add(new Edge(a, time));
            }
            dijkstra();
        }
        System.out.println(sb.toString());
    }

    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long[] costs = new long[nodeCount];
        Arrays.fill(costs, INF);
        costs[start] = 0;
        pq.offer(new Edge(start, 0));

        int count = 1;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (Edge near : edges.get(now.node)) {
                long sum = now.time + near.time;
                if (costs[near.node] > sum) {
                    if (costs[near.node] == INF)
                        count++;
                    costs[near.node] = sum;
                    pq.offer(new Edge(near.node, sum));
                }
            }
        }
        sb.append(count).append(" ").append(getMinTime(costs)).append("\n");
    }

    public static long getMinTime(long[] costs) {
        long result = 0;
        for (int i = 0; i < nodeCount; i++) {
            if (costs[i] != INF)
                result = Math.max(result, costs[i]);
        }
        return result;
    }
}
