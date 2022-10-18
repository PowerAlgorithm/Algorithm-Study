// 24479번 알고리즘 수업 - 깊이 우선 탐색 1 (S2) [BFS&DFS]
/*
<문제 정보>
 1. dfs, 인접정점 오름차순 방문시 각각 정점의 방문 순서 출력

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

public class Q24479 {
    static int N;
    static Graph8 g;
    static int[] visitOrder;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        g = new Graph8(N);
        visitOrder = new int[N+1];
        int v1, v2;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            g.addEdge(v1,v2);
        }
        dfs(R);
        for (int i=1;i<=N;i++) sb.append(visitOrder[i]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(start);
        int now;
        int next;
        int size;
        int order=0;

        while(!stack.isEmpty()) {
            now = stack.pollLast();
            if (g.visited[now]) continue;
            g.visited[now]=true;
            order++;
            visitOrder[now] = order;
//            System.out.printf("now : %d\n",now);

            size = g.adjList[now].size();
            for(int i=0;i<size;i++) {
                next = g.adjList[now].poll();
                if(!g.visited[next]) stack.add(next);
            }
        }
    }
}

// 무방향, 무가중치 그래프
class Graph8 {
    PriorityQueue<Integer>[] adjList;
    boolean[] visited;

    public Graph8(int size) {
        adjList = new PriorityQueue[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new PriorityQueue<>((a, b) -> b-a);
    }

    public void addEdge(int v1, int v2) {
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}






















