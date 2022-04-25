// 24444번 알고리즘 수업 - 너비 우선 탐색 1 (S2) [BFS]
/*
<문제 정보>
 1. bfs, 무방향 무가중치 그래프, 인접정점 오름차순 방문
 2. 방문순서 출력

<변수 범위>
 1. 1초 / 512MB

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q24444 {
    static int N;
    static Graph10 g;
    static int[] visitOrder;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        g = new Graph10(N);
        int v1, v2;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            g.addEdge(v1, v2);
        }
        visitOrder = new int[N+1];

        bfs(R);
        for (int i=1;i<=N;i++) sb.append(visitOrder[i]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void bfs (int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        g.visited[start]=true;
        int now;
        int next;
        int size;
        int order=0;

        while(!q.isEmpty()) {
            now = q.poll();
            visitOrder[now] = ++order;

            size = g.adjList[now].size();
            while(size-- >0) {
                next = g.adjList[now].poll();
                if(!g.visited[next]) {
                    g.visited[next]=true;
                    q.add(next);
                }
            }
        }
    }
}

// 무방향, 무가중치 그래프
class Graph10 {
    PriorityQueue<Integer>[] adjList;
    boolean[] visited;

    public Graph10 (int size) {
        adjList = new PriorityQueue[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new PriorityQueue<>();
    }

    public void addEdge (int v1, int v2) {
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}

















