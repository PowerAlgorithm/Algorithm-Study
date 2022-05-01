
import java.io.*;
import java.util.*;

class Main {

    public static int Find(int node) {
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if (relation[node] == node)
            return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static void Union(int a, int b) {
        int root_a = Find(a);
        int root_b = Find(b);

        if (root_a != root_b) {
            relation[root_b] = root_a;
        }
    }

    public static void floydWarshall() {
        // k - 거쳐가는 노드
        for (int k = 1; k < nodeCount; k++) {
            // i - 출발 노드
            for (int i = 1; i < nodeCount; i++) {
                // j - 도착 노드
                for (int j = 1; j < nodeCount; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    static int[] relation, highDist, represent;
    static int[][] dist;
    static int nodeCount, edgeCount;
    static final int INF = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        nodeCount = Integer.parseInt(br.readLine()) + 1;
        edgeCount = Integer.parseInt(br.readLine());

        // '관계 배열' 및 '거리 배열' , '최고 거리 기록 배열' , '대표 배열' 초기화
        relation = new int[nodeCount];
        dist = new int[nodeCount][nodeCount];
        highDist = new int[nodeCount];
        represent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            relation[i] = i;
            highDist[i] = INF;
            represent[i] = INF;
        }

        // 1. 각 노드의 차수를 카운트해서 Union할 때 차수가 제일 많은 노드를 부모 노드로 삼게 해보았다
        // - 위 처럼 하면 그래프가 만들어지지 않을 수 있다
        // 2. 플로이드 와샬 + DSU 사용
        // - 플로이드 와샬로 모든 노드의 거릐를 체크한다
        // - DSU로 각 위원회의 집합을 만든다
        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            Union(node1, node2);
            dist[node1][node2] = 1;
            dist[node2][node1] = 1;
        }

        // 플로이드 와샬
        floydWarshall();

        for (int i = 1; i < nodeCount; i++) {
            // i의 부모 노드를 찾고 , i 노드의 가장 먼 노드의 거리를 max로 저장한다
            int root = Find(i), max = Integer.MIN_VALUE;
            for (int j = 1; j < nodeCount; j++) {
                if (dist[i][j] != INF) {
                    max = Math.max(max, dist[i][j]);
                }
            }
            // highDist배열 root인덱스의 값이 위의 max보다 크다면 
            if (highDist[root] > max) {
                // root노드의 인덱스에 max값을 저장하고 ,
                // represent배열 root노드 인덱스에 현재 노드 i를 저장한다 
                highDist[root] = max;
                represent[root] = i;
            }
        }

        Arrays.sort(represent);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int value : represent) {
            if (value != 101) {
                sb.append(value).append("\n");
                count++;
            }
        }
        sb.insert(0, count + "\n");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}