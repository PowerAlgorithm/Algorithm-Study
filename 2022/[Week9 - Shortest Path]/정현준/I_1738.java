import java.io.*;
import java.util.*;

class Edge {
    int end;
    long cost;

    public Edge(int end, long cost) {
        this.end = end;
        this.cost = cost;
    }
}

/**
 * 1. 음의 사이클이 존재한다고 무조건 -1을 출력하는게 아니라 , 시작 지점과 목표 지점 사이에 음의 사이클이 존재할 때 -1을 출력한다
 * 2. 시작 지점과 목표 지점 까지의 최단 거리로 이동하며 거치는 노드를 기억해야 한다
 */

class Main {
    static int nodeCount, edgeCount;
    static int[] prev;
    static long[] costs;
    static boolean[] visited;
    static List<List<Edge>> edges;
    static List<List<Integer>> rev;
    static final long MIN = -2_000_000_001;
    static final long MAX = 2_000_000_001;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        rev = new ArrayList<>();
        prev = new int[nodeCount + 1];
        costs = new long[nodeCount + 1];
        sb = new StringBuilder();

        for (int i = 0; i <= nodeCount; i++) {
            edges.add(new ArrayList<>());
            rev.add(new ArrayList<>());
            costs[i] = MIN;
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            edges.get(start).add(new Edge(end, cost));
        }

        if (bellmanFord()) {
            int[] trace = new int[nodeCount + 1];
            visited = new boolean[nodeCount + 1];
            visited[1] = true;
            trace[0] = 1;
            findTrace(1, 1, trace);
        } else {
            sb.append("-1");
        }

        System.out.println(sb.toString());
    }

    public static void findTrace(int start, int index, int[] trace) {
        if (start == nodeCount) {
            for (int i = 0; i < index; i++) {
                sb.append(trace[i]).append(" ");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }
        for (Edge nearNode : edges.get(start)) {
            if (!visited[nearNode.end] && costs[nearNode.end] == (costs[start] + nearNode.cost)) {
                visited[nearNode.end] = true;
                trace[index] = nearNode.end;
                findTrace(nearNode.end, index + 1, trace);
            }
        }
    }

    public static boolean bellmanFord() {
        costs[1] = 0;
        for (int loop = 0; loop < nodeCount; loop++) {
            for (int node = 1; node <= nodeCount; node++) {
                if (costs[node] == MIN)
                    continue;
                for (Edge edge : edges.get(node)) {
                    int endNode = edge.end;
                    long cost = edge.cost;
                    if (costs[endNode] < costs[node] + cost) {
                        costs[endNode] = costs[node] + cost;
                        if (loop == nodeCount - 1) {
                            visited = new boolean[nodeCount + 1];
                            visited[node] = true;
                            // 양의 사이클이 도착 지점과 연결되어있는지
                            if (isCycle(node)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCycle(int node) {
        if (node == nodeCount) {
            return true;
        }
        boolean flag = false;
        for (Edge nearNode : edges.get(node)) {
            if (!visited[nearNode.end]) {
                visited[nearNode.end] = true;
                flag |= isCycle(nearNode.end);
            }
        }
        return flag;
    }
}
