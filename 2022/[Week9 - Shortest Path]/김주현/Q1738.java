// 1738번 골목길 (G2) [최단 경로 - 벨만포드]
/*
<문제 정보>
 1. 1번 지점에서 n번지점으로 갈 때 가질 수 있는 금품이 최댓값일 때 경로 출력
    - 최적의 경로가 존재하지 않을 때는 -1 출력

<변수 범위>
 1. 2초 / 128MB
 2. 골목길 교차 지점 N 2<=N<=100
 3. 골목길 개수 1<=M<=20,000
 4. 비용 절댓값 1,000 이하

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q1738 {
    static int N, M;
    static Edge3[] edges;
    static int[] money;
    static int[] pathDP;
    static boolean[][] g;
    static boolean[] visited;
    static boolean plusCycle = false;

    static final int START = 1;
    static final int INF = Integer.MIN_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge3[M];
        money = new int[N+1];
        pathDP = new int[N+1];
        g = new boolean[N+1][N+1];
        Arrays.fill(money, INF);
        money[1] = 0;
        int from, to, time;
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            edges[i] = new Edge3(from, to, time);
            g[from][to] = true;
        }
        BellmanFord();
        if(plusCycle || money[N]==INF) sb.append(-1);
        else {
            ArrayList<Integer> path = new ArrayList<>();
            int now = N;
            while(now!=START) {
                path.add(now);
                now = pathDP[now];
            }
            path.add(START);
            Collections.reverse(path);
            for (int v : path) sb.append(v).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BellmanFord() {
        for (int i=1;i<N;i++) edgeRelaxation();
        if (money[N]==INF) return;
        if (pathDP[1]!=0) {
            plusCycle=true;
            return;
        }
        /*HashSet<Integer> pathVertex = new HashSet<>();

        int now = N;
        while(now!=START) {
            if(!pathVertex.add(now)) {
                System.out.printf("now : %d\n",now);
                plusCycle = true;
                return;
            }
            now = pathDP[now];
        }
        pathVertex.add(START);*/
        FloydWarshall();
        for (int i=0;i<M;i++) {
            if(money[edges[i].from]==INF) continue;
            if(money[edges[i].to]<money[edges[i].from]+edges[i].time && g[edges[i].to][N]) {
                plusCycle = true;
                break;
            }
        }
    }

    public static void edgeRelaxation() {
        for (int i=0;i<M;i++) {
            if(money[edges[i].from]==INF) continue;
            if (money[edges[i].to] < money[edges[i].from]+edges[i].time) {
                money[edges[i].to] = money[edges[i].from]+edges[i].time;
                pathDP[edges[i].to] = edges[i].from;
            }
        }
        /*for (int i=1;i<=N;i++) {
            System.out.printf("%d ",pathDP[i]);
        }
        System.out.println();*/
    }

    public static void FloydWarshall() {
        for (int via=1;via<=N;via++) {
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=N;j++) {
                    if (!g[i][j] && i!=j && i!=via && j!=via && g[i][via] && g[via][j]) {
                        g[i][j] = true;
                    }
                }
            }
        }
    }

    public static boolean checkValidCycle(int v) {
        Arrays.fill(visited, false);
        Deque<Integer> q = new ArrayDeque<>();
        q.add(v);
        visited[v] = true;

        int now;

        while(!q.isEmpty()) {
            now = q.poll();
            for (int i=1;i<=N;i++) {
                if (!visited[i] && g[now][i]) {
                    if(i==N) return true;
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return false;
    }
}

class Edge3 {
    int from, to, time;

    public Edge3 (int from, int to, int time) {
        this.from = from;
        this.to = to;
        this.time = time;
    }
}
