// 1753번 최단경로 (G4) [최단경로 - 다익스트라]
/*
<문제 정보>
 1. 시작점에서 다른 모든 정점으로의 최단 경로 구하기
    - 서로 다른 두 정점 사이에 여러 간선이 존재할 수 있음

<변수 범위>
 1. 1초 / 256MB
 2. 정점 V 1<=V<=20,000
 3. 간선 E 1<=E<=300,000
 4. 간선의 시작과 끝은 서로 다름
 5. 가중치는 10이하의 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Q1753_studySolve {
    static int V, E, K;
    static DWGraph g;
    static int[] cost;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        g = new DWGraph(V);
        K = Integer.parseInt(br.readLine());
        int start, end, weight;
        while(E-- >0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            g.addEdge(start, end, weight);
        }
        cost = new int[V+1];
        Arrays.fill(cost, INF);
        cost[K] = 0;
        dijkstra(K);
        for (int i=1;i<=V;i++) sb.append(cost[i]==INF ? "INF" : cost[i]).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(int start) {
        int visitedVertex=0;
        PriorityQueue<CostStatus> pq = new PriorityQueue<>();
        pq.add(new CostStatus(start, 0));
        CostStatus now;

        while(visitedVertex<V && !pq.isEmpty()) {
            now = pq.poll();
            if(g.visited[now.vertex]) continue;
            visitedVertex++;
            g.visited[now.vertex] = true;
            for (Pair17 p : g.adjList[now.vertex]) {
                if(now.cost+p.weight < cost[p.end]) {
                    cost[p.end] = now.cost+p.weight;
                    pq.add(new CostStatus(p.end, cost[p.end]));
                }
            }
        }
    }
}
// D : direction, W : Weighted
class DWGraph {
    ArrayList<Pair17>[] adjList;
    boolean[] visited;

    public DWGraph (int size) {
        adjList = new ArrayList[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge (int start, int end, int weight) {
        adjList[start].add(new Pair17(end, weight));
    }
}

class Pair17 {
    int end, weight;

    public Pair17 (int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

class CostStatus implements Comparable<CostStatus>{
    int vertex, cost;

    public CostStatus (int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(CostStatus o) {
        return this.cost==o.cost ? this.vertex-o.vertex : this.cost-o.cost;
    }
}










