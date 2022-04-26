// 24481번 알고리즘 수업 - 깊이 우선 탐색 3 (S2) [DFS]
/*
<문제 정보>
 1. dfs, 인접 정점 오름차순 방문

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

public class Q24481 {
    static int N;
    static Graph9 g;
    static int[] depths;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        g = new Graph9(N);
        int v1, v2;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            g.addEdge(v1, v2);
        }
        depths = new int[N+1];
        dfs(R);
        for (int i=1;i<=N;i++) {
            if (i!=R && depths[i]==0) sb.append(-1).append("\n");
            else sb.append(depths[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start) {
        Deque<Pair1> stack = new ArrayDeque<>();
        stack.add(new Pair1(start, 0));
        Pair1 now;
        int next;
        int size;

        while(!stack.isEmpty()) {
            now = stack.pollLast();
            if(g.visited[now.vertex]) continue;
            g.visited[now.vertex] = true;
            depths[now.vertex] = now.depth;

            size = g.adjList[now.vertex].size();
            while (size-->0) {
                next = g.adjList[now.vertex].poll();
                if (!g.visited[next]) stack.add(new Pair1(next, now.depth+1));
            }

        }
    }
}

// 무방향, 무가중치 그래프
class Graph9 {
    PriorityQueue<Integer>[] adjList;
    boolean[] visited;

    public Graph9 (int size) {
        adjList = new PriorityQueue[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new PriorityQueue<>((a,b) -> b-a);
    }

    public void addEdge(int v1, int v2) {
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}

class Pair1 {
    int vertex;
    int depth;

    public Pair1 (int v, int d) {
        this.vertex = v;
        this.depth = d;
    }
}





















