import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int startNode;
    int endNode;
    int cost;

    public Edge(int startNode, int endNode, int cost) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                ", cost=" + cost +
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

    public static int Union(Edge edge) {
        nodes.get(edge.startNode).add(edge.endNode);
        nodes.get(edge.endNode).add(edge.startNode);

        int startNodeRoot = Find(edge.startNode);
        int endNodeRoot = Find(edge.endNode);

        if (startNodeRoot == endNodeRoot)
            return 0;
        else {
            relation[startNodeRoot] = endNodeRoot;
            madeEdgeCount++;
        }
        return edge.cost;
    }

    public static void initRelationArr(int nodeCount) {
        relation = new int[nodeCount + 1];
        for (int i = 1; i < relation.length; i++)
            relation[i] = i;
    }

    static int madeEdgeCount;
    static int[] relation;
    static int[] rank;
    static List<Edge> list;
    static List<List<Integer>> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        int gameCount = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        nodes = new ArrayList<>();
        for (int i = 0; i < nodeCount + 1; i++) {
            nodes.add(new ArrayList<>());
        }

        // 높이 배열 생성
        rank = new int[nodeCount + 1];

        for (int i = 1; i <= edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            // 게임 턴이 지나갈 수록 비용이 제일 낮은 엣지를 빼기위해 큐를 사용
            list.add(new Edge(startNode, endNode, i));
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        // 게임 진행
        for (int game = 0; game < gameCount; game++) {
            // 관계 배열 초기화
            initRelationArr(nodeCount);
            int result = 0;
            // 매 게임 마다 제일 낮은 cost는 제외하고 MST를 생성한다
            // 만들어진 edgeCount
            madeEdgeCount = 0;
            for (int i = game; i < list.size(); i++) {
                Edge edge = list.get(i);
                result += Union(edge);
            }
            // 한 개의 MST가 만들어졌는지 확인하는데 고생...
            // 만들어진 edge의 갯수가 node - 1과 같다면 MST 만들어짐
            if (madeEdgeCount == nodeCount - 1) {
                sb.append(result).append(" ");
            }
            // MST가 만들어지지 않았다면 MST를 만들지 않는다
            else {
                sb.append("0 ");
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}