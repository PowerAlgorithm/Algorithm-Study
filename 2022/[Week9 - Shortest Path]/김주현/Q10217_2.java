// 10217번 KCM Travel
/*
<문제 정보>
 1. 인천(1) -> LA(N) 를 비용 M원 이하로 갈때 최단경로
 2. LA에 도착할 수 없으면 Poor KCM 출력

<변수 범위>
 1. 10초 / 256MB
 2. 2<=N<=100
 3. 0<=M<=10,000
 4. 티켓정보 수 K 0<=K<=10,000
 5. 소요시간 d 1<=d<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 38926545	hyukk97
// 38800313	gioan92 	73968	1496
// 38284167	xodhksrjqnr 73500	1836

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;



public class Q10217_2 {
    static WeightGraph31 g;
    static int[][] time;
    static int N,M;
    final static int INF = Integer.MAX_VALUE;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int K;
        int from, to, c, t;

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            g = new WeightGraph31(N);
            time = new int[N + 1][M + 1];

            for (int i=1;i<=N;i++) Arrays.fill(time[i],INF);

            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());
                g.addEdge(from, new Air1(to,c,t));
            }

            dijkstra();
            sb.append((time[N][M]==INF) ? "Poor KCM" : time[N][M]).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra() {
        PriorityQueue<Air1> pq = new PriorityQueue<>();
        pq.add(new Air1(1,0,0));
        Air1 now;
        int temp_cost;
        int temp_time;

        while (!pq.isEmpty()) {
            now = pq.poll();
            //System.out.printf("now : (%d, %d, %d)\n",now.to,now.cost,now.time);
            if (now.to == N) break;
            if (now.cost>=M) continue;

            for (Air1 air : g.adjList[now.to]) {
                temp_cost = now.cost + air.cost;
                if (temp_cost>M) continue;
                temp_time = now.time + air.time;
                if (temp_time<time[air.to][temp_cost]) {
                    for (int i=temp_cost;i<=M;i++) {
                        if (temp_time<time[air.to][i]) time[air.to][i] = temp_time;
                        else break; // 이게 시간을 엄청 줄여줌
                    }
                    pq.add(new Air1(air.to,temp_cost,temp_time));
                }
            }
            //printTime(now);
        }
    }

    public static void printTime(Air1 V) {
        System.out.printf("------------------- now : %d, cost : %d, time : %d----------------------\n",V.to,V.cost,V.time);
        for (int i=1;i<=M;i++) {
            for (int j=1;j<=N;j++) {
                System.out.print((time[j][i]==INF) ? "F" : time[j][i]);
                if (j!=N) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------\n");
    }
}

class Air1 implements Comparable<Air1>{
    int to, time, cost;

    public Air1 (int to, int cost, int time) {
        this.to = to;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public int compareTo (Air1 V) {
        return (this.time==V.time) ? this.cost-V.cost : this.time - V.time;
    }
}

// 방향 - 가중치2 그래프
class WeightGraph31 {
    int size;
    public ArrayList<Air1>[] adjList;

    public WeightGraph31 (int size) {
        this.size = size;
        adjList = new ArrayList[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge (int v1, Air1 v2) {
        adjList[v1].add(v2);
    }
}