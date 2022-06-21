import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

class Main {
    static List<List<Edge>> edges;
    static int[] results;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(br.readLine()) - 1;
        results = new int[nodeCount];
        edges = new ArrayList<>();

        Arrays.fill(results, Integer.MAX_VALUE);

        for (int i = 0; i < nodeCount; i++)
            edges.add(new ArrayList<>());

        // 입력
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edges.get(s).add(new Edge(e, cost));
        }

        // 다익스트라
        dijkstra(startNode);
        for (int cost : results) {
            if (cost == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(cost);
        }
    }

    public static void dijkstra(int startNode) {
        results[startNode] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.offer(new Edge(startNode, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (Edge edge : edges.get(now.node)) {
                int sumCost = now.cost + edge.cost;
                if (results[edge.node] > sumCost) {
                    results[edge.node] = sumCost;
                    pq.offer(new Edge(edge.node, sumCost));
                }
            }
        }
    }
}
