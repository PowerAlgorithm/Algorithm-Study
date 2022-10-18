
import java.io.*;
import java.util.*;

class Edge {
    int node1;
    int node2;

    public Edge(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}

class Main {

    public static int Find(int node) {
        if (relation[node] < 0)
            return node;
        return relation[node] = Find(relation[node]);
    }

    public static void Union(int a, int b) {
        int root_a = Find(a);
        int root_b = Find(b);

        if (root_a == root_b)
            return;

        relation[root_a] += relation[root_b];
        relation[root_b] = root_a;
    }

    static List<Edge> edges;
    static int[] relation;
    static boolean[] visited;
    static int nodeCount, edgeCount, removeCount;

    public static void main(String[] args) throws IOException {
        // 시도 : 입력 받는 간선들을 Map에 저장 , 입력 받는 순서가 키
        // 해당 Map기준으로 트리를 만들면 , 트리를 어떻게 순회할 지 모르겠다... (포기)
        // 참고 링크
        // https://medium.com/dev-ium/boj-17398-%ED%86%B5%EC%8B%A0%EB%A7%9D-%EB%B6%84%ED%95%A0-java-7fe983cb26a3
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken()) + 1;
        edgeCount = Integer.parseInt(st.nextToken());
        removeCount = Integer.parseInt(st.nextToken());

        // 관계 , 방문 배열 초기화
        relation = new int[nodeCount];
        Arrays.fill(relation, -1);
        visited = new boolean[edgeCount + 1];

        edges = new ArrayList<>();
        edges.add(new Edge(0, 0));
        // 간선 입력
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            edges.add(new Edge(node1, node2));
        }

        // 제거할 간선의 정보를 Stack에 담는다
        Deque<Integer> removeEdges = new ArrayDeque<>();
        for (int i = 0; i < removeCount; i++) {
            int removeEdgeIndex = Integer.parseInt(br.readLine());
            removeEdges.push(removeEdgeIndex);
            // 해당 제거할 간선의 인덱스는 true 처리한다
            visited[removeEdgeIndex] = true;
        }

        // 제거할 간선은 제외하고 나머지 간선들로 트리를 만든다
        for (int i = 1; i <= edgeCount; i++) {
            if (!visited[i]) {
                Edge edge = edges.get(i);
                Union(edge.node1, edge.node2);
            }
        }

        long cost = 0;
        while (!removeEdges.isEmpty()) {
            int index = removeEdges.pop();
            Edge edge = edges.get(index);
            int root_a = Find(edge.node1);
            int root_b = Find(edge.node2);

            if (root_a != root_b) {
                cost += relation[root_a] * relation[root_b];
            }

            Union(root_a, root_b);
        }
        bw.append(String.valueOf(cost));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void print() {
        for (int i = 0; i < nodeCount; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int val : relation) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}