import java.io.*;
import java.util.*;

class Edge {
    int node;
    long cost;

    public Edge(int node, long cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Main {
    static int node, edge;
    static final int START_NODE = 0;
    static final long INF = Long.MAX_VALUE;
    static long[] costs;
    static int[][] map;
    static List<List<Edge>> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        costs = new long[node];
        map = new int[node][node];
        edges = new ArrayList<List<Edge>>();

        for (int i = 0; i < node; i++) {
            edges.add(new ArrayList<Edge>());
            costs[i] = INF;
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());
            edges.get(start).add(new Edge(end, cost));
        }

        StringBuilder sb = new StringBuilder();
        if (bellmanFord()) {
            for (int i = 1; i < node; i++) {
                sb.append(costs[i] == INF ? "-1" : costs[i]).append("\n");
            }
            System.out.println(sb.toString());
        } else
            System.out.println("-1");
    }

    public static boolean bellmanFord() {
        costs[START_NODE] = 0;

        for (int loop = 0; loop < node - 1; loop++) {
            for (int i = 0; i < node; i++) {
                if (costs[i] == INF)
                    continue;
                for (Edge edge : edges.get(i)) {
                    int endNode = edge.node;
                    long cost = edge.cost;
                    costs[endNode] = Math.min(costs[endNode], costs[i] + cost);
                }
            }
        }

        for (int i = 0; i < node; i++) {
            if (costs[i] == INF)
                continue;
            for (Edge edge : edges.get(i)) {
                int endNode = edge.node;
                long cost = edge.cost;
                if (costs[endNode] > costs[i] + cost) {
                    return false;
                }
            }
        }

        return true;
    }
}