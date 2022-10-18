import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node1;
    int node2;
    int uphill;

    public Edge(int node1, int node2, int uphill) {
        this.node1 = node1;
        this.node2 = node2;
        this.uphill = uphill;
    }

    @Override
    public int compareTo(Edge o) {
        return o.uphill - this.uphill;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", uphill=" + uphill +
                '}';
    }
}

class Main {

    public static int Find(int node) {
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if (relation[node] == node)
            return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static boolean Union(Edge edge) {
        int root_a = Find(edge.node1);
        int root_b = Find(edge.node2);

        if (root_a != root_b) {
            // root_b 높이가 크다면 두 노드 swap
            if (rank[root_a] < rank[root_b]) {
                int tmp = root_a;
                root_a = root_b;
                root_b = tmp;
            }

            // root_a 가 부모 노드
            relation[root_b] = root_a;
            if (rank[root_a] == rank[root_b]) {
                rank[root_a]++;
            }
            if (edge.uphill == 0)
                return true;
        }
        return false;
    }

    public static void initRelationAndRank() {
        relation = new int[nodeCount];
        rank = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            relation[i] = i;
        }
    }

    static int[] relation, rank;
    static int nodeCount, edgeCount;
    static Edge startEdge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        nodeCount++;
        edgeCount++;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            // hill 0 : 오르막길 , 1 : 내리막길
            int hill = Integer.parseInt(st.nextToken());
            if (i == 0) {
                startEdge = new Edge(node1, node2, hill);
            } else {
                edges.add(new Edge(node1, node2, hill));
            }
        }

        // 내리막길 순으로 정렬
        Collections.sort(edges);
        initRelationAndRank();

        // 오르막길 카운트
        int bestHillCount = 0;
        // 입구 간선 세팅
        if (Union(startEdge)) {
            bestHillCount++;
        }
        for (Edge edge : edges) {
            if (Union(edge)) {
                bestHillCount++;
            }
        }

        initRelationAndRank();

        // 내리막길 순으로 정렬되어있는 리스트 뒤에서 부터 접근 (오르막길 순으로 접근)
        int worstHillCount = 0;
        // 입구 간선 세팅
        if (Union(startEdge)) {
            worstHillCount++;
        }
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge edge = edges.get(i);
            if (Union(edge)) {
                worstHillCount++;
            }
        }

        int result = (int) (Math.pow(worstHillCount, 2) - Math.pow(bestHillCount, 2));
        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}