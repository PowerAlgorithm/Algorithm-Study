// 11657번 타임머신 (G4) [최단경로 - 벨만포드]
/*
<문제 정보>
 1. 1번 도시에서 다른 도시까지 가장 빠른 시간 출력
    - 어떤 도시로 가는 과정을 무한히 오래전으로 돌릴 수 있으면 -1 출력

<변수 범위>
 1. 1초 / 256MB
 2. 도시의 개수 1<=N<=500
 3. 버스 노선 1<=M<=6,000
 4. 시간 -10,000<=C<=10,000
// 범위초과 가능성
**벨만포드 진행시 음수 사이클이 있으면 한사이클이 돌때마다 양수 최대치만큼이 음수쪽으로 늘어남
그래서 음수쪽의 max값을 생각할땐 양수쪽처럼 생각하면 안된다.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q11657_studySolve {
    static int N, M;
    static Edge2[] edges;
    static long[] times;
    static boolean minusCycle = false;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge2[M];
        times = new long[N+1];
        Arrays.fill(times, INF);
        times[1] = 0;
        int from, to, time;
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            edges[i] = new Edge2(from, to, time);
        }
        BellmanFord();
        if(minusCycle) sb.append(-1);
        else {
            for (int i=2;i<=N;i++) sb.append(times[i]==INF ? -1 : times[i]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void BellmanFord() {
        for (int i=1;i<N;i++) edgeRelaxation();
        for (int i=0;i<M;i++) {
            if(times[edges[i].from]==INF) continue;
            if(times[edges[i].to]>times[edges[i].from]+edges[i].time) {
                minusCycle = true;
                break;
            }
        }
    }

    public static void edgeRelaxation() {
        for (int i=0;i<M;i++) {
            if(times[edges[i].from]==INF) continue;
            times[edges[i].to] = Math.min(times[edges[i].to], times[edges[i].from]+edges[i].time);
        }
    }
}

class Edge2 {
    int from, to, time;

    public Edge2 (int from, int to, int time) {
        this.from = from;
        this.to = to;
        this.time = time;
    }
}

















